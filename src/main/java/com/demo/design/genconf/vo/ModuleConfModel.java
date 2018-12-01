package com.demo.design.genconf.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Data
public class ModuleConfModel {
    /**
     * 用户需要生成的模块的标识
     */
    private String modelId;
    /**
     * 用户需要生成的这个模块所使用的外部主题的标识
     */
    private String useTheme;
    /**
     * 用户需要生成的具体功能，key-需要生成的功能的标识，value-该功能生成后的多种输出类型的标识I的集合
     */
    private Map<String, List<String>> mapNeedGenTypes=new HashMap<>();
    /**
     * 模块生成所需要的扩展数据，key-数据的id，value-对应的扩展数据的model
     */
    private Map<String,ExtendConfModel> mapExtends=new HashMap<>();
}
