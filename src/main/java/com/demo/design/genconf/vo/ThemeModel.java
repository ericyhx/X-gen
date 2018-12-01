package com.demo.design.genconf.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ThemeModel {
    private String id;
    private String fileName;
    private Map<String,GenTypeModel> mapGenTypes=new HashMap<>();
    private Map<String,String> mapGenOutTypes=new HashMap<>();
    private Map<String,String> mapProviders=new HashMap<>();
}
