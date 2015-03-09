package Modules;

import java.io.File;
import javax.swing.filechooser.FileFilter;
/**
 *
 * @author Administrator
 */
public class SQLFilter extends FileFilter {

    public boolean accept(File f) {
        String filename = f.getName().toLowerCase();
        return filename.endsWith(".sql") || f.isDirectory();
    }

    @Override
    public String getDescription() {
        return "SQL script file"; 
    }
}
