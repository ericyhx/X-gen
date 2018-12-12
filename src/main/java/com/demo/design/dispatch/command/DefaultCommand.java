package com.demo.design.dispatch.command;

import com.demo.design.dispatch.executechain.DefaultHandler;
import com.demo.design.dispatch.executechain.GenHandler;
import com.demo.design.genconf.vo.ModuleConfModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultCommand implements GenCommand {
    /**
     * 具体要生成的模块的配置model
     */
    private ModuleConfModel moduleConf;

    public DefaultCommand(ModuleConfModel moduleConf) {
        this.moduleConf = moduleConf;
    }

    /**
     * 这里相当于职责链的客户端
     */
    @Override
    public void execute() {
        //应该调用后续的模块去实现真正的功能

        //根据配置来动态组装职责链，也就是按照生成的配置来组合需要生成的功能
        List<String> listNeedGenTypes=new ArrayList<>();
        //1：先把配置中配置的需要生成的功能的类型获取到，并放入到list里面
        for(String s:moduleConf.getMapNeedGenTypes().keySet()){
            listNeedGenTypes.add(s);
        }
        //2:把list里面的数据转换到一个map中，key就是一个顺序值，value就是需要生成的类型的处理器
        Map<Integer, GenHandler> mapHandlers=new HashMap<>();
        for (int i = 0; i < listNeedGenTypes.size(); i++) {
            mapHandlers.put(i+1,new DefaultHandler(listNeedGenTypes.get(i)));
        }
        //3:把map里面的数据按照顺序获取出来，构建职责链
        GenHandler h1=mapHandlers.get(1);
        for (int i = 1; i <mapHandlers.size(); i++) {
            mapHandlers.get(i).setSuccessor(mapHandlers.get(i+1));
        }
        //4: 执行请求
        h1.handlerRequest(moduleConf);

    }}
