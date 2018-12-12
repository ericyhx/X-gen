package com.demo.design.themes.simple.visitors;

import com.demo.design.genconf.vo.ModuleConfModel;
import com.demo.design.template.visitor.TemplateElement;
import com.demo.design.template.visitor.Visitor;

public class GetterAndSetter implements Visitor {
    private String name;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    @Override
    public Object visitTemplateElement(TemplateElement element) {
        ModuleConfModel moduleConf=element.getModuleConf();
        return genProperties(moduleConf,"voFields","voFieldsTypes");
    }
    private static String genProperties(ModuleConfModel moduleConf,String fieldsName,String fieldsTypeName){
        final String[] voFields = moduleConf.getMapExtends().get(fieldsName).getValues();
        final String[] voFieldsTypes = moduleConf.getMapExtends().get(fieldsTypeName).getValues();
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < voFields.length; i++) {
            sb.append("private "+voFieldsTypes[i]+" "+voFields[i]+";\n\t");
//            voFields[i].r
            sb.append("public "+voFieldsTypes[i]+" get"+voFields[i]+"() { return "+voFields[i]+"; }");
        }
        return sb.toString();
    }
}
