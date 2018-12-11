package com.demo.design.geninvocation.decorator;

import com.demo.design.genconf.vo.ModuleConfModel;
import com.demo.design.mediator.CoreMediator;

public class ReadTemplateContent extends GenDecorator {
    public ReadTemplateContent(GenComponent component) {
      super(component);
    }

    @Override
    public Object operation(ModuleConfModel moduleConf, String genTypeId, Object obj) {
        //注意，通常会作为装饰器组装的第一个，也就是最内核的那个，他的里面不在有装饰器

        //从模块管理模块里面获取具体的模板内容

        //然后返回这个内容

        return CoreMediator.getInstance().getTemplateContent(moduleConf,genTypeId);
    }
}
