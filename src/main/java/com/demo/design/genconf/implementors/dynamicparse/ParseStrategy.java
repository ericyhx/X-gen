package com.demo.design.genconf.implementors.dynamicparse;

import com.demo.design.genconf.vo.ExtendConfModel;
import com.demo.design.genconf.vo.GenconfModel;

import java.util.Map;

/**
 * 进行模块配置中需要动态解析的字符串的策略接口
 */
public interface ParseStrategy {
    /**
     * 解析动态内容，获得完整的字符串
     * @param gm 包含参数值
     * @param mapEcms 包含参数值
     * @param expr 包含需要动态解析部分的字符串
     * @return 经过动态解析后的完整的内容字符串
     */
    public String parseDynamicContent(GenconfModel gm, Map<String, ExtendConfModel> mapEcms, String expr);
}
