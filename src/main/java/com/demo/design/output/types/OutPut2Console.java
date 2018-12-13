package com.demo.design.output.types;

import com.demo.design.output.GenOutputEbi;

import java.util.Observable;

public class OutPut2Console implements GenOutputEbi {
    @Override
    public void update(Observable o, Object obj) {
        String content= (String) obj;
        System.out.println("now content="+content);
        System.out.println("========================");
    }
}
