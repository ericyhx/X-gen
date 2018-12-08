package com.demo.design.template.visitor;

/**
 * @Description:
 * @author: yuhongxi
 * @date:2018/12/9
 */

import com.demo.design.genconf.vo.ModuleConfModel;
import lombok.Getter;

/**
 * 模板元素
 */
public class TemplateElement {
    @Getter
    private ModuleConfModel moduleConf;

    public TemplateElement(ModuleConfModel moduleConf) {
        this.moduleConf = moduleConf;
    }

    /**
     * 接受访问的方法，也就是预留的调用的通道
     * @param v
     * @return
     */
    public Object accept(Visitor v){
        return v.visitTemplateElement(this);
    }
}
