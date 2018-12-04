package com.demo.design.mediator;

import com.demo.design.genconf.GenConfFactory;
import com.demo.design.genconf.implementors.GenConfImplementor;
import com.demo.design.genconf.implementors.xmlimpl.GenConfXmlImpl;
import com.demo.design.genconf.vo.ModuleConfModel;
import com.demo.design.geninvocation.GenInvocation;
import com.demo.design.geninvocation.GenInvocationFactory;
import com.demo.design.genproxy.GenProxyFactory;

import java.util.Collection;

/**
 * 核心框架的中介者对象
 */
public class CoreMediator {
    //实现成为单例
    private static CoreMediator mediator=new CoreMediator();
    private CoreMediator(){}
    public static CoreMediator getInstance(){
        return mediator;
    }
    public GenConfImplementor getDefaultGenConfProvider(){
        return new GenConfXmlImpl();
    }
    public Collection<ModuleConfModel> getNeedGenModuleConf(GenConfImplementor provider){
        return GenConfFactory.cteateGenConfEbi(provider).getMapModuleConf().values();

    }
    public void needProxyGen(String needGenType, ModuleConfModel mouduleConf){
        GenProxyFactory.createGenProxy(needGenType,mouduleConf);
    }
    public GenInvocation getDefaultGenInvocation(String needGenType, ModuleConfModel mouduleConf){
        GenInvocation invocation= GenInvocationFactory.createGenInvocation(needGenType,mouduleConf);
        return invocation;
    }
}
