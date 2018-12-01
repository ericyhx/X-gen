package com.demo.design.genconf.confmanager;

import com.demo.design.genconf.implementors.GenConfImplementor;
import com.demo.design.genconf.implementors.xmlimpl.GenConfXmlImpl;
import com.demo.design.genconf.vo.GenconfModel;
import com.demo.design.genconf.vo.ModuleConfModel;

import java.util.HashMap;
import java.util.Map;
public class ConfManager {
    //先实现成单例模式
    private ConfManager(){}
    private static ConfManager manager=null;
    public static ConfManager genInstance(){
        if(manager==null){
            manager=new ConfManager();
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
    }

    public GenconfModel getGenConf(GenConfImplementor genConfImpl) {
        readConf(genConfImpl);
        return genConf;
    }

    public Map<String, ModuleConfModel> getMapModuleConf() {
        return mapModuleConf;
    }
}
