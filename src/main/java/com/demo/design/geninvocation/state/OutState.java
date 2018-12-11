package com.demo.design.geninvocation.state;

import com.demo.design.geninvocation.DefaultGenInvocation;
import com.demo.design.mediator.CoreMediator;
import com.demo.design.template.DefaultTemplateEbo;

public class OutState implements State {
    @Override
    public void doWork(DefaultGenInvocation ctx) {
        //把内容输出出去

        //1：注册观察者，需要把需要输出的类型当成观察者，并注册到obserable里面
//        CoreMediator.getInstance().registerObservers(ctx);
        //2:通知观察者
        ctx.setContentOver(ctx.getTempContent());
        //设置后续state，现在还没有
        System.out.println("over content:"+((DefaultTemplateEbo)ctx.getTempContent()).getNowContent());
    }
}
