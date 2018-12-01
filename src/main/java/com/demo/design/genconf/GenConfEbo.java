package com.demo.design.genconf;

import com.demo.design.genconf.confmanager.ConfManager;
import com.demo.design.genconf.implementors.GenConfImplementor;
import com.demo.design.genconf.vo.GenconfModel;
import com.demo.design.genconf.vo.ModuleConfModel;

import java.util.Map;

/**
 * 采用单例模式
 * 负责完成配置管理模块的业务功能
 */
public class GenConfEbo implements GenConfEbi {
    private static GenConfImplementor genConf;
    private GenConfEbo(){}
    private static GenConfEbo ebo=new GenConfEbo();
    public static GenConfEbo getInstance(GenConfImplementor genConfImpl){
        genConf=genConfImpl;
        return ebo;
    }
/*
    //进行职责分离，由专门的缓存负责
    private GenconfModel gcm=null;
*/
    @Override
    public GenconfModel getGenConf() {
        //获取相应的配置数据
        return ConfManager.genInstance().getGenConf(genConf);
    }

    @Override
    public Map<String, ModuleConfModel> getMapModuleConf() {
        //获取相应的配置数据
        return ConfManager.genInstance().getMapModuleConf();
    }
}
