package com.demo.design.dispatch;

import com.demo.design.dispatch.command.CmdInvoker;
import com.demo.design.dispatch.command.DefaultCommand;
import com.demo.design.dispatch.command.GenCommand;
import com.demo.design.genconf.implementors.GenConfImplementor;
import com.demo.design.genconf.vo.ModuleConfModel;
import com.demo.design.mediator.CoreMediator;

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
        generate(CoreMediator.getInstance().getDefaultGenConfProvider());
    }

    /**
     * 提供诶外部使用的方法，客户端通过这个方法来请求生成器运行
     * 生成所需要的配置数据，从传入的获取配置数据的实现中获得
     * @param provider
     */
    public static void generate(GenConfImplementor provider) {
        //循环生成在核心框架里面配置需要生成的模块
        Collection<ModuleConfModel> values = CoreMediator.getInstance().getNeedGenModuleConf(provider);
        for(ModuleConfModel mcm:values){
            //具体的生成某一个模块
            genOneModule(mcm);
        }
    }

    /**
     * 具体的生成一个模块
     * 相当于命令模式的客户端
     * @param mcm
     */
    private static void genOneModule(ModuleConfModel mcm){
        //发出一个命令，让x-gen按照配置去生成相应的内容
        //命令模式的客户端分三个步骤：
        //1: 创建命令对象
        GenCommand cmd=new DefaultCommand(mcm);
        //2: 创建invoker
        CmdInvoker invoker=new CmdInvoker();
        invoker.setCmd(cmd);
        //3: 通过invoker执行命令
        invoker.doCommand();

    }
}
