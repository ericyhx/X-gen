package com.demo.design.geninvocation.state;


import com.demo.design.geninvocation.DefaultGenInvocation;

/**
 * 引入状态模式
 * 公共的状态的接口
 */
public interface State {
    /**
     * 执行状态所对应的功能处理
     * @param ctx 上下文实例对象
     */
    public void doWork(DefaultGenInvocation ctx);
}
