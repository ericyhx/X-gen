package com.demo.design.template;

import com.demo.design.genconf.vo.ModuleConfModel;
import com.demo.design.template.flyweight.TemplateFlyweight;
import com.demo.design.template.flyweight.TemplateFlyweightFactory;

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
    private TemplateFlyweight flyweight=null;
    public DefaultTemplateEbo(ModuleConfModel moduleConf,String genTypeId) {
        this.moduleConf = moduleConf;
        this.genTypeId=genTypeId;
        //初始化
        this.flyweight=TemplateFlyweightFactory.getInstance().getTemplateFlyweight(moduleConf,this.genTypeId);
        nowContent=flyweight.getRawContent();
    }
    @Override
    public Object replaceProperty() {
        this.nowContent=flyweight.replaceProperties(moduleConf,nowContent);
        return this;
    }

    @Override
    public Object replaceMethod() {
        this.nowContent=flyweight.replaceMethods(moduleConf,nowContent);
        return this;
    }

    @Override
    public Object getNowContent() {
        return this.nowContent;
    }
}
