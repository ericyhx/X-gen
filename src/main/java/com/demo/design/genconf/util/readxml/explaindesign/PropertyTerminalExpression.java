package com.demo.design.genconf.util.readxml.explaindesign;

import org.w3c.dom.Element;

/**
 * 单属性终结符
 */
public class PropertyTerminalExpression extends ReadXmlExpression{
    public PropertyTerminalExpression(String propName) {
        super.setEleName(propName);
    }

    @Override
    public String[] interpret(Context ctx) {
        String[] ss=new String[1];
        //1. 获取父元素
        Element element = ctx.getPreEles().get(0);
        //2. 直接取该元素的属性值
        ss[0] = element.getAttribute(getEleName());
        return ss;
    }
    @Override
    public PropertyTerminalExpression clone(){
        PropertyTerminalExpression obj=null;
        try {
            obj= (PropertyTerminalExpression) super.clone();
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }
}
