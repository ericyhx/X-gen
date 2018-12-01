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
}
