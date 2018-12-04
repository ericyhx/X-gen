package com.demo.design.geninvocation;

import com.demo.design.genconf.vo.ModuleConfModel;

public class GenInvocationFactory {
    private GenInvocationFactory() {
    }
    public static GenInvocation createGenInvocation(String needGenType, ModuleConfModel mouduleConf){
        return new DefaultGenInvocation(needGenType,mouduleConf);
    }
}
