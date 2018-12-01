package com.demo.design.genconf;

/**
 * 采用简单工程模式
 */
public class GenConfFactory {
    private GenConfFactory(){}
    public static GenConfEbi cteateGenConfEbi(){
        return GenConfEbo.getInstance();
    }
}
