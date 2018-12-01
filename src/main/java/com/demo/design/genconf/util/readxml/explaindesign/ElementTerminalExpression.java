package com.demo.design.genconf.util.readxml.explaindesign;

import org.w3c.dom.Element;

import java.util.List;

/**
 * 单元素终结符
 */
public class ElementTerminalExpression extends ReadXmlExpression{
    private String condition;
    public ElementTerminalExpression(String eleName, String condition) {
        this.condition=condition;
        super.setEleName(eleName);
    }

    @Override
    public String[] interpret(Context ctx) {
        //1. 获取到自己这个元素
        //1.1 先获取到父节点
        List<Element> preEles = ctx.getPreEles();
        Element ele=null;
        if(preEles.size()==0){
            //说明是根元素
            ele=ctx.getDocument().getDocumentElement();
        }else {
            ele=ctx.getNowEles(preEles.get(0),getEleName()).get(0);
        }
        //2.判断这个元素是否满足条件
        if(!ctx.judgeCondition(ele,condition)){
            return new String[0];
        }
        //3.获取这个元素的值
        String[] ss=new String[1];
        if(ele.getFirstChild()!=null){
            ss[0]=ele.getFirstChild().getNodeValue();
        }
        return ss;
//        List<Element> nowEles=new ArrayList<>();
//        List<String> result=new ArrayList<>();
//        if(preEles.size()>0){
//            nowEles.addAll(ctx.getNowEles(preEles.get(0),getEleName()));
////            for(Element pre:preEles){
////                nowEles.addAll(ctx.getNowEles(pre,getEleName()));
////            }
//            if(nowEles.size()>0){
//                for (Element e:nowEles){
//                    if(ctx.judgeCondition(e,condition)){
//                        result.add(e.getTextContent());
//                        break;
//                    }
//                }
//            }
//        }
//        String[] ss=new String[result.size()];
//        return result.toArray(ss);
    }

    @Override
    public ElementTerminalExpression clone(){
        ElementTerminalExpression obj=null;
        try {
            obj= (ElementTerminalExpression) super.clone();
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }
}
