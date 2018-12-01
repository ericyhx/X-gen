package com.demo.design.genconf.util.readxml.memontodesign;

import com.demo.design.genconf.util.readxml.explaindesign.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 应用组合模式
 * 备忘录模式--->原发器
 */
public class Parser {
    //定义常量
    private static final String BACKLASH="/";
    private static final String DOT=".";
    private static final String DOLLAR="$";
    private static final String OPEN_BRACKET="[";
    private static final String CLOSE_BRACKET="]";
    /**
     * 按照分解的先后顺序的元素名称
     */
    private static List<String> listElesPath=null;
    private Parser(){}
    /*******************************备忘录部分-begin******************************************/
    private static class MementoImpl implements ParseMemento{
        @Getter
        private Map<String,ReadXmlExpression> mapRe=new HashMap<>();
        public MementoImpl(Map<String,ReadXmlExpression> mapRe){
            this.mapRe=mapRe;
        }
    }
    public static ReadXmlExpression parse(String expr){
        ReadXmlExpression retObj=null;
        //1.应该获取备忘录对象
        ParseMemento memento = ParseCaretaker.getInstance().retriveMemento();
        //2.从备忘录中取出数据
        Map<String,ReadXmlExpression> mapRe=null;
        if(memento==null){
            mapRe=new HashMap<>();
        }else {
            mapRe=((MementoImpl)memento).getMapRe();
        }
        //3.从缓存里面找到最长的相同的string来，这部分就不用解析了
        String notParseExpr = searchMaxLongEquals(expr, mapRe);
        //4.获取剩下的需要解析的部分
        String needParseExpr="";
        if(notParseExpr.trim().length()==0){
            needParseExpr=expr;
        }else {
            if(notParseExpr.length()<expr.length()){
                needParseExpr=expr.substring(notParseExpr.length()+1);
            }else {
                needParseExpr="";
            }
        }
        //5. 真正解析剩下的需要解析的string,把两个部分的抽象语法树合并
        if(needParseExpr.trim().length()>0){
            retObj=parse2(needParseExpr,notParseExpr,mapRe);
        }else {
            retObj=mapRe.get(notParseExpr);
        }
        //6. 解析完了，该重新设置备忘录
        ParseCaretaker.getInstance().saveMemnto(new MementoImpl(mapRe));
        return retObj;
    }
    private static String searchMaxLongEquals(String expr,Map<String,ReadXmlExpression> mapRe){
        // a/b/c/d
        boolean flag=mapRe.containsKey(expr);
        while (!flag){
            int lastIndex = expr.lastIndexOf(BACKLASH);
            if(lastIndex>0){
                expr=expr.substring(0,lastIndex);
                flag=mapRe.containsKey(expr+BACKLASH);
            }else {
                expr="";
                flag=true;
            }
        }
        return expr;
    }
    /*********************************备忘录部分-end****************************************/

    /**
     * 根据传入的字符串表达式，通过解析，组合称为一个抽象的语法树
     * @param needParseExpr 要解析的表达式
     * @return 表达式对应的抽象语法树
     */
    public static ReadXmlExpression parse2(String needParseExpr,String notParseExpr,Map<String,ReadXmlExpression> mapRe){
        listElesPath=new ArrayList<>();
        // root/a/b/c
        //1.分解表达式，得到需要解析的元素名称和元素对应的解析模型
        Map<String, ParseModel> mapPath = parseMapPath(needParseExpr);
        //2. 根据元素对应的解析模型转换成相应的解释器对象
        Map<String,ReadXmlExpression> mapPahtAndRe = mapPath2Expression(mapPath);
        //3. 按照先后顺序组成成为抽象的语法树
        ReadXmlExpression prefixre=mapRe.get(notParseExpr+BACKLASH);
        if(prefixre!=null){
            //为了使用过程中对备忘录的数据造成影响，所以应该clone一份
            prefixre = (ReadXmlExpression) mapRe.get(notParseExpr+BACKLASH).clone();
        }
        ReadXmlExpression retTree = buildTree(notParseExpr,prefixre,mapPahtAndRe,mapRe);
        return retTree;
    }

    /**
     * 按照从左到右的顺序来解析表达式，得到相应的元素路径和该元素相对应的解析模块
     * @param expr
     * @return
     */
    private static Map<String,ParseModel> parseMapPath(String expr){
        // root/a/b/c.name
        Map<String,ParseModel> mapPath=new HashMap<>();
        //从根开始的前缀路径
        StringBuffer sb=new StringBuffer();
        String[] ss = expr.split(BACKLASH);
        for (int i = 0; i < ss.length; i++) {
            if(i==ss.length-1){
                int dotIndex=ss[i].indexOf(DOT);
                if(dotIndex>0){
                    //说明是属性结尾
                    String eleName = ss[i].substring(0, dotIndex);
                    String propName = ss[i].substring(dotIndex + 1);
                    //设置路径
                    sb.append(eleName+DOT);
                    //设置属性前面的元素
                    setParsePath(sb,eleName,false,false,mapPath);
                    //设置路径
                    sb.append(propName);
                    //设置属性
                    setParsePath(sb,propName,true,true,mapPath);
                }else {
                    //说明是元素结尾
                    sb.append(ss[i]);
                    setParsePath(sb,ss[i],true,false,mapPath);
                }
            }else {
                sb.append(ss[i]+BACKLASH);
                setParsePath(sb,ss[i],false,false,mapPath);
            }
        }
        return mapPath;
    }
    private static void setParsePath(StringBuffer buffer,String eleName,boolean end,boolean propValue,Map<String,ParseModel> mapPath){
        ParseModel pm=new ParseModel();
        pm.setEnd(end);
        pm.setPropertyValue(propValue);
        pm.setSingleValue(!(eleName.indexOf(DOLLAR)>0));
        //去掉$
        eleName=eleName.replace(DOLLAR,"");
        int tempBegin=0;
        int temEnd=0;
        if((tempBegin=eleName.indexOf(OPEN_BRACKET))>0){
            temEnd=eleName.indexOf(CLOSE_BRACKET);
            pm.setCondition(eleName.substring(tempBegin+1,temEnd));
            eleName=eleName.substring(0,tempBegin);

        }
        pm.setEleName(eleName);
        mapPath.put(buffer.toString(),pm);
        listElesPath.add(buffer.toString());
    }

    /**
     * 根据元素对应的解析模型转换成相应的解释器对象
     * @param mapPath
     * @return
     */
    private static Map<String,ReadXmlExpression> mapPath2Expression(Map<String,ParseModel> mapPath){
        //一定要按照分解的先后顺序来转换成相应的解释器对象
        Map<String,ReadXmlExpression> map=new HashMap<>();
        for(String key:listElesPath){
            ParseModel pm = mapPath.get(key);
            ReadXmlExpression obj=parseModel2ReadExpression(pm);
            map.put(key,obj);
        }
        return map;
    }
    private static ReadXmlExpression parseModel2ReadExpression(ParseModel pm){
        ReadXmlExpression obj=null;
        if(!pm.isEnd()){
            if(pm.isSingleValue()){
                obj=new ElementExpression(pm.getEleName(),pm.getCondition());
            }else {
                obj=new ElementsExpression(pm.getEleName(),pm.getCondition());
            }
        }else {
            //是否属性结束
            if(pm.isPropertyValue()){
                if(pm.isSingleValue()){
                    obj=new PropertyTerminalExpression(pm.getEleName());
                }else {
                    obj=new PropertysTerminalExpression(pm.getEleName());
                }

            }else {
                if(pm.isSingleValue()){
                    obj=new ElementTerminalExpression(pm.getEleName(),pm.getCondition());
                }else {
                    obj=new ElementsTerminalExpression(pm.getEleName(),pm.getCondition());
                }
            }
        }
        return obj;
    }
    /**
     * 按照先后顺序组成成为抽象的语法树
     */
    private static ReadXmlExpression buildTree(String prefixStr,ReadXmlExpression prefixRe,Map<String,ReadXmlExpression> mapPathAndRe,Map<String,ReadXmlExpression> mapRe){
        //第一个对象，根对象，也就是返回去的对象
        ReadXmlExpression ret=prefixRe;
        //用来临时记录上一个元素
        ReadXmlExpression preRe=getLastRe(prefixRe);
        for(String path:listElesPath){
            ReadXmlExpression re = mapPathAndRe.get(path);
            if(preRe==null){
                ret=re;
                preRe=ret;
            }else {
                //把当前元素添加到父元素的下面，同时把自己设置成父元素
                if(preRe instanceof ElementExpression){
                    ((ElementExpression)preRe).addEle(re);
                    preRe=re;
                }else if(preRe instanceof ElementsExpression){
                    ((ElementsExpression)preRe).addEle(re);
                    preRe=re;
                }
            }
            //每次生成一个新的抽象树对象，就应该添加到缓存里面
            if(prefixStr!=null&&prefixStr.trim().length()>0){
                //这里的ret需要进行深度clone
                mapRe.put(prefixStr+BACKLASH+path, (ReadXmlExpression) ret.clone());
            }else {
                mapRe.put(path,(ReadXmlExpression) ret.clone());
            }
        }
        return ret;
    }

    /**
     * 获取已经解析过的对象树的最后一个元素对象
     * @param prefixRe
     * @return
     */
    private static ReadXmlExpression getLastRe(ReadXmlExpression prefixRe){
        ReadXmlExpression lastRe=prefixRe;
        boolean flag=true;
        while (flag){
            if(lastRe instanceof ElementExpression){
                if(((ElementExpression)lastRe).getEles().size()>0){
                    lastRe=((ElementExpression)lastRe).getEles().get(0);
                    if(lastRe instanceof ElementExpression){
                        flag=((ElementExpression)lastRe).getEles().size()>0;
                    }else if(lastRe instanceof ElementsExpression){
                        flag=((ElementsExpression)lastRe).getEles().size()>0;
                    }else {
                        flag=false;
                    }
                }else {
                    flag=false;
                }
            }else if(lastRe instanceof ElementsExpression){
                if(((ElementsExpression)lastRe).getEles().size()>0){
                    lastRe=((ElementsExpression)lastRe).getEles().get(0);
                    if(lastRe instanceof ElementExpression){
                        flag=((ElementExpression)lastRe).getEles().size()>0;
                    }else if(lastRe instanceof ElementsExpression){
                        flag=((ElementsExpression)lastRe).getEles().size()>0;
                    }else {
                        flag=false;
                    }
                }else {
                    flag=false;
                }
            }else {
                flag=false;
            }
        }
        return lastRe;
    }
}
