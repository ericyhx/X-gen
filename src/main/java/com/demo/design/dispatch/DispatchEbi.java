package com.demo.design.dispatch;

import com.demo.design.genconf.implementors.GenConfImplementor;

public interface DispatchEbi {
    /**
     * 提供诶外部使用的方法，客户端通过这个方法来请求生成器运行
     */
    public void generate();

    /**
     * 提供诶外部使用的方法，客户端通过这个方法来请求生成器运行
     * 生成所需要的配置数据，从传入的获取配置数据的实现中获得
     * @param provider
     */
    public void generate(GenConfImplementor provider);
}
