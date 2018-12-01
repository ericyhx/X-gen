package com.demo.design.genconf.implementors;

import com.demo.design.genconf.vo.NeedGenModel;
import com.demo.design.genconf.vo.ThemeModel;

import java.util.List;
import java.util.Map;

/**
 * 采用桥接模式
 * 获取核心框架配置数据的接口
 */
public interface GenConfImplementor {
    /**
     * 获取核心框架配置中注册的需要生成的模块的配置数据
     * @return
     */
    public List<NeedGenModel> getNeedGens();

    /**
     * 获取核心框架配置中注册的外部主题的配置数据
     * @return
     */
    public List<ThemeModel> getThemes();

    /**
     * 获取核心框架配置中注册的定义的公共常量
     * @return
     */
    public Map<String,String> getMapConstants();
}
