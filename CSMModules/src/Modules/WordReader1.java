/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modules;

import com.jacob.activeX.*;
import com.jacob.com.*;

/**
 *
 * @author Administrator
 */
public class WordReader1 {

    public static void extractDoc(String inputFIle, String outputFile) {
        boolean flag = false;
        // 打开Word应用程序
        ActiveXComponent app = new ActiveXComponent("Word.Application");
        try {
            // 设置word可见
            app.setProperty("Visible", new Variant(false));
            // 打开word文件
            Dispatch doc1 = app.getProperty("Documents").toDispatch();
            Dispatch doc2 = Dispatch.invoke(
                    doc1,
                    "Open",
                    Dispatch.Method,
                    new Object[]{inputFIle, new Variant(false),
                        new Variant(true)}, new int[1]).toDispatch();
            // 作为txt格式保存到临时文件
            Dispatch.invoke(doc2, "SaveAs", Dispatch.Method, new Object[]{
                        outputFile, new Variant(7)}, new int[1]);
            // 关闭word
            Variant f = new Variant(false);
            Dispatch.call(doc2, "Close", f);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            app.invoke("Quit", new Variant[]{});
        }

        if (flag == true) {
            System.out.println("Transformed Successfully");
        } else {
            System.out.println("Transform Failed");
        }
    }
}
