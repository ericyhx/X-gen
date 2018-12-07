package com.demo.design.template.flyweight;

import com.demo.design.genconf.vo.ModuleConfModel;

public class DefaultTemplate implements TemplateFlyweight {
    /**
     * 模板的原始内容
     */
    private String rawContent;

    protected DefaultTemplate(String rawContent) {
        this.rawContent = rawContent;
    }

    @Override
    public Object replaceProperties(ModuleConfModel moduleConf, Object content) {
        return null;
    }

    @Override
    public Object replaceMethods(ModuleConfModel moduleConf, Object content) {
        return null;
    }

    @Override
    public Object getRawContent() {
        return this.rawContent;
    }
}
