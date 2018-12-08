package com.demo.design.template;

import com.demo.design.genconf.vo.ModuleConfModel;

public class TemplateFactory {
    private TemplateFactory() {
    }

    public static TemplateEbi createTemplateEbi(ModuleConfModel moduleConf,String genTypeId){
        return new DefaultTemplateEbo(moduleConf,genTypeId);
    }
}
