package com.demo.design.state;


import com.demo.design.geninvocation.DefaultGenInvocation;
import com.demo.design.mediator.CoreMediator;

public class GenState implements State {
    @Override
    public void doWork(DefaultGenInvocation ctx) {
        //首先实现自己的业务功能
        //1:获取每个theme对应的Action
        String clzName= CoreMediator.getInstance().getNeedGenTypeClz(ctx.getMouduleConf(),ctx.getNeedGenType());
        //2：调用Action来获得生成的内容
//        Class.forName(clzName).newInstance();

        //设置下一个state
        ctx.setState(new OutState());
        ctx.doWork();
    }
}
