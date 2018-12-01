package com.demo.design.genconf.util.readxml.explaindesign;

import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * 多元素非终结符
 */
public class ElementsExpression extends ReadXmlExpression{
    private String condition;
    /**
     * 用来记录组合的子ReadXmlExpression元素
     */
    @Getter@Setter
    private List<ReadXmlExpression> eles=new ArrayList<>();
    public ElementsExpression(String eleName, String condition) {
        super.setEleName(eleName);
        this.condition=condition;
    }
    public void addEle(ReadXmlExpression ele){
        this.eles.add(ele);
    }
    public boolean removeEle(ReadXmlExpression ele){
        eles.remove(ele);
        return true;
    }
    public void removeAll(){
        this.eles.clear();
    }
    @Override
    public String[] interpret(Context ctx) {
        //1. 维护父节点记录
        List<Element> preEles = ctx.getPreEles();
        //2. 获取当前元素
        List<Element> nowEles=new ArrayList<>();
        if(preEles.size()>0){
            for(Element pre:preEles){
                nowEles.addAll(ctx.getNowEles(pre, getEleName()));
            }
           if(nowEles.size()>0){
               for(Element e:nowEles){
                   if(!ctx.judgeCondition(e,condition)){
                       nowEles.remove(e);
                   }
               }
               ctx.setPreEles(nowEles);
           }
        }else {
            System.out.println(getEleName()+" 没有父节点");
        }
        String[] ss=null;
        for(ReadXmlExpression rxe:eles){
            ss=rxe.interpret(ctx);
        }
        return ss;
    }
    @Override
    public ElementsExpression clone(){
        ElementsExpression obj=null;
        try {
            obj= (ElementsExpression) super.clone();
            List<ReadXmlExpression> objEles=new ArrayList<>();
            for(ReadXmlExpression exe:eles){
                objEles.add((ReadXmlExpression) exe.clone());
            }
            obj.setEles(objEles);
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }
}
