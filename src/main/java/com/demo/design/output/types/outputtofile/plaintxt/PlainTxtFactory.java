package com.demo.design.output.types.outputtofile.plaintxt;

import com.demo.design.output.types.outputtofile.AbstractFactory;
import com.demo.design.output.types.outputtofile.GenOutPathPackage;
import com.demo.design.output.types.outputtofile.Outter;

/**
 * @Description:
 * @author: yuhongxi
 * @date:2018/12/16
 */
public class PlainTxtFactory implements AbstractFactory {

    @Override
    public GenOutPathPackage createGenOutPathPackage() {
        return new GenOutPathPackageImpl();
    }

    @Override
    public Outter createOutter() {
        return new OutterImpl();
    }
}
