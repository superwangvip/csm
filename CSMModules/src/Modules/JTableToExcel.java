/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modules;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JTable;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.util.*;
import javax.swing.table.TableModel;

/**
 *
 * @author Administrator
 */
public class JTableToExcel {

    public JTableToExcel() {
    }

    public void saveToExcel(JTable table, Vector columnType, String outFile,boolean addSerial)
            throws FileNotFoundException, IOException {
        TableModel md = table.getModel();
        //创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 在Excel工作簿中建工作表，
        HSSFSheet sheet = workbook.createSheet("sheet1");
        // 在索引0的位置创建行（表头）
        HSSFRow row = sheet.createRow(0);

        int c = table.getColumnCount();

        for (int i = 0; i < c; i++) {
            HSSFCell cell = row.createCell(i);
            String colName = table.getColumnName(i);

            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(colName);
        }
        String sqlInt = "int,tinyint,smallint";
        String sqlFloat = "float,real,numeric,money";
        // 写表的内容
        int n = table.getRowCount();
        for (int i = 0; i < n; i++) {
            row = sheet.createRow(i + 1);
            int m = table.getColumnCount();

            for (int j = 0; j < m; j++) {
                JDBColumn dbcol = (JDBColumn) columnType.elementAt(j);
                String type = dbcol.getTypeName();

                HSSFCell cell = row.createCell(j);
                //HSSFCellStyle cellStyle = workbook.createCellStyle();
                //cell.setCellStyle(cellStyle);
                Object cellData = md.getValueAt(i, j);
                String data = "";
                if (cellData != null) {
                    data = cellData.toString().trim();
                }
                if (sqlInt.contains(type)) {
                    cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    int d = Integer.valueOf(data);
                    cell.setCellValue(d);

                } else if (sqlFloat.contains(type)) {
                    double d = Double.valueOf(data);
                    cell.setCellValue(d);

                } else {
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setCellValue(data);
                }
            }
        }
        // 调整各列宽度

        for (int j = 0; j < c; j++) {
            sheet.autoSizeColumn(j);
        }
        // 新建一输出文件流
        FileOutputStream fOut = new FileOutputStream(outFile);
        // 把相应的Excel 工作簿存盘
        workbook.write(fOut);
        fOut.flush();
        // 操作结束，关闭文件
        fOut.close();
    }
}
