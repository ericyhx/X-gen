package com.demo.design.genconf;

import com.demo.design.genconf.confmanager.ConfManager;
import com.demo.design.genconf.implementors.GenConfImplementor;
import com.demo.design.genconf.vo.GenTypeModel;
import com.demo.design.genconf.vo.GenconfModel;
import com.demo.design.genconf.vo.ModuleConfModel;

import java.util.Map;

/**
 * 采用单例模式
 * 负责完成配置管理模块的业务功能
 */
public class GenConfEbo implements GenConfEbi {
    private static GenConfImplementor provider;
    private GenConfEbo(GenConfImplementor provider){
        this.provider=provider;
    }
    private static GenConfEbo ebo=null;
    public static GenConfEbo getInstance(GenConfImplementor provider){
        if(ebo==null){
            if(provider==null){
                throw new IllegalArgumentException("第一次创建配置对象,provider 不能为空");
            }
            ebo=new GenConfEbo(provider);
        }
        return ebo;
    }
/*
    //进行职责分离，由专门的缓存负责
    private GenconfModel gcm=null;
*/
    @Override
    public GenconfModel getGenConf() {
        //获取相应的配置数据
        return ConfManager.genInstance(provider).getGenConf();
    }

    @Override
    public Map<String, ModuleConfModel> getMapModuleConf() {
        //获取相应的配置数据
        return ConfManager.genInstance(provider).getMapModuleConf();
    }

    @Override
    public GenTypeModel getThemeGenType(ModuleConfModel moduleConfModel, String needGenTypeId) {

        return getGenConf().getThemeById(moduleConfModel.getUseTheme()).getMapGenTypes().get(needGenTypeId);
    }
}
