package com.demo.design.genconf;

import com.demo.design.genconf.implementors.GenConfImplementor;

/**
 * 采用简单工程模式
 */
public class GenConfFactory {
    private GenConfFactory(){}
    public static GenConfEbi cteateGenConfEbi(GenConfImplementor genConfImpl){
        return GenConfEbo.getInstance(genConfImpl);
    }

    /**
     * 创建访问核心配置的接口对象，前提是确保已经获取了配置数据，这个方法才能生效
     * @return
     */
    public static GenConfEbi cteateGenConfEbi(){
        return cteateGenConfEbi(null);
    }
}
