package com.demo.design.genconf;

import com.demo.design.genconf.vo.GenTypeModel;
import com.demo.design.genconf.vo.GenconfModel;
import com.demo.design.genconf.vo.ModuleConfModel;

import java.util.Map;

public interface GenConfEbi {
    /**
     *
     * 获取x-gen核心框架运行所需要的配置数据model
     * @return 核心框架运行所需要的配置数据model
     */
    public GenconfModel getGenConf();

    /**
     * 获取需要生成的所有模块的配置
     * @return 包含所有需要生产的所有模块的配置数据的map，key-模块的ID，value-相应的模块的配置数据的mopdel
     */
    public Map<String, ModuleConfModel> getMapModuleConf();
    /**==============================================以下提供点友好的方法======================================================**/
    /**
     * 根据需要生成模块配置和theme当中的生成类型的编号，来获取相应的theme中的生成类型的model
     * @param moduleConfModel
     * @param needGenTypeId
     * @return
     */
    public GenTypeModel getThemeGenType(ModuleConfModel moduleConfModel,String needGenTypeId);
}
