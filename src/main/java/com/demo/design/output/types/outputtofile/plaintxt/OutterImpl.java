package com.demo.design.output.types.outputtofile.plaintxt;

import com.demo.design.genconf.util.file.FileHelper;
import com.demo.design.output.types.outputtofile.Outter;

public class OutterImpl implements Outter {
    @Override
    public boolean writeContent(String outPathAndFileName, String content) {
        FileHelper.writeFile(outPathAndFileName,content);
        return true;
    }
}
