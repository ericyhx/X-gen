package com.demo.design.genconf.util.readxml.componentdesign;

import com.demo.design.genconf.util.readxml.explaindesign.*;
import com.demo.design.genconf.util.readxml.memontodesign.ParseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 应用组合模式
 */
public class Parser2 {
    //定义常量
    private static final String BACKLASH="/";
    private static final String DOT=".";
    private static final String DOLLAR="$";
    private static final String OPEN_BRACKET="[";
    private static final String CLOSE_BRACKET="]";
    /**
     * 按照分解的先后顺序的元素名称
     */
    private static List<String> listEles=null;
    private Parser2(){}

    /**
     * 根据传入的字符串表达式，通过解析，组合称为一个抽象的语法树
     * @param expr 要解析的表达式
     * @return 表达式对应的抽象语法树
     */
    public static ReadXmlExpression parse(String expr){
        listEles=new ArrayList<>();
        // root/a/b/c
        //1.分解表达式，得到需要解析的元素名称和元素对应的解析模型
        Map<String, ParseModel> mapPath = parseMapPath(expr);
        //2. 根据元素对应的解析模型转换成相应的解释器对象
        List<ReadXmlExpression> list = mapPath2Expression(mapPath);
        //3. 按照先后顺序组成成为抽象的语法树
        ReadXmlExpression retTree = buildTree(list);
        return retTree;
    }

    /**
     * 按照从左到右的顺序来解析表达式，得到相应的元素名称和该元素相对应的解析模块
     * @param expr
     * @return
     */
    private static Map<String,ParseModel> parseMapPath(String expr){
        // root/a/b/c.name
        Map<String,ParseModel> mapPath=new HashMap<>();
        String[] ss = expr.split(BACKLASH);
        for (int i = 0; i < ss.length; i++) {
            if(i==ss.length-1){
                int dotIndex=ss[i].indexOf(DOT);
                if(dotIndex>0){
                    //说明是属性结尾
                    String eleName = ss[i].substring(0, dotIndex);
                    String propName = ss[i].substring(dotIndex + 1);
                    //设置属性前面的元素
                    setParsePath(eleName,false,false,mapPath);
                    //设置顺序
                    setParsePath(propName,true,true,mapPath);
                }else {
                    //说明是元素结尾
                    setParsePath(ss[i],true,false,mapPath);
                }
            }else {
                setParsePath(ss[i],false,false,mapPath);
            }
        }
        return mapPath;
    }
    private static void setParsePath(String eleName,boolean end,boolean propValue,Map<String,ParseModel> mapPath){
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
        mapPath.put(eleName,pm);
        listEles.add(eleName);
    }

    /**
     * 根据元素对应的解析模型转换成相应的解释器对象
     * @param mapPath
     * @return
     */
    private static List<ReadXmlExpression> mapPath2Expression(Map<String,ParseModel> mapPath){
        //一定要按照分解的先后顺序来转换成相应的解释器对象
        List<ReadXmlExpression> list=new ArrayList<>();
        for(String key:listEles){
            ParseModel pm = mapPath.get(key);
            ReadXmlExpression obj=parseModel2ReadExpression(pm);
            list.add(obj);
        }
        return list;
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
     * @param listExp
     * @return
     */
    private static ReadXmlExpression buildTree(List<ReadXmlExpression> listExp){
        //第一个对象，根对象，也就是返回去的对象
        ReadXmlExpression ret=null;
        //用来临时记录上一个元素
        ReadXmlExpression preRe=null;
        for(ReadXmlExpression re:listExp){
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
        }
        return ret;
    }
}
