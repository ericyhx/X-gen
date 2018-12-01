package com.demo.design.genconf.implementors;

import com.demo.design.genconf.vo.GenTypeModel;

import java.util.Map;

/**
 * 采用桥接模式
 */
public interface ThemeImplementor {
    /**
     * 根据theme的id和在核心框架里面注册theme是配置的相应的参数，来获取theme中定义的能生成的功能类型
     * @param themeId theme 的id
     * @param params 在核心框架里面注册的theme配置的相应的参数
     * @return theme中定义的能生成的功能类型
     */
    public Map<String, GenTypeModel> getMapGenType(String themeId,Map<String,String> params);

    public Map<String,String> getMapGenOutTypes(String themeId,Map<String,String> params);

    public Map<String,String> getMapProviders(String themeId,Map<String,String> params);


}
