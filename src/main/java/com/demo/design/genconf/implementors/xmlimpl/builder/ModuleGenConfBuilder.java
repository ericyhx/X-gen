package com.demo.design.genconf.implementors.xmlimpl.builder;

import com.demo.design.genconf.constants.ExpressionEnum;
import com.demo.design.genconf.constants.GenConfEnum;
import com.demo.design.genconf.constants.ModuleGenConfEnum;

public class ModuleGenConfBuilder {
    private StringBuffer buffer = new StringBuffer();
    public String build(){
        return buffer.toString();
    }
    public ModuleGenConfBuilder addModuleGenConf() {
        buffer.append(ModuleGenConfEnum.ModuleGenConf);
        return this;
    }

    public ModuleGenConfBuilder addNeedGenTypes() {
        buffer.append(ModuleGenConfEnum.NeedGenTypes);
        return this;
    }

    public ModuleGenConfBuilder addNeedGenType() {
        buffer.append(ModuleGenConfEnum.NeedGenType);
        return this;
    }

    public ModuleGenConfBuilder addNeedGenOutType() {
        buffer.append(ModuleGenConfEnum.NeedGenOutType);
        return this;
    }

    public ModuleGenConfBuilder addExtendConfs() {
        buffer.append(ModuleGenConfEnum.ExtendConfs);
        return this;
    }

    public ModuleGenConfBuilder addExtendConf() {
        buffer.append(ModuleGenConfEnum.ExtendConf);
        return this;
    }

    public ModuleGenConfBuilder addIsSingle() {
        buffer.append(ModuleGenConfEnum.isSingle);
        return this;
    }

    public ModuleGenConfBuilder addOtherValue(String str) {
        buffer.append(str);
        return this;
    }

    public ModuleGenConfBuilder addId() {
        buffer.append(ModuleGenConfEnum.id.name());
        return this;
    }

    public ModuleGenConfBuilder addDot() {
        buffer.append(ExpressionEnum.DOT.getExpr());
        return this;
    }

    public ModuleGenConfBuilder addXml() {
        buffer.append(ExpressionEnum.XML.getExpr());
        return this;
    }

    public ModuleGenConfBuilder addSeparator() {
        buffer.append(ExpressionEnum.SEPARATOR.getExpr());
        return this;
    }

    public ModuleGenConfBuilder addDollar() {
        buffer.append(ExpressionEnum.DOLLOR.getExpr());
        return this;
    }

    public ModuleGenConfBuilder addOpenbacket() {
        buffer.append(ExpressionEnum.OPENBACKET.getExpr());
        return this;
    }

    public ModuleGenConfBuilder addClosebacket() {
        buffer.append(ExpressionEnum.CLOSEBACKET.getExpr());
        return this;
    }

    public ModuleGenConfBuilder addEqual() {
        buffer.append(ExpressionEnum.EQUAL.getExpr());
        return this;
    }
}
