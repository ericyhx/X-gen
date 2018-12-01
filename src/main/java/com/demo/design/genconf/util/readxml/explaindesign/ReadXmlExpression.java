package com.demo.design.genconf.util.readxml.explaindesign;

import lombok.Data;

/**
 * 解释器模式
 *
 */
@Data
public abstract class ReadXmlExpression implements Cloneable{
    /**
     * 元素的名称
     */
    private String eleName;
    public abstract String[] interpret(Context context);

    @Override
    public Object clone(){
        Object obj=null;
        try {
            obj=super.clone();
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }
}
