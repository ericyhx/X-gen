package com.demo.design.genproxy;

import com.demo.design.geninvocation.GenInvocation;

public class GenProxyFactory {
    private GenProxyFactory() {
    }
    public static GenInvocation createGenProxy(){
        //创建真正执行generate的对象，也就是被代理的对象，现在是默认使用本地的
        GenInvocation invocation=null;
        //创建代理对象
        return new DefaultProxy(invocation);
    }
}
