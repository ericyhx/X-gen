package com.demo.design.genconf.implementors.dynamicparse;

import bsh.Interpreter;
import com.demo.design.genconf.vo.ExtendConfModel;
import com.demo.design.genconf.vo.GenconfModel;

import java.util.Map;

public class BeanShellStrategy implements ParseStrategy {
    @Override
    public String parseDynamicContent(GenconfModel gm, Map<String, ExtendConfModel> mapEcms, String expr) {
        Interpreter interpreter=new Interpreter();
        String retStr="";
        //设置要传入的参数
        try {
            interpreter.set("gm",gm);
            interpreter.set("mapEcms",mapEcms);
            //执行
            interpreter.eval("retValue="+expr);
            //获取返回值
            retStr=interpreter.get("retValue").toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return retStr;
    }
}
