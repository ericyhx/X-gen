package com.demo.design.geninvocation;

import com.demo.design.genconf.vo.ModuleConfModel;

/**
 * 本地默认具体实现
 */
public class DefaultGenInvocation implements GenInvocation {
    /**
     * 具体要生成的类型标识
     */
    private String needGenType;
    /**
     * 当前被generate模块的配置数据
     */
    private ModuleConfModel mouduleConf;

    public DefaultGenInvocation(String needGenType, ModuleConfModel mouduleConf) {
        this.needGenType = needGenType;
        this.mouduleConf = mouduleConf;
    }

    @Override
    public void executeGen() {
        //第一大步：按照一定流程调用相应的生成实现，获取生成的内容

        //第二大步：把生成的内容输出出去
    }
}
