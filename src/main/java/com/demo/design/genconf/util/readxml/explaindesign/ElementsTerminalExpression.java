package com.demo.design.genconf.util.readxml.explaindesign;

import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * 多元素终结符
 */
public class ElementsTerminalExpression extends ReadXmlExpression {
    private String condition;
    public ElementsTerminalExpression(String eleName, String condition) {
        super.setEleName(eleName);
        this.condition=condition;
    }
    @Override
    public String[] interpret(Context ctx) {
        //1.先获取到自己这个多元素
        List<Element> preEles = ctx.getPreEles();
        //2.获取当前元素，有可能是多个
        List<Element> nowEles=new ArrayList<>();
        if(preEles.size()>0){
            for(Element pe:preEles){
                nowEles.addAll(ctx.getNowEles(pe,getEleName()));
            }
            if(nowEles.size()>0){
                for(Element e:nowEles){
                    if(!ctx.judgeCondition(e,condition)){
                        nowEles.remove(e);
                    }
                }
            }
        }else {
            System.out.println(getEleName()+"没有找到他的父节点");
        }
        String[] ss=new String[nowEles.size()];
        int i=0;
        for(Element ele:nowEles){
            if(ele.getFirstChild()!=null){
                ss[i++]=ele.getFirstChild().getNodeValue();
            }else {
                ss[i++]="";
            }
        }
        return  ss;
    }
    @Override
    public ElementsTerminalExpression clone(){
        ElementsTerminalExpression obj=null;
        try {
            obj= (ElementsTerminalExpression) super.clone();
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }
}
