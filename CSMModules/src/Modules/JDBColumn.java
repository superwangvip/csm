/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modules;

/**
 *
 * @author Administrator
 */
public class JDBColumn {
   private String typeName=null;
   private int width=0;
   public void setTypeName(String typeName) {
       this.typeName=typeName;
   }
   public String getTypeName() {
       return typeName;
   }
   public void setcolumnWidth(int width){
       this.width=width;
   }
   public int getcolumnWidth() {
       return this.width;
   }
}
