package com.demo.design.actions;

import com.demo.design.genconf.vo.ModuleConfModel;
import com.demo.design.geninvocation.decorator.GenComponent;
import com.demo.design.geninvocation.templates.BaseAction;

public class GenBusinessEbiAction extends BaseAction {
    @Override
    public Object initObj() {
        return "";
    }

    @Override
    public Object execute(ModuleConfModel moduleConf, Object obj) {
        //1:读取相应的模板文件

        //2:分解模板文件里面要替换的属性，从moduleConf里面取值替换过去

        //3:分机模板文件里面需要执行的方法，这个并不一定每个功能都需要的
        return obj;
    }

    @Override
    public Object executeDecorators(ModuleConfModel moduleConf, Object obj, GenComponent gc) {
        //注意：这里出啊你的genTypeId必须和theme配置中的GenType的id值一样
        return gc.operation(moduleConf,"GenBusinessEbi",obj);
    }

    @Override
    public GenComponent beforeAction(ModuleConfModel moduleConf) {
        return super.defaultBeforeAction(moduleConf);
    }
}
