package com.demo.design.template.flyweight;

import com.demo.design.genconf.vo.ModuleConfModel;

/**
 * 享元对象需要对外提供的一些功能
 */
public interface TemplateFlyweight {
    /**
     * 根据配置和默认的语法来替换模板中的属性值
     * @param moduleConf
     * @param content 作为这次处理的起点内容
     * @return 经过处理后的模板内容
     */
    public Object replaceProperties(ModuleConfModel moduleConf,Object content);

    /**
     * 根据配置和默认的运行方法来替换模板中的相应的值
     * @param moduleConf
     * @param content 作为这次处理的起点内容
     * @return 经过处理后的模板内容
     */
    public Object replaceMethods(ModuleConfModel moduleConf,Object content);

    /**
     * 获取原始模板文件的内容
     * @return
     */
    public Object getRawContent();
}
