package com.kikc.www.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * ----------Dragon be here!----------/
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑
 * 　　　　┃　　　┃代码无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * <p/>
 * Created by ebinhon on 3/7/2016.
 */
public class FileUtil {

    public static void writeFile(String lineContent, File file) {
        String fileName = file.getName();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }else{
            FileWriter writer = null;
            try {
                writer = new FileWriter(fileName, true);
                writer.write(lineContent);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
