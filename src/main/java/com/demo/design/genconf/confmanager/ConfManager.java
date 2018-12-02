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
        this.genConf.setNeedGens(tempGenConf.getNeedGens());
        this.genConf.setMapConstants(tempGenConf.getMapConstants());
        this.genConf.setThemes(tempGenConf.getThemes());
        fillThemeModel();
        fillNeedGenModel();

    }
    public GenconfModel getGenConf() {
        return genConf;
    }

    public Map<String, ModuleConfModel> getMapModuleConf() {
        return mapModuleConf;
    }


    private void fillNeedGenModel() {
        List<NeedGenModel> needGens = this.genConf.getNeedGens();
        for(NeedGenModel needGen:needGens){
            Collection<String> values = needGen.getMapParams().values();
            for(String fileName:values){
                ModuleGenConfImplementor moduleImpl=new ModuleGenConfXmlImpl(fileName);
                ModuleConfModel module = moduleImpl.getBaseModuleConfModel();
                module.setMapExtends(moduleImpl.getMapExtends());
                module.setMapNeedGenTypes(moduleImpl.getMapNeedGenTypes());
                module.setUseTheme(needGen.getTheme());
                mapModuleConf.put(module.getModelId(),module);
            }
        }
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

}
