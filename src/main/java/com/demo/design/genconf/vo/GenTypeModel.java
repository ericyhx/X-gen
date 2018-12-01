package com.demo.design.genconf.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class GenTypeModel {
    private String id;
    private String genTypeClz;
    private Map<String,String> mapParms=new HashMap<>();
}
