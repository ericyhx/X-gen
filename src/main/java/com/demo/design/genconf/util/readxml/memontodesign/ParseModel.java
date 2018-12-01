package com.demo.design.genconf.util.readxml.memontodesign;

import lombok.Data;

@Data
public class ParseModel {
    /**
     * 元素的名称
     */
    private String eleName;
    /**
     * 判断是否是属性值，不是属性值就是元素的值
     */
    private boolean propertyValue;
    /**
     * 是否终结符
     */
    private boolean end;
    /**
     * 是否是单个值
     */
    private boolean isSingleValue;
    /**
     * 条件表达式
     */
    private String condition;
}
