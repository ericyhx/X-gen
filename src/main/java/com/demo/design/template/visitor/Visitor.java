package com.demo.design.template.visitor;

/**
 * @Description:
 * @author: yuhongxi
 * @date:2018/12/9
 */

/**
 * 应用访问者模式
 */
public interface Visitor {
    public Object visitTemplateElement(TemplateElement element);
}
