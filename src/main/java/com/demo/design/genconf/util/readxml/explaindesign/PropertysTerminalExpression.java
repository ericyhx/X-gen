package com.demo.design.genconf.util.readxml.explaindesign;

import org.w3c.dom.Element;

import java.util.List;

/**
 * 多属性终结符
 */
public class PropertysTerminalExpression extends ReadXmlExpression{
    public PropertysTerminalExpression(String propName) {
        super.setEleName(propName);

    }

    @Override
    public String[] interpret(Context ctx) {
        List<Element> preEles = ctx.getPreEles();
        String[] ss=new String[preEles.size()];
        int i=0;
        if(preEles.size()>0){
            for(Element pre:preEles){
               ss[i++]=pre.getAttribute(getEleName());
            }
        }
        return ss;
    }
    @Override
    public PropertysTerminalExpression clone(){
        PropertysTerminalExpression obj=null;
        try {
            obj= (PropertysTerminalExpression) super.clone();
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }
}
