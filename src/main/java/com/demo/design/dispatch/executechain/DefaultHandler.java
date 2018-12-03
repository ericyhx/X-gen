package com.demo.design.dispatch.executechain;

import com.demo.design.genconf.vo.ModuleConfModel;

public class DefaultHandler extends GenHandler {
    /**
     * 区分命令对象或者命令标识
     */
    private String cmd="";

    public DefaultHandler(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public void handlerRequest(ModuleConfModel mcm) {
        //继续调用真正实现生成功能的模块
        super.handlerRequest(mcm);
    }
}
