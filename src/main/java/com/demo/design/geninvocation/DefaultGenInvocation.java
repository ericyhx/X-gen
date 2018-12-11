package com.demo.design.geninvocation;

import com.demo.design.genconf.vo.ModuleConfModel;
import com.demo.design.geninvocation.state.DefaultBeginState;
import com.demo.design.geninvocation.state.State;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * 本地默认具体实现
 */
public class DefaultGenInvocation implements GenInvocation {
    private List<Observer> obs=new ArrayList<>();
    /**
     * 上下文中持有一个状态对象
     */
    @Setter
    private State state;
    /**
     * 具体要生成的类型标识
     */
    @Getter
    private String needGenType;
    /**
     * 当前被generate模块的配置数据
     */
    @Getter
    private ModuleConfModel mouduleConf;
    @Getter@Setter
    private Object tempContent;
    public DefaultGenInvocation(String needGenType, ModuleConfModel mouduleConf) {
        this.needGenType = needGenType;
        this.mouduleConf = mouduleConf;
    }

    @Override
    public void executeGen() {
        //设置generate调用流程开始需要执行的状态
        this.state=new DefaultBeginState();
        //让状态开始执行工作
        state.doWork(this);
        //第一大步：按照一定流程调用相应的生成实现，获取生成的内容

        //第二大步：把生成的内容输出出去
    }

    /**
     * 用来执行工作，在每一个state完成自己的工作之后调用
     */
    public void  doWork(){
        this.state.doWork(this);
    }

    public void addOberver(Observer o) {
        this.obs.add(o);
    }
    /**
     * 通知内容已经生成好了，可以出发联动了
     * @param obj
     */
    public void setContentOver(Object obj) {
//        this.setChanged();
//        this.notifyObservers(obj);

    }
}
