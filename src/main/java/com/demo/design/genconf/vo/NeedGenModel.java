package com.demo.design.genconf.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class NeedGenModel {
   private String id;
   private String provider;
   private String theme;
   private Map<String,String> mapParams=new HashMap<>();
}
