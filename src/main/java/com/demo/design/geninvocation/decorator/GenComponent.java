package com.demo.design.geninvocation.decorator;

import com.demo.design.genconf.vo.ModuleConfModel;

public interface GenComponent {
    public Object operation(ModuleConfModel moduleConf, String genTypeId, Object obj);
}
