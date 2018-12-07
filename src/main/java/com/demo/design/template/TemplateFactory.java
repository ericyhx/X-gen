package com.demo.design.template;

import com.demo.design.genconf.vo.ModuleConfModel;

public class TemplateFactory {
    private TemplateFactory() {
    }

    public static TemplateEbi createTemplateEbi(ModuleConfModel moduleConf){
        return new DefaultTemplateEbo(moduleConf);
    }
}
