package com.demo.design.template;

import com.demo.design.genconf.vo.ModuleConfModel;

public class DefaultTemplateEbo implements TemplateEbi {
    /**
     * 模块配置的数据model
     */
    private ModuleConfModel moduleConf;
    /**
     * 具体要生成的类型标识
     */
    private String genTypeId;
    /**
     * 当前被模板处理的内容
     */
    private Object nowContent;

    public DefaultTemplateEbo(ModuleConfModel moduleConf) {
        this.moduleConf = moduleConf;
    }

    @Override
    public Object replaceProperty() {
        //1:读取到相应的模板的原始内容
        //2:分解模板的原始内容，得到需要替换处理的property
        //3:从moduleConfModel得到相应的property的值
        //4:把这个值替换到模板当中的相应的位置去
        //5:一直替换到模板内容里面没有可替换的内容了，那么久处理好了
        return null;
    }

    @Override
    public Object replaceMethod() {
        //1:读取到相应的模板的原始内容
        //2:分解模板的原始内容，得到需要替换处理的method
        //3:运行method获取到结果值
        //4:把这个值替换到模板当中的相应的位置去
        //5:一直替换到模板内容里面没有可替换的内容了，那么久处理好了
        return null;
    }

    @Override
    public Object getNowContent() {
        return this.nowContent;
    }
}
