package com.demo.design.template.flyweight;

import com.demo.design.genconf.constants.ExpressionEnum;
import com.demo.design.genconf.vo.ModuleConfModel;
import com.demo.design.template.visitor.TemplateElement;
import com.demo.design.template.visitor.Visitor;

public class DefaultTemplate implements TemplateFlyweight {
    /**
     * 模板的原始内容
     */
    private String rawContent;

    protected DefaultTemplate(String rawContent) {
        this.rawContent = rawContent;
    }

    @Override
    public Object replaceProperties(ModuleConfModel moduleConf, Object content) {
        return this.nowReplaceProperties(moduleConf,content);
    }

    @Override
    public Object replaceMethods(ModuleConfModel moduleConf, Object content) {
        return this.nowReplaceMethods(moduleConf,content);
    }

    @Override
    public Object getRawContent() {
        return this.rawContent;
    }
    private Object nowReplaceMethods(ModuleConfModel moduleConf,Object content){
        //1:读取到相应的模板的原始内容
        String templateContent= (String) content;
        int begin=templateContent.indexOf(ExpressionEnum.METHODBEGINSTR.getExpr());
        if(begin>0) {
            int end = begin + ExpressionEnum.METHODBEGINSTR.getExpr().length() + templateContent.substring(begin + ExpressionEnum.METHODBEGINSTR.getExpr().length()).indexOf(ExpressionEnum.METHODENDSTR.getExpr());
            //2:分解模板的原始内容，得到需要替换处理的method
            String className=templateContent.substring(begin+ExpressionEnum.METHODBEGINSTR.getExpr().length(),end);
            //3:运行method获取到结果值
            String methodValue= (String) callMethodVisitor(className,moduleConf);
            //4:把这个值替换到模板当中的相应的位置去
            templateContent=templateContent.replace(ExpressionEnum.METHODBEGINSTR.getExpr()+className+ExpressionEnum.METHODENDSTR.getExpr(),methodValue);
            //5:一直替换到模板内容里面没有可替换的内容了，那么久处理好了
            //递归
            templateContent= (String) nowReplaceMethods(moduleConf,templateContent);
        }
        return templateContent;

    }
    private Object callMethodVisitor(String className,ModuleConfModel moduleConf){
        Object ret=null;
        try {
            Visitor v=(Visitor) Class.forName(className).newInstance();
            final TemplateElement ele = new TemplateElement(moduleConf);
            //接受访问者访问
            ret=ele.accept(v);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    private Object nowReplaceProperties(ModuleConfModel moduleConf,Object content){
        //1:读取到相应的模板的原始内容
        String templateContent= (String) content;
        //2:分解模板的原始内容，得到需要替换处理的property
        int begin=templateContent.indexOf(ExpressionEnum.PROBEGINSTR.getExpr());
        if(begin>0){
            int end=begin+ExpressionEnum.PROBEGINSTR.getExpr().length()+templateContent.substring(begin+ExpressionEnum.PROBEGINSTR.getExpr().length()).indexOf(ExpressionEnum.PROENDSTR.getExpr());
            String prop=templateContent.substring(begin+ExpressionEnum.PROBEGINSTR.getExpr().length(),end);
            //3:从moduleConfModel得到相应的property的值
            final String value = moduleConf.getMapExtends().get(prop).getValue();
            //4:把这个值替换到模板当中的相应的位置去
            templateContent=templateContent.replace(ExpressionEnum.PROBEGINSTR.getExpr()+prop+ExpressionEnum.PROENDSTR.getExpr(),value);
            //5:一直替换到模板内容里面没有可替换的内容了，那么久处理好了
            //递归
            templateContent= (String) nowReplaceProperties(moduleConf,templateContent);
        }
        return content;
    }
}
