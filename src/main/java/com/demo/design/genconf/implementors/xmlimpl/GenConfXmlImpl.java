package com.demo.design.genconf.implementors.xmlimpl;

import com.demo.design.genconf.implementors.GenConfImplementor;
import com.demo.design.genconf.implementors.xmlimpl.builder.GenConfBuilder;
import com.demo.design.genconf.util.readxml.explaindesign.Context;
import com.demo.design.genconf.vo.NeedGenModel;
import com.demo.design.genconf.vo.ThemeModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.demo.design.genconf.util.readxml.memontodesign.Parser.parse;

public class GenConfXmlImpl implements GenConfImplementor {
    //通过parser去获取到配置的数据，然后把这些数据组装成GenConfImplementor所需要的样子
    @Override
    public List<NeedGenModel> getNeedGens() {
        return readNeedGen();
    }
    @Override
    public List<ThemeModel> getThemes() {

        return readTheme();
    }
    @Override
    public Map<String, String> getMapConstants() {
        return readMapConstants();
    }


    private Context getContext(){
        Context c=null;
        try {
            c=Context.getInstance(new GenConfBuilder().addGenConf().addDot().addXml().build());
        }catch (Exception e){
            e.printStackTrace();
        }
        return c;
    }

    private Map<String, String> readMapConstants() {
        Map<String,String> map=new HashMap<>();
        Context c=this.getContext();
        String[] ids=parseConstantsIds(c);
        String[] values=parseConstantsValues(c);
        for (int i = 0; i < ids.length; i++) {
            map.put(ids[i],values[i]);
        }
        return map;
    }

    private String[] parseConstantsValues(Context c) {
        String path = new GenConfBuilder().addGenConf().addSeparator().addContants().addSeparator().addContant().addDollar().build();
        return parse(path).interpret(c);
    }

    private String[] parseConstantsIds(Context c) {
        String path = new GenConfBuilder().addGenConf().addSeparator().addContants().addSeparator().addContant().addDollar().addDot().addId().addDollar().build();
        return parse(path).interpret(c);
    }
    private List<ThemeModel> readTheme() {
        Context c=this.getContext();
        List<ThemeModel> models=new ArrayList<>();
        String[] ids=parseThemeIds(c);
        String[] location=parseThemeLoc(c);
        for (int i = 0; i < ids.length; i++) {
            ThemeModel model=new ThemeModel();
            model.setId(ids[i]);
            model.setLocation(location[i]);
            models.add(model);
        }
        return models;
    }

    private String[] parseThemeLoc(Context c) {
        String path = new GenConfBuilder().addGenConf().addSeparator().addThemes().addSeparator().addTheme().addDollar().build();
        return parse(path).interpret(c);
    }

    private String[] parseThemeIds(Context c) {
        String path = new GenConfBuilder().addGenConf().addSeparator().addThemes().addSeparator().addTheme().addDollar().addDot().addId().addDollar().build();
        return parse(path).interpret(c);
    }


    private List<NeedGenModel> readNeedGen() {
        List<NeedGenModel> models=new ArrayList<>();
        Context c=this.getContext();
        String[] ids=parseIds(c);
        String[] providers=parseProviders(c);
        String[] themeIds=parseThemes(c);
        for (int i = 0; i < ids.length; i++) {
            NeedGenModel model=new NeedGenModel();
            model.setId(ids[i]);
            model.setTheme(themeIds[i]);
            model.setProvider(providers[i]);
            model.setMapParams(parseParams(c,ids[i]));
            models.add(model);
        }
        return models;
    }

    private String[] parseThemes(Context c) {
        String path = new GenConfBuilder().addGenConf().addSeparator().addNeedGens().addSeparator().addNeedGen().addDollar().addDot().addThemeId().addDollar().build();
        return parse(path).interpret(c);
    }

    private String[] parseProviders(Context c) {
        String path = new GenConfBuilder().addGenConf().addSeparator().addNeedGens().addSeparator().addNeedGen().addDollar().addDot().addProvider().addDollar().build();
        return parse(path).interpret(c);
    }

    private String[] parseIds(Context c) {
        String path = new GenConfBuilder().addGenConf().addSeparator().addNeedGens().addSeparator().addNeedGen().addDollar().addDot().addId().addDollar().build();
        return parse(path).interpret(c);
    }
    private String[] parseParamsValues(Context c,String needGenId) {
        String path = new GenConfBuilder().addGenConf().addSeparator().addNeedGens().addSeparator().addNeedGen().addOpenbacket().addId().addEqual()
                .addOtherValue(needGenId).addClosebacket().addSeparator().addParams().addDollar().addSeparator().addParam().addDollar().build();
        System.out.println(path);
        return parse(path).interpret(c);
    }
    private String[] parseParamsIds(Context c,String needGenId) {
        String path = new GenConfBuilder().addGenConf().addSeparator().addNeedGens().addSeparator().addNeedGen().addOpenbacket().addId().addEqual()
                .addOtherValue(needGenId).addClosebacket().addSeparator().addParams().addSeparator().addParam().addDollar().addDot().addId().addDollar().build();
        System.out.println(path);
        return parse(path).interpret(c);
    }
    private Map<String,String> parseParams(Context c,String needGenId){
        Map<String,String> map=new HashMap<>();
        String[] ids=parseParamsIds(c,needGenId);
        String[] values=parseParamsValues(c,needGenId);
        for (int i = 0; i < ids.length; i++) {
            map.put(ids[i],values[i]);
        }
        return map;
    }

}
