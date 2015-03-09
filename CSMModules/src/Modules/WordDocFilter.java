/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modules;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Administrator
 */
public class WordDocFilter extends FileFilter {

    public boolean accept(File f) {
        String filename = f.getName().toLowerCase();

        return filename.endsWith(".doc") || f.isDirectory();
    }

    public String getDescription() {
        return "Microsoft Word文档(.doc)";
    //  throw new UnsupportedOperationException("Not supported yet.");
    }
}