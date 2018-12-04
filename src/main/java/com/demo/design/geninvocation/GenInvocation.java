package com.demo.design.geninvocation;

/**
 * proxy要实现的接口
 * 本地默认要实现的接口
 */
public interface GenInvocation {
    /**
     * 只是一个触发，执行真正的generate
     */
    public void executeGen();
}
