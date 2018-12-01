package com.demo.design.genconf.implementors.xmlimpl.builder;

import com.demo.design.genconf.constants.ExpressionEnum;
import com.demo.design.genconf.constants.ThemeEnum;

public class ThemeBuilder {
    private StringBuffer buffer=new StringBuffer();
    public String build(){
        return buffer.toString();
    }
    public ThemeBuilder addTheme(){
        buffer.append(ThemeEnum.Theme);
        return this;
    }
    public ThemeBuilder addGenTypes(){
        buffer.append(ThemeEnum.GenTypes);
        return this;
    }
    public ThemeBuilder addGenType(){
        buffer.append(ThemeEnum.GenType);
        return this;
    }
    public ThemeBuilder addParams(){
        buffer.append(ThemeEnum.Params);
        return this;
    }
    public ThemeBuilder addParam(){
        buffer.append(ThemeEnum.Param);
        return this;
    }
    public ThemeBuilder addGenOutTypes(){
        buffer.append(ThemeEnum.GenOutTypes);
        return this;
    }
    public ThemeBuilder addGenOutType(){
        buffer.append(ThemeEnum.GenOutType);
        return this;
    }
    public ThemeBuilder addProviders(){
        buffer.append(ThemeEnum.Providers);
        return this;
    }
    public ThemeBuilder addProvider(){
        buffer.append(ThemeEnum.Provider);
        return this;
    }
    public ThemeBuilder addType(){
        buffer.append(ThemeEnum.type);
        return this;
    }

    public ThemeBuilder addOtherValue(String str) {
        buffer.append(str);
        return this;
    }

    public ThemeBuilder addId() {
        buffer.append(ThemeEnum.id.name());
        return this;
    }

    public ThemeBuilder addDot() {
        buffer.append(ExpressionEnum.DOT.getExpr());
        return this;
    }

    public ThemeBuilder addXml() {
        buffer.append(ExpressionEnum.XML.getExpr());
        return this;
    }

    public ThemeBuilder addSeparator() {
        buffer.append(ExpressionEnum.SEPARATOR.getExpr());
        return this;
    }

    public ThemeBuilder addDollar() {
        buffer.append(ExpressionEnum.DOLLOR.getExpr());
        return this;
    }

    public ThemeBuilder addOpenbacket() {
        buffer.append(ExpressionEnum.OPENBACKET.getExpr());
        return this;
    }

    public ThemeBuilder addClosebacket() {
        buffer.append(ExpressionEnum.CLOSEBACKET.getExpr());
        return this;
    }

    public ThemeBuilder addEqual() {
        buffer.append(ExpressionEnum.EQUAL.getExpr());
        return this;
    }

}
