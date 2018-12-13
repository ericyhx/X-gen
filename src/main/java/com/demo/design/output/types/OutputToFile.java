package com.demo.design.output.types;

import com.demo.design.output.GenOutputEbi;

import java.util.Observable;

public class OutputToFile implements GenOutputEbi {
    @Override
    public void update(Observable o, Object obj) {
        System.out.println("now to outputTofile");

    }
}
