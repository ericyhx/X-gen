package com.demo.design.genproxy;

import com.demo.design.genconf.vo.ModuleConfModel;
import com.demo.design.geninvocation.GenInvocation;
import com.demo.design.mediator.CoreMediator;

public class GenProxyFactory {
    private GenProxyFactory() {
    }
    public static GenInvocation createGenProxy(String needGenType, ModuleConfModel mouduleConf){
        //创建真正执行generate的对象，也就是被代理的对象，现在是默认使用本地的
        GenInvocation invocation= CoreMediator.getInstance().getDefaultGenInvocation(needGenType,mouduleConf);
        //创建代理对象
        return new DefaultProxy(invocation);
    }
}
