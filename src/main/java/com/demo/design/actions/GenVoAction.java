package com.demo.design.actions;

import com.demo.design.genconf.vo.ModuleConfModel;
import com.demo.design.geninvocation.decorator.GenComponent;
import com.demo.design.geninvocation.templates.BaseAction;

/**
 * @Description:
 * @author: yuhongxi
 * @date:2018/12/9
 */
public class GenVoAction extends BaseAction {
    @Override
    public Object initObj() {
        return "";
    }

    @Override
    public Object execute(ModuleConfModel moduleConf, Object obj) {
        return obj;
    }

    @Override
    public Object executeDecorators(ModuleConfModel moduleConf, Object obj, GenComponent gc) {
        return gc.operation(moduleConf,"GenVo",obj);
    }

    @Override
    public GenComponent beforeAction(ModuleConfModel moduleConf) {
        return super.defaultBeforeAction(moduleConf);
    }
}
