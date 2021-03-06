package com.demo.design.genconf.implementors.xmlimpl;

import com.demo.design.genconf.constants.ExpressionEnum;
import com.demo.design.genconf.implementors.ModuleGenConfImplementor;
import com.demo.design.genconf.implementors.dynamicparse.ParseContext;
import com.demo.design.genconf.implementors.xmlimpl.builder.GenConfBuilder;
import com.demo.design.genconf.implementors.xmlimpl.builder.ModuleGenConfBuilder;
import com.demo.design.genconf.util.readxml.explaindesign.Context;
import com.demo.design.genconf.util.readxml.explaindesign.ReadXmlExpression;
import com.demo.design.genconf.util.readxml.memontodesign.Parser;
import com.demo.design.genconf.vo.ExtendConfModel;
import com.demo.design.genconf.vo.GenconfModel;
import com.demo.design.genconf.vo.ModuleConfModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleGenConfXmlImpl implements ModuleGenConfImplementor {

    @Setter@Getter
    private String moduleFileName;

    public ModuleGenConfXmlImpl() {
    }
    @Override
    public void setModuleFileName(String name) {
        this.moduleFileName=name;
    }
    @Override
    public ModuleConfModel getBaseModuleConfModel() {
        ModuleConfModel mcm=new ModuleConfModel();
        //设置模块标识
        parseModuleId(mcm,this.getContext());
        return mcm;
    }

    @Override
    public Map<String, List<String>> getMapNeedGenTypes() {
        Map<String, List<String>> map=new HashMap<>();
        String[] needGenIds=parseNeedGenIds(this.getContext());
        for (int i = 0; i < needGenIds.length; i++) {
            map.put(needGenIds[i],parseNeedGenOutType(this.getContext()));
        }
        return map;
    }
    @Override
    public Map<String, ExtendConfModel> getMapExtends(GenconfModel gm) {
        Map<String, ExtendConfModel> map=new HashMap<>();
        String[] extendids=parseExtendIds(this.getContext());
        String[] isSingles=parseIsSingles(this.getContext());
        String[] values=parseExendValues(this.getContext());
        for (int i = 0; i < extendids.length; i++) {
            ExtendConfModel model=new ExtendConfModel();
            model.setId(extendids[i]);
            model.setSingle(Boolean.parseBoolean(isSingles[i]));
            model.setValue(values[i]);
            if(!model.isSingle()){
                model.setValues(model.getValue().split(ExpressionEnum.COMMON.getExpr()));
            }
            map.put(extendids[i],model);
        }
        //等读取完成后，再来进行动态的解析
        ParseContext pctx=new ParseContext();
        pctx.parse(gm,map);
        return map;
    }

    private Context getContext(Map<String, String> param){
        Context c=null;
        try {
            c=Context.getInstance(new GenConfBuilder().addOtherValue(param.get(moduleFileName)).addDot().addXml().build());
        }catch (Exception e){
            e.printStackTrace();
        }
        return c;
    }
    private Context getContext(){
        Context c=null;
        try {
            c=Context.getInstance(new GenConfBuilder().addOtherValue(moduleFileName).build());
        }catch (Exception e){
            e.printStackTrace();
        }
        return c;
    }
    private void parseModuleId(ModuleConfModel mcm, Context ctx){
        ctx.init();
        ReadXmlExpression re= Parser.parse(new ModuleGenConfBuilder().addModuleGenConf().addDot().addId().build());
        String[] ss = re.interpret(ctx);
        mcm.setModelId(ss[0]);
    }

    private List<String> parseNeedGenOutType(Context ctx) {
        ctx.init();
        ReadXmlExpression re=Parser.parse(new ModuleGenConfBuilder().addModuleGenConf().addSeparator()
                .addNeedGenTypes().addSeparator().addNeedGenType().addDollar().addSeparator()
                .addNeedGenOutType().addDollar().addDot().addId().addDollar().build());
        return Arrays.asList(re.interpret(ctx));
    }

    private String[] parseNeedGenIds(Context ctx) {
        ctx.init();
        ReadXmlExpression re=Parser.parse(new ModuleGenConfBuilder().addModuleGenConf().addSeparator()
        .addNeedGenTypes().addSeparator().addNeedGenType().addDollar().addDot().addId().addDollar().build());
        return re.interpret(ctx);
    }
    private String[] parseExendValues(Context ctx) {
        ctx.init();
        ReadXmlExpression re=Parser.parse(new ModuleGenConfBuilder().addModuleGenConf().addSeparator()
                .addExtendConfs().addSeparator().addExtendConf().addDollar().build());
        return re.interpret(ctx);
    }

    private String[] parseIsSingles(Context ctx) {
        ctx.init();
        ReadXmlExpression re=Parser.parse(new ModuleGenConfBuilder().addModuleGenConf().addSeparator()
                .addExtendConfs().addSeparator().addExtendConf().addDollar().addDot().addIsSingle().addDollar().build());
        return re.interpret(ctx);
    }

    private String[] parseExtendIds(Context ctx) {
        ctx.init();
        ReadXmlExpression re=Parser.parse(new ModuleGenConfBuilder().addModuleGenConf().addSeparator()
                .addExtendConfs().addSeparator().addExtendConf().addDollar().addDot().addId().addDollar().build());
        return re.interpret(ctx);
    }
}
