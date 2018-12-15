package com.demo.design.output.types.outputtofile.plaintxt;

import com.demo.design.genconf.constants.ExpressionEnum;
import com.demo.design.genconf.util.file.FileHelper;
import com.demo.design.genconf.vo.ModuleConfModel;
import com.demo.design.mediator.CoreMediator;
import com.demo.design.output.types.outputtofile.GenOutPathPackage;

public class GenOutPathPackageImpl implements GenOutPathPackage {
    @Override
    public boolean genPackage(ModuleConfModel moduleConf, String genTypeId) {
        String relativePath= CoreMediator.getInstance().getGenTypeParams(moduleConf,genTypeId).get("relativePath");
        String packagePath=moduleConf.getMapExtends().get("modulePackage").getValue();
        String dirPackage=packagePath+ ExpressionEnum.DOT.getExpr()+relativePath;

        String packages=dirPackage.replace(ExpressionEnum.DOT.getExpr(),ExpressionEnum.SEPARATOR.getExpr());
        FileHelper.genDir(packages);
        return false;
    }

    @Override
    public String getOutPathAndFileName(ModuleConfModel moduleConf, String genTypeId) {
        return null;
    }
}
