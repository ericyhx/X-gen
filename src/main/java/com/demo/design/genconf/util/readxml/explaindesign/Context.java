package com.demo.design.genconf.util.readxml.explaindesign;

import com.demo.design.genconf.util.readxml.XmlUtil;
import lombok.Data;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解释器模式
 *      单例模式
 * 上下文，存放解释器需要的一些全局信息
 */
@Data
public class Context {
    private Document document=null;
    /**
     * 上一次被处理的多个父节点的记录
     */
    private List<Element> preEles=new ArrayList<>();
    /**
     * 用来缓存多个context实例
     */
    private static Map<String,Context> mapCtx=new HashMap<>();
    private Context(Document document) {
        this.document = document;
    }
    public static Context getInstance(String fileName) throws Exception{
        Context ctx=mapCtx.get(fileName);
        if(ctx==null){
            Document doc = XmlUtil.getDocument(fileName);
            ctx=new Context(doc);
            mapCtx.put(fileName,ctx);
        }
        //需要先初始化一下
        ctx.init();
        return ctx;
    }
    public void init(){
        preEles=new ArrayList<>();
    }
    public List<Element> getNowEles(Element pEle,String eleName){
        List<Element> nowEles=new ArrayList<>();
        NodeList tempList = pEle.getChildNodes();
        for (int i = 0; i < tempList.getLength(); i++) {
            if(tempList.item(i) instanceof Element){
                Element ele= (Element) tempList.item(i);
                if(ele.getTagName().equals(eleName)){
                    nowEles.add(ele);
                }
            }
        }
        return nowEles;
    }

    /**
     * 目前只是实现了判断元素的属性等于某个条件值
     * @param ele
     * @param condition
     * @return
     */
    public boolean judgeCondition(Element ele,String condition){
        if(condition==null||condition.trim().length()==0){
            return true;
        }
        String[] ss=condition.split("=");
        if(ss[1]!= null&&ss[1].equals(ele.getAttribute(ss[0]))){
            return true;
        }
        return false;

    }
}
