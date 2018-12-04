package com.demo.design.dispatch.executechain;

import com.demo.design.genconf.vo.ModuleConfModel;
import com.demo.design.mediator.CoreMediator;

public class DefaultHandler extends GenHandler {
    /**
     * 区分命令对象或者命令标识(需要生成的标识)
     */
    private String needGenTyoe ="";

    public DefaultHandler(String needGenTyoe) {
        this.needGenTyoe = needGenTyoe;
    }

    @Override
    public void handlerRequest(ModuleConfModel mcm) {
        //1：本职责对象要实现的功能，继续调用真正实现生成功能的模块
        CoreMediator.getInstance().needProxyGen(needGenTyoe,mcm);
        //2：交给弗父类，继续职责链的后续处理
        super.handlerRequest(mcm);
    }
}
