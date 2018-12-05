package com.demo.design.state;

import com.demo.design.geninvocation.DefaultGenInvocation;

public class OutState implements State {
    @Override
    public void doWork(DefaultGenInvocation ctx) {
        //把内容输出出去

        //设置后续state，现在还没有
    }
}
