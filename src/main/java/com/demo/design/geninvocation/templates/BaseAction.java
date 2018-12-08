package com.demo.design.geninvocation.templates;

import com.demo.design.genconf.vo.ModuleConfModel;
import com.demo.design.geninvocation.decorator.GenComponent;

/**
 * 引入模板方法
 */
public abstract class BaseAction {
    /**
     * 模板方法，执行每个功能的具体generate过程
     * @param moduleConf
     * @return
     */
    public Object generate(ModuleConfModel moduleConf){
        //1:得到用来封装generate内容的对象
        Object obj=initObj();
        //2:执行具体generate之前要执行的功能
        beforeAction(moduleConf);
        //3:执行action功能
        obj=execute(moduleConf,obj);
        //4:执行具体generate之后要执行的功能
        afterAction(moduleConf);
        return obj;
    }

    /**
     * 工厂方法模式
     * 原语操作，初始化封装generate生成内容的对象
     * @return
     */
    public abstract Object initObj();

    /**
     * 钩子操作，在执行action之前要实现的功能
     * @param moduleConf
     */
    public GenComponent beforeAction(ModuleConfModel moduleConf){

        return null;
    }
    /**
     * 钩子操作，在执行action之后要实现的功能
     * @param moduleConf
     */
    public void afterAction(ModuleConfModel moduleConf){

    }

    /**
     * 原语操作，执行action的generate方法
     * @param moduleConf
     * @param obj
     * @return
     */
    public abstract Object execute(ModuleConfModel moduleConf,Object obj);
    public abstract Object executeDecorators(ModuleConfModel moduleConf, Object obj, GenComponent gc);

    protected GenComponent defaultBeforeAction(ModuleConfModel moduleConf) {
        return null;
    }
}
