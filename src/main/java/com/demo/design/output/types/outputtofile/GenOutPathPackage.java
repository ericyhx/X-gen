package com.demo.design.output.types.outputtofile;

import com.demo.design.genconf.vo.ModuleConfModel;

public interface GenOutPathPackage {
    /**
     * 根据配置来生成内容输出的文件夹
     * @param moduleConf
     * @param genTypeId
     * @return
     */
    public boolean genPackage(ModuleConfModel moduleConf,String genTypeId);

    /**
     * 获取内容输出到的文件的路径和文件名
     * @param moduleConf
     * @param genTypeId
     * @return
     */
    public String getOutPathAndFileName(ModuleConfModel moduleConf,String genTypeId);
}
