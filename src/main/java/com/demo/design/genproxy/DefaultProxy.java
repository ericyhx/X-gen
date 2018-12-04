package com.demo.design.genproxy;

import com.demo.design.geninvocation.GenInvocation;

/**
 * 生成代理，其实是一个介于分发调度和具体生成之间的额外的附加层
 * 目的：为了今后可以根据需要切换不同的具体实现
 */
public class DefaultProxy implements GenInvocation {
    /**
     * 持有的真正执行generate的对象
     */
    private GenInvocation invocation=null;

    public DefaultProxy(GenInvocation invocation) {
        this.invocation = invocation;
    }

    @Override
    public void executeGen() {
        //代理访问真正的invocation
        this.invocation.executeGen();
    }
}
