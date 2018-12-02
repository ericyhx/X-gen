package com.demo.design.genconf.confmanager;

import com.demo.design.genconf.implementors.GenConfImplementor;
import com.demo.design.genconf.implementors.ModuleGenConfImplementor;
import com.demo.design.genconf.implementors.ThemeImplementor;
import com.demo.design.genconf.implementors.xmlimpl.GenConfXmlImpl;
import com.demo.design.genconf.implementors.xmlimpl.ModuleGenConfXmlImpl;
import com.demo.design.genconf.implementors.xmlimpl.ThemeXmlImpl;
import com.demo.design.genconf.vo.GenconfModel;
import com.demo.design.genconf.vo.ModuleConfModel;
import com.demo.design.genconf.vo.NeedGenModel;
import com.demo.design.genconf.vo.ThemeModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ConfManager {
    private GenConfImplementor provider;
    //先实现成单例模式
    private ConfManager(GenConfImplementor provider){
        this.provider=provider;
        readConf(provider);
    }
    private static ConfManager manager=null;
    public static ConfManager genInstance(GenConfImplementor provider){
        if(manager==null){
            manager=new ConfManager(provider);
        }
        return manager;
    }
    //定义要缓存的数据
    private GenconfModel genConf=new GenconfModel();
    private Map<String, ModuleConfModel> mapModuleConf=new HashMap<>();
    private void  readConf(GenConfImplementor tempGenConf){
        //正真的获取配置数据
        //然后把获取到的配置数据设置到属性上，缓存起来
       readGenConf(provider);
        fillThemeModel();
       for(NeedGenModel ngm:genConf.getNeedGens()){
           readOneModuleGenConf(ngm);
        }
    }

    private void readGenConf(GenConfImplementor provider) {
        this.genConf.setNeedGens(provider.getNeedGens());
        this.genConf.setMapConstants(provider.getMapConstants());
        this.genConf.setThemes(provider.getThemes());
    }

    public GenconfModel getGenConf() {
        return genConf;
    }

    public Map<String, ModuleConfModel> getMapModuleConf() {
        return mapModuleConf;
    }
    private void fillThemeModel() {
        List<ThemeModel> themes = this.genConf.getThemes();
        for(ThemeModel theme:themes){
            ThemeImplementor tempTheme=new ThemeXmlImpl(theme.getFileName());
            theme.setMapGenTypes(tempTheme.getMapGenType());
            theme.setMapGenOutTypes(tempTheme.getMapGenOutTypes());
            theme.setMapProviders(tempTheme.getMapProviders());
        }
    }
    private void readOneModuleGenConf(NeedGenModel ngm){
        ThemeModel tm = this.genConf.getThemeById(ngm.getTheme());
        String providerClzName="";
        ModuleGenConfImplementor userGenConfImpl=null;
        if(tm!=null){
            providerClzName=tm.getMapProviders().get(ngm.getProvider());
        }
        if(providerClzName.trim().length()==0)return;
        try {
            userGenConfImpl= (ModuleGenConfImplementor) Class.forName(providerClzName).newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        if(userGenConfImpl==null) return;
       for(String moduleName: ngm.getMapParams().values()){
           userGenConfImpl.setModuleFileName(moduleName);
       }
        ModuleConfModel mcm=userGenConfImpl.getBaseModuleConfModel();
        mcm.setUseTheme(ngm.getTheme());
        mcm.setMapNeedGenTypes(userGenConfImpl.getMapNeedGenTypes());
        //解析Extends应该放到其他解析的后面，因为如果有动态解析的话需要使用其他的值
        mcm.setMapExtends(userGenConfImpl.getMapExtends(genConf));
        //设置到缓存里面
        this.mapModuleConf.put(mcm.getModelId(),mcm);
    }
}
