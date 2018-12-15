package com.demo.design.output.types;

import com.demo.design.mediator.CoreMediator;
import com.demo.design.output.GenOutputEbi;
import com.demo.design.output.types.outputtofile.AbstractFactory;
import com.demo.design.output.types.outputtofile.plaintxt.PlainTxtFactory;

import java.util.Observable;

public class OutputToFile implements GenOutputEbi {
    @Override
    public void update(Observable o, Object obj) {
        //1：首先要得到要输出的内容
        String content= (String) obj;
        //2:根据配置要创建输出文件的文件夹（需要引入抽象工厂）
        AbstractFactory af=new PlainTxtFactory();
        af.createGenOutPathPackage().genPackage(
                CoreMediator.getInstance().getObserverModuleConf(o),
                CoreMediator.getInstance().getObserverGenTypeId(o));
        //3：输出成文件到这个文件夹中去
        //3.1得到文件的路径和文件名
        final String genFilePathAndName = af.createGenOutPathPackage().getOutPathAndFileName(
                CoreMediator.getInstance().getObserverModuleConf(o),
                CoreMediator.getInstance().getObserverGenTypeId(o)
        );
        //3.2 使用哦utter对象输出
        af.createOutter().writeContent(genFilePathAndName,content);
        System.out.println("now to outputTofile");

    }
}
