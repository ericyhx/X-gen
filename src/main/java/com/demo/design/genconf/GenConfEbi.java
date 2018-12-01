package com.demo.design.genconf;

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
}
