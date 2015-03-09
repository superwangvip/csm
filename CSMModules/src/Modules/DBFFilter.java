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
public class DBFFilter  extends FileFilter{
    @Override
    public boolean accept(File f){
   String filename= f.getName().toLowerCase();

    return filename.endsWith(".dbf")||f.isDirectory();
}

    @Override
    public String getDescription() {
        return "dbf file";
     //  throw new UnsupportedOperationException("Not supported yet.");
    }

}
