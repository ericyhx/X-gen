package com.demo.design.genconf.implementors.dynamicparse;

import com.demo.design.genconf.vo.ExtendConfModel;
import com.demo.design.genconf.vo.GenconfModel;

import java.util.Map;

public class PropertyReplaceStrategy implements ParseStrategy {
    @Override
    public String parseDynamicContent(GenconfModel gm, Map<String, ExtendConfModel> mapEcms, String expr) {
        String retStr="";
        ExtendConfModel ecm=mapEcms.get(expr);
        if(ecm!=null){
            retStr=ecm.getValue();
        }
        return retStr;
    }
}
