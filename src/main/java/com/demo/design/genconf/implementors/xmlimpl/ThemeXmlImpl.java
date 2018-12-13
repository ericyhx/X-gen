package com.demo.design.genconf.implementors.xmlimpl;

import com.demo.design.genconf.implementors.ThemeImplementor;
import com.demo.design.genconf.implementors.xmlimpl.builder.GenConfBuilder;
import com.demo.design.genconf.implementors.xmlimpl.builder.ThemeBuilder;
import com.demo.design.genconf.util.readxml.explaindesign.Context;
import com.demo.design.genconf.util.readxml.explaindesign.ReadXmlExpression;
import com.demo.design.genconf.util.readxml.memontodesign.Parser;
import com.demo.design.genconf.vo.GenTypeModel;
import com.demo.design.genconf.vo.GenconfModel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ThemeXmlImpl implements ThemeImplementor {
    private String themeFileName;

    public ThemeXmlImpl(String themeFileName) {
        this.themeFileName = themeFileName;
    }
    @Override
    public Map<String, GenTypeModel> getMapGenType() {
        Map<String, GenTypeModel> map=new HashMap<>();
        String[] genTypeids=parseGenTypeIds(this.getContext());
        String[] genTypeClzs=parseGenTypeClzs(this.getContext());
        for (int i = 0; i < genTypeids.length; i++) {
            GenTypeModel model=new GenTypeModel();
            model.setId(genTypeids[i]);
            model.setGenTypeClz(genTypeClzs[i]);
            String[] paramIds=parseParamIds(this.getContext(),genTypeids[i]);
            String[] paramValues=parseParamValues(this.getContext(),genTypeids[i]);
            Map<String,String> paramMap=new HashMap<>();
            for (int j = 0; j < paramIds.length; j++) {
                paramMap.put(paramIds[j],paramValues[j]);
            }
            model.setMapParms(paramMap);
            map.put(model.getId(),model);
        }
        return map;
    }
    @Override
    public Map<String, String> getMapGenOutTypes() {
        Map<String, String> map=new HashMap<>();
        String[] ids=parseGenOutTypeIds(this.getContext());
        String[] types=parseGenOutTypeTypes(this.getContext());
        for (int i = 0; i < ids.length; i++) {
            map.put(ids[i],types[i]);
        }
        return map;
    }
    @Override
    public Map<String, String> getMapProviders() {
        Map<String, String> map=new HashMap<>();
        String[] ids=parseProviderIds(this.getContext());
        String[] types=parseProviderTypes(this.getContext());
        for (int i = 0; i < ids.length; i++) {
            map.put(ids[i],types[i]);
        }
        return map;
    }

    private Context getContext(){
        Context c=null;
        try {
            c=Context.getInstance(new GenConfBuilder().addOtherValue(themeFileName).build());
        }catch (Exception e){
            e.printStackTrace();
        }
        return c;
    }
    private String[] parseParamValues(Context ctx,String genTypeId) {
        ctx.init();
        ReadXmlExpression re= Parser.parse(new ThemeBuilder().addTheme().addSeparator()
                .addGenTypes().addSeparator()
                .addGenType().addOpenbacket().addId().addEqual().addOtherValue(genTypeId).addClosebacket().addSeparator()
                .addParams().addDollar().addSeparator()
                .addParam().addDollar().build());
        return re.interpret(ctx);
    }

    private String[] parseParamIds(Context ctx,String genTypeId) {
        ctx.init();
        ReadXmlExpression re= Parser.parse(new ThemeBuilder().addTheme().addSeparator()
                .addGenTypes().addSeparator()
                .addGenType().addOpenbacket().addId().addEqual().addOtherValue(genTypeId).addClosebacket().addSeparator()
                .addParams().addDollar().addSeparator()
                .addParam().addDollar().addDot().addId().addDollar().build());
        return re.interpret(ctx);
    }

    private String[] parseGenTypeClzs(Context ctx) {
        ctx.init();
        ReadXmlExpression re= Parser.parse(new ThemeBuilder().addTheme().addSeparator()
                .addGenTypes().addSeparator()
                .addGenType().addDollar().addDot().addType().addDollar().build());
        return re.interpret(ctx);
    }

    private String[] parseGenTypeIds(Context ctx) {
        ctx.init();
        ReadXmlExpression re= Parser.parse(new ThemeBuilder().addTheme().addSeparator()
                .addGenTypes().addSeparator()
                .addGenType().addDollar().addDot().addId().addDollar().build());
        return re.interpret(ctx);
    }

    private String[] parseGenOutTypeIds(Context ctx) {
        ctx.init();
        ReadXmlExpression re= Parser.parse(new ThemeBuilder().addTheme().addSeparator()
                .addGenOutTypes().addSeparator()
                .addGenOutType().addDollar().addDot().addId().addDollar().build());
        return re.interpret(ctx);
    }

    private String[] parseGenOutTypeTypes(Context ctx) {
        ctx.init();
        ReadXmlExpression re= Parser.parse(new ThemeBuilder().addTheme().addSeparator()
                .addGenOutTypes().addSeparator()
                .addGenOutType().addDollar().addDot().addType().addDollar().build());
        return re.interpret(ctx);
    }
    private String[] parseProviderIds(Context ctx) {
        ctx.init();
        ReadXmlExpression re= Parser.parse(new ThemeBuilder().addTheme().addSeparator()
                .addProviders().addSeparator()
                .addProvider().addDollar().addDot().addId().addDollar().build());
        return re.interpret(ctx);
    }

    private String[] parseProviderTypes(Context ctx) {
        ctx.init();
        ReadXmlExpression re= Parser.parse(new ThemeBuilder().addTheme().addSeparator()
                .addProviders().addSeparator()
                .addProvider().addDollar().addDot().addType().addDollar().build());
        return re.interpret(ctx);
    }
}
