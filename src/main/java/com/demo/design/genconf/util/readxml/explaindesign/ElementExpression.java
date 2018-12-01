package com.demo.design.genconf.util.readxml.explaindesign;

import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 单元素非终结符
 */
public class ElementExpression extends ReadXmlExpression {
    /**
     * 用来记录组合的子ReadXmlExpression元素
     */
    @Getter@Setter
    private List<ReadXmlExpression> eles=new ArrayList<>();

    /**
     * 判断的条件
     */
    private String condition;

    public ElementExpression(String eleName, String condition) {
        super.setEleName(eleName);
        this.condition = condition;
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
        //1、维护父节点记录
        //1.1 先取出父元素
        List<Element> pEles=ctx.getPreEles();
        List<Element> nowEles=new ArrayList<>();
        Element ele=null;
        if(pEles==null||pEles.size()==0){
            //1.2判断父元素是否存在，如果不存在，表示是根元素
            ele=ctx.getDocument().getDocumentElement();
            pEles.add(ele);
            ctx.setPreEles(Arrays.asList(ele));
        }else {
            //1.3 如果存在，那么就应该找到当前表达式所对应的元素，把这个元素设置成父级节点
            for(Element e:pEles){
                nowEles.addAll(ctx.getNowEles(e,getEleName()));
//                if(nowEles.size()>0){
//                    //找到一个就停止
//                    break;
//                }
                //设置为父级节点
                if(nowEles.size()>0&&ctx.judgeCondition(nowEles.get(0),condition)){
                    ctx.setPreEles(Arrays.asList(nowEles.get(0)));
                }
            }

        }

        //2、循环解释子元素
        String[] ss=null;
        for(ReadXmlExpression rxe:eles){
            ss = rxe.interpret(ctx);
        }
        return ss;
    }

    @Override
    public ElementExpression clone(){
        ElementExpression obj=null;
        try {
            obj= (ElementExpression) super.clone();
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
