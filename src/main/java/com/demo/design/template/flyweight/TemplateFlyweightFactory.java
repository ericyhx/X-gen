package com.demo.design.template.flyweight;

import com.demo.design.genconf.util.file.FileHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂
 */
public class TemplateFlyweightFactory {
    private TemplateFlyweightFactory() { }
    private static TemplateFlyweightFactory factory=new TemplateFlyweightFactory();
    public static TemplateFlyweightFactory getInstance(){
        return factory;
    }
    //缓存享元对象
    private Map<String,DefaultTemplate> mapTemplate=new HashMap<>();

    public DefaultTemplate getTemplateweight(String templatePath){
        //按照缓存的写法
        DefaultTemplate template = mapTemplate.get(templatePath);
        if(template==null){
            DefaultTemplate dt=new DefaultTemplate(FileHelper.readFile(templatePath));
            mapTemplate.put(templatePath,dt);
            return dt;
        }else {
            return template;
        }
    }

}
