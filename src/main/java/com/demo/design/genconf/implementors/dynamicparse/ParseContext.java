package com.demo.design.genconf.implementors.dynamicparse;

import com.demo.design.genconf.vo.ExtendConfModel;
import com.demo.design.genconf.vo.GenconfModel;

import java.util.Map;

public class ParseContext {

    public Map<String, ExtendConfModel> parse(GenconfModel gm,Map<String,ExtendConfModel> mapEcms){
        //现在只支持两种语法，因此选择策略算法的过程是可以固定的，就这里做选择
        //目前的约定是只在Module配置的ExtendConf里面才能使用变量
        //如果${}里面是一个单独的单词，那就使用属性直接替换策略，否则就使用beanshell策略
        for(String key:mapEcms.keySet()){
            ExtendConfModel ecm = mapEcms.get(key);
            ecm.setValue(this.parseOne(gm,mapEcms,ecm.getValue()));
            mapEcms.put(key,ecm);
        }
        return mapEcms;
    }
    private String parseOne(GenconfModel gm,Map<String,ExtendConfModel> mapEcms,String value){
        //1.首先判断是否有${,如果没有，那就直接返回，不用解析
        if(value!=null&&value.contains("${")){
            //2.如果有${，那就把里面的值取出来，
            int begin=value.indexOf("${");
            int end=begin+2+value.substring(begin+2).indexOf("}");
            String prop=value.substring(begin+2,end);
            //3.在判断${}里面是一个单词，那就使用属性直接替换，否则就使用beanshell策略
            ParseStrategy ps=null;
            if(isWord(prop)){
                ps=new PropertyReplaceStrategy();
            }else {
                ps=new BeanShellStrategy();
            }
            //得到解析后的动态部分的数据
            String tempStr=ps.parseDynamicContent(gm,mapEcms,prop);
            //然后把这动态的部分数据替换回去到value上
            value=value.replace("${"+prop+"}",tempStr);
            //继续递归
            value=parseOne(gm,mapEcms,value);
        }
        return value;
    }
    private boolean isWord(String s){
        char[] chars = s.toCharArray();
        for(char c:chars){
            if((c>='a'&&c<='z') || (c>='A'&&c<='Z')){

            }else {
                return false;
            }
        }
        return true;
    }
}
