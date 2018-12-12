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
            String temp = (voFields[i].substring(0, 1)).toUpperCase().concat(voFields[i].substring(1));
            sb.append("public "+voFieldsTypes[i]+" get"+temp+"() { return "+voFields[i]+"; }\n\t");
            sb.append("public void set"+temp+"(String "+voFields[i]+") { this."+voFields[i]+" = "+voFields[i]+"; }\n\t");
        }
        return sb.toString();
    }
}
