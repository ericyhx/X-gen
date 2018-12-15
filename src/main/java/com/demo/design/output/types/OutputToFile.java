package com.demo.design.output.types;

import com.demo.design.output.GenOutputEbi;

import java.util.Observable;

public class OutputToFile implements GenOutputEbi {
    @Override
    public void update(Observable o, Object obj) {
        //1：首先要得到要输出的内容
        String content= (String) obj;
        //2:根据配置要创建输出文件的文件夹（需要引入抽象工厂）

        //3：输出成文件到这个文件夹中去
        System.out.println("now to outputTofile");

    }
}
