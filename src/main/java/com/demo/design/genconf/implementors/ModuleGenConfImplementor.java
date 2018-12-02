package com.demo.design.genconf.implementors;

import com.demo.design.genconf.vo.ExtendConfModel;
import com.demo.design.genconf.vo.ModuleConfModel;

import java.util.List;
import java.util.Map;

/**
 * 采用桥接模式
 * 获取定义用户需要生成的模块的配置数据
 */
public interface ModuleGenConfImplementor {
    /**
     * 根据核心框架里面注册的需要生成模块的配置参数，来获取相应的需要生成模块的配置数据model，数据只有基本的部分
     * @param param 在核心框架里面注册的需要生成模块的配置参数
     * @return 相应的需要生成模块的配置数据model，数据只有基本的部分
     */
    public ModuleConfModel getBaseModuleConfModel();

    /**
     * 根据核心框架里面注册的需要生成模块的配置参数，来获取相应的需要生成的功能类型
     * @param param 在核心框架里面注册的需要生成模块的配置参数
     * @return
     */
    public Map<String, List<String>> getMapNeedGenTypes();

    public Map<String, ExtendConfModel> getMapExtends();
}
