package com.demo.design.genconf.constants;

import lombok.Getter;

public enum  ExpressionEnum {
    DOT("."),
    SEPARATOR("/"),
    DOLLOR("$"),
    OPENBACKET("["),
    CLOSEBACKET("]"),
    EQUAL("="),
    XML("xml"),
    PROBEGINSTR("${"),
    PROENDSTR("}"),
    METHODBEGINSTR("$["),
    METHODENDSTR("]"),
    TEMPLATE("template"),
    COMMON(",");
    @Getter
    private String expr;
    private ExpressionEnum(String expr){
        this.expr=expr;
    }

}
