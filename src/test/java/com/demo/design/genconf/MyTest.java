package com.demo.design.genconf;

import com.demo.design.genconf.util.readxml.componentdesign.Parser2;
import com.demo.design.genconf.util.readxml.explaindesign.Context;
import com.demo.design.genconf.util.readxml.explaindesign.ElementExpression;
import com.demo.design.genconf.util.readxml.explaindesign.ElementsExpression;
import com.demo.design.genconf.util.readxml.explaindesign.ElementsTerminalExpression;
import com.demo.design.genconf.util.readxml.memontodesign.Parser;
import org.junit.Test;

public class MyTest {
    @Test
    public void genConfMehtodTest() throws Exception {
        //1. 表达式--->抽象语法树--->解析器
        //2. 抽象语法树--->交给结束期模式去解释执行

        ElementExpression genConf=new ElementExpression("GenConf","");
        ElementExpression needGens=new ElementExpression("NeedGens","");
        ElementsExpression needGen=new ElementsExpression("NeedGen","");
        ElementsExpression params=new ElementsExpression("Params","");

        ElementsTerminalExpression param=new ElementsTerminalExpression("Param","");
        //组装抽象语法树
        genConf.addEle(needGens);
        needGens.addEle(needGen);
        needGen.addEle(params);
        params.addEle(param);

        Context ctx=Context.getInstance("GenConf.xml");
        String[] ss=genConf.interpret(ctx);
        for (String s:ss){
            System.out.println("ss=="+s);
        }

    }
    @Test
    public void  parserTest() throws Exception {
        Context ctx=Context.getInstance("GenConf.xml");
        String[] ss;
        String[] ss2;
        long start=System.currentTimeMillis();
        //只取一个值
//        ss= Parser2.parse("GenConf/NeedGens/NeedGen/Params/Param").interpret(ctx);
        //取单个属性
//        ss= Parser2.parse("GenConf/NeedGens/NeedGen/Params/Param.id").interpret(ctx);
        //取多个值
//        ss= Parser2.parse("GenConf/NeedGens/NeedGen/Params/Param$").interpret(ctx);
        //取多个属性值
//        ss= Parser2.parse("GenConf/NeedGens/NeedGen/Params/Param$.id$").interpret(ctx);
        //按条件获取
        ss= Parser2.parse("GenConf/Contants/Constant$.id$").interpret(ctx);
//        ss2= Parser.parse("GenConf/NeedGens/NeedGen/Params/Param$[id=fileName2]").interpret(ctx);
        System.out.println("cost:"+(System.currentTimeMillis()-start));
        for (String s:ss){
            System.out.println("ss=="+s);
        }
//        for (String s:ss2){
//            System.out.println("ss=="+s);
//        }
    }
}
