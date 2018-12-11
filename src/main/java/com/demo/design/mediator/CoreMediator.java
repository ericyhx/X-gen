package com.demo.design.mediator;

import com.demo.design.genconf.GenConfFactory;
import com.demo.design.genconf.implementors.GenConfImplementor;
import com.demo.design.genconf.implementors.xmlimpl.GenConfXmlImpl;
import com.demo.design.genconf.vo.GenTypeModel;
import com.demo.design.genconf.vo.ModuleConfModel;
import com.demo.design.geninvocation.DefaultGenInvocation;
import com.demo.design.geninvocation.GenInvocation;
import com.demo.design.geninvocation.GenInvocationFactory;
import com.demo.design.genproxy.GenProxyFactory;
import com.demo.design.template.TemplateEbi;
import com.demo.design.template.TemplateFactory;

import java.util.Collection;
import java.util.List;
import java.util.Observer;

/**
 * 核心框架的中介者对象
 */
public class CoreMediator {
    //实现成为单例
    private static CoreMediator mediator=new CoreMediator();
    private CoreMediator(){}
    public static CoreMediator getInstance(){
        return mediator;
    }
    public GenConfImplementor getDefaultGenConfProvider(){
        return new GenConfXmlImpl();
    }
    public Collection<ModuleConfModel> getNeedGenModuleConf(GenConfImplementor provider){
        return GenConfFactory.cteateGenConfEbi(provider).getMapModuleConf().values();

    }
    public void needProxyGen(String needGenType, ModuleConfModel mouduleConf){
        GenProxyFactory.createGenProxy(needGenType,mouduleConf).executeGen();
    }
    public GenInvocation getDefaultGenInvocation(String needGenType, ModuleConfModel mouduleConf){
        GenInvocation invocation= GenInvocationFactory.createGenInvocation(needGenType,mouduleConf);
        return invocation;
    }
    public String getNeedGenTypeClz(ModuleConfModel moduleConf,String needGenTypeId){
        return GenConfFactory.cteateGenConfEbi().getThemeGenType(moduleConf,needGenTypeId).getGenTypeClz();
    }

    public Object templateReplaceProperty(Object obj) {
        return ((TemplateEbi)obj).replaceProperty();
    }
    public Object getTemplateContent(ModuleConfModel moduleConf,String genTypeId){
        //直接传递模板管理的对象
        return TemplateFactory.createTemplateEbi(moduleConf,genTypeId);
    }
    public void registerObservers(DefaultGenInvocation ctx){
        //1:获得相应的NeedGenOutType的id
        List<String> needGenOutTypeIds = ctx.getMouduleConf().getMapNeedGenTypes().get(ctx.getNeedGenType());
        //2:根据NeedGenOutType的id获得相应的OutType的类的定义
        for(String id:needGenOutTypeIds){
            String genOutTypeClz = GenConfFactory.cteateGenConfEbi().getThemeGenType(ctx.getMouduleConf(), id).getGenTypeClz();
            try {
                Observer o = (Observer) Class.forName(genOutTypeClz).newInstance();
                ctx.addOberver(o);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //3:用反射创建这些类的实例，这些类就是Observer
    }
    public String getThemeMbPathFile(ModuleConfModel moduleConf,String genTypeId){
        return GenConfFactory.cteateGenConfEbi().getGenConf().getThemeById(moduleConf.getUseTheme())
                .getMapGenTypes().get(genTypeId).getMapParms().get("mbPathFile");
    }
    public String getThemePath(ModuleConfModel moduleConf){
        return GenConfFactory.cteateGenConfEbi().getGenConf().getThemeById(moduleConf.getUseTheme()).getFileName();
    }
}
