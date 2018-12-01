package com.demo.design.genconf.implementors.xmlimpl.builder;

import com.demo.design.genconf.constants.ExpressionEnum;
import com.demo.design.genconf.constants.GenConfEnum;

public class GenConfBuilder{
    private StringBuffer buffer=new StringBuffer();
    public String build(){
        return buffer.toString();
    }

    public GenConfBuilder addXmlFilePre() {
        buffer.append(GenConfEnum.GenConf.name());
        return this;
    }

    public GenConfBuilder addGenConf() {
        buffer.append(GenConfEnum.GenConf.name());
        return this;

    }
    public GenConfBuilder addNeedGens() {
        buffer.append(GenConfEnum.NeedGens.name());
        return this;

    }
    public GenConfBuilder addNeedGen() {
        buffer.append(GenConfEnum.NeedGen.name());
        return this;

    }
    public GenConfBuilder addParams() {
        buffer.append(GenConfEnum.Params.name());
        return this;

    }
    public GenConfBuilder addParam() {
        buffer.append(GenConfEnum.Param.name());
        return this;

    }
    public GenConfBuilder addContants() {
        buffer.append(GenConfEnum.Contants.name());
        return this;

    }
    public GenConfBuilder addContant() {
        buffer.append(GenConfEnum.Constant.name());
        return this;

    }

    public GenConfBuilder addThemes() {
        buffer.append(GenConfEnum.Themes.name());
        return this;

    }
    public GenConfBuilder addTheme() {
        buffer.append(GenConfEnum.Theme.name());
        return this;

    }
    public GenConfBuilder addThemeId() {
        buffer.append(GenConfEnum.themeId.name());
        return this;

    }
    public GenConfBuilder addProvider() {
        buffer.append(GenConfEnum.provider.name());
        return this;

    }
    public GenConfBuilder addOtherValue(String str){
        buffer.append(str);
        return this;
    }

    public GenConfBuilder addId() {
        buffer.append(GenConfEnum.id.name());
        return this;
    }
    public GenConfBuilder addDot() {
        buffer.append(ExpressionEnum.DOT.getExpr());
        return this;
    }
    public GenConfBuilder addXml() {
        buffer.append(ExpressionEnum.XML.getExpr());
        return this;
    }
    public GenConfBuilder addSeparator(){
        buffer.append(ExpressionEnum.SEPARATOR.getExpr());
        return this;
    }
    public GenConfBuilder addDollar(){
        buffer.append(ExpressionEnum.DOLLOR.getExpr());
        return this;
    }
    public GenConfBuilder addOpenbacket(){
        buffer.append(ExpressionEnum.OPENBACKET.getExpr());
        return this;
    }
    public GenConfBuilder addClosebacket(){
        buffer.append(ExpressionEnum.CLOSEBACKET.getExpr());
        return this;
    }
    public GenConfBuilder addEqual(){
        buffer.append(ExpressionEnum.EQUAL.getExpr());
        return this;
    }
}
