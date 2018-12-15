package com.demo.design.output.types.outputtofile.plaintxt;

import com.demo.design.genconf.constants.ExpressionEnum;
import com.demo.design.genconf.util.file.FileHelper;
import com.demo.design.genconf.vo.ModuleConfModel;
import com.demo.design.mediator.CoreMediator;
import com.demo.design.output.types.outputtofile.GenOutPathPackage;

public class GenOutPathPackageImpl implements GenOutPathPackage {
    @Override
    public boolean genPackage(ModuleConfModel moduleConf, String genTypeId) {
        FileHelper.genDir(this.getDirPath(moduleConf,genTypeId));
        return false;
    }
    private String getDirPath(ModuleConfModel moduleConf, String genTypeId){
        String codeOutPath=moduleConf.getMapExtends().get("codeOutPath").getValue();
        String relativePath= CoreMediator.getInstance().getGenTypeParams(moduleConf,genTypeId).get("relativePath");
        String packagePath=moduleConf.getMapExtends().get("modulePackage").getValue();
        String dirPackage=codeOutPath+ExpressionEnum.DOT.getExpr()+packagePath+ ExpressionEnum.DOT.getExpr()+relativePath;
        String packages=dirPackage.replace(ExpressionEnum.DOT.getExpr(),ExpressionEnum.SEPARATOR.getExpr());

        return packages;
    }

    @Override
    public String getOutPathAndFileName(ModuleConfModel moduleConf, String genTypeId) {
        String preName=CoreMediator.getInstance().getGenTypeParams(moduleConf,genTypeId).get("preGenFileName");
        String afterName=CoreMediator.getInstance().getGenTypeParams(moduleConf,genTypeId).get("afterGenFileName");
        String moduleNameSuperCase=moduleConf.getMapExtends().get("moduleNameSuperCase").getValue();
        String retName=this.getDirPath(moduleConf,genTypeId)
                +ExpressionEnum.SEPARATOR.getExpr()
                +preName
                +moduleNameSuperCase
                +afterName;
        return retName;
    }
}
