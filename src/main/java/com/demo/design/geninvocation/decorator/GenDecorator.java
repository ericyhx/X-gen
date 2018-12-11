package com.demo.design.geninvocation.decorator;

import com.demo.design.genconf.vo.ModuleConfModel;

public class GenDecorator implements GenComponent{
    protected GenComponent component;
    public GenDecorator(GenComponent component) {
     this.component=component;
    }
    public Object operation(ModuleConfModel moduleConf, String genTypeId, Object obj){
        return null;
    }
}
