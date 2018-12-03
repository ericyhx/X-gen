package com.demo.design.dispatch.command;

import lombok.Setter;

public class CmdInvoker {
    /**
     * 持有命令模块
     */
    @Setter
    private GenCommand cmd=null;

    /**
     * 提供给客户端使用的方法，让客户来请求执行命令
     */
    public void doCommand(){
        this.cmd.execute();
    }
}
