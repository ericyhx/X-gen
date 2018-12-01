package com.demo.design.genconf.util.readxml.memontodesign;

/**
 * 备忘录对象
 */
public class ParseCaretaker {
    private static ParseCaretaker taker=new ParseCaretaker();
    private ParseMemento memento=null;
    private ParseCaretaker(){}
    public static ParseCaretaker getInstance(){
        return taker;
    }

    /**
     * 保存备忘录对象
     * @param memento
     */
    public void saveMemnto(ParseMemento memento){
        this.memento=memento;
    }

    /**
     * 获取备忘录对象
     * @return
     */
    public ParseMemento retriveMemento(){
        return this.memento;
    }
}
