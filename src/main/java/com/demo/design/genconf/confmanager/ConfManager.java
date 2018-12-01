package com.demo.design.genconf.confmanager;

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
    private void  readConf(){
        //正真的获取配置数据

        //然后把获取到的配置数据设置到属性上，缓存起来
    }

    public GenconfModel getGenConf() {
        return genConf;
    }

    public Map<String, ModuleConfModel> getMapModuleConf() {
        return mapModuleConf;
    }
}
