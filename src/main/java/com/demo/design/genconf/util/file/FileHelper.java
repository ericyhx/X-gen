package com.demo.design.genconf.util.file;

import java.io.*;

public class FileHelper {
    private FileHelper() { }
    public static String readFile(String path){
        DataInputStream din=null;
        String content="";
        try {
            din=new DataInputStream(new BufferedInputStream(FileHelper.class.getClassLoader().getResourceAsStream(path)));
            byte[] bytes = new byte[din.available()];
            din.read(bytes);
            content=new String(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                din.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }
    public static void writeFile(String pathName,String content){
        File f=new File(pathName);
        DataOutputStream dos=null;
        if(f.exists()){
            f.delete();
        }
        try {
            f.createNewFile();
            dos=new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
            dos.write(content.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
