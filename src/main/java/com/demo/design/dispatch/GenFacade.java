package com.demo.design.dispatch;

import com.demo.design.genconf.GenConfFactory;
import com.demo.design.genconf.implementors.GenConfImplementor;
import com.demo.design.genconf.vo.ModuleConfModel;

import java.util.Collection;

/**
 * 引入外观模式
 */
public class GenFacade {
    //单例或者工具类

    /**
     * 防止外部无畏的去创建实例，这里采用工具类来实现
     */
    private GenFacade(){}
    /**
     * 提供诶外部使用的方法，客户端通过这个方法来请求生成器运行
     */
    public static void generate() {

    }

    /**
     * 提供诶外部使用的方法，客户端通过这个方法来请求生成器运行
     * 生成所需要的配置数据，从传入的获取配置数据的实现中获得
     * @param provider
     */
    public static void generate(GenConfImplementor provider) {
        //循环生成在核心框架里面配置需要生成的模块
        Collection<ModuleConfModel> values = GenConfFactory.cteateGenConfEbi(provider).getMapModuleConf().values();
        for(ModuleConfModel mcm:values){
            //具体的生成某一个模块
        }
    }

    /**
     * 具体的生成一个模块
     * @param mcm
     */
    private void genOneModule(ModuleConfModel mcm){

    }
}
