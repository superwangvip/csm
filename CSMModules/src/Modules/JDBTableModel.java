/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modules;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Jiang Youquan
 */
public class JDBTableModel {

    private Connection conn;
    private String sql;
    private JTable table;
    private Vector columnType;
    private boolean addSerial;
    private SQLStatus st = new SQLStatus();
    boolean isColorGrid = false;

    public JDBTableModel(JTable table) {
        this.table = table;
    }
    //1.单线程执行sql并将返回数据集读到表格模型，无等待提示,一般用户快速简单的查询并返回结果集列表

    public synchronized void fetchDataToTable(Connection conn, String sql, Vector columnType, boolean addSerial) {
        Vector content = new Vector();
        Vector header = new Vector();
        Statement stat;
        try { //执行SQL
            stat = (Statement) conn.createStatement();
            boolean hasResultSet = stat.execute(sql);
            while (!hasResultSet) {//读取返回结果
                hasResultSet = stat.getMoreResults();
            }
            if (hasResultSet) {
                //先读出表头及列属性（列类型名),并预设列宽)
                ResultSet SqlResult = stat.getResultSet();
                ResultSetMetaData metaData = SqlResult.getMetaData();
                int columnCount = metaData.getColumnCount();
                int rowNo = 1;
                columnType.clear();
                if (addSerial) {
                    header.add("序号");
                    JDBColumn dbcol = new JDBColumn();
                    dbcol.setTypeName("int");
                    dbcol.setcolumnWidth(8);
                    columnType.add(dbcol);
                }
                for (int i = 1; i <= columnCount; i++) {
                    String name = metaData.getColumnName(i);
                    String type = metaData.getColumnTypeName(i);
                    int w = metaData.getColumnDisplaySize(i);

                    System.out.println(i + ":" + name + ":" + type + ":" + w);
                    JDBColumn dbcol = new JDBColumn();
                    dbcol.setTypeName(type);
                    dbcol.setcolumnWidth(w);
                    columnType.add(dbcol);
                    header.add(name);
                }
                // System.out.println( columnType.size());
                //读出各行,并按列类型进行格式化输出;
                DecimalFormat myFormatter = new DecimalFormat("###,###,###,###.##");
                String sqlInt = "int,bigint,tinyint,smallint";
                String sqlFloat = "float,real,money,numeric";
                while (SqlResult.next()) {
                    Vector row = new Vector();
                    if (addSerial) {
                        Integer r = Integer.valueOf(rowNo);
                        row.add(r);
                        rowNo++;
                    }
                    for (int i = 1; i <= columnCount; i++) {
                        String type = metaData.getColumnTypeName(i);
                        if (type.equals("int") || type.equals("tinyint") || type.equals("smallit")) {
                            int intCell = SqlResult.getInt(i);
                            row.add(Integer.valueOf(intCell));
                        } else if (type.equals("bigint")) {
                            long longCell = SqlResult.getLong(i);
                            row.add(Long.valueOf(longCell));
                        } else if (type.equals("float") || type.equals("numeric") || type.equals("real")) {
                            float floatCell = SqlResult.getFloat(i);
                            row.add(Float.valueOf(floatCell));
                        } else if (type.equals("money")) {
                            double doubleCell = SqlResult.getDouble(i);
                            row.add(Double.valueOf(doubleCell));
                        } else {
                            String s = SqlResult.getString(i);
                            if (s == null) {
                                s = "";
                            }
                            row.add(s);
                        }
                    }
                    content.add(row);
                }
                SqlResult.close();
                TableModel mytablemodel = new DefaultTableModel(content, header) {
                    //此方法可以排序

                    @Override
                    public Class<?> getColumnClass(int column) {
                        if (this.getRowCount() > 0) {
                            if (getValueAt(0, column) != null) {
                                return getValueAt(0, column).getClass();
                            } else {
                                return Object.class;
                            }
                        } else {
                            return Object.class;
                        }
                    }
                };
                table.setModel(mytablemodel);
                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(mytablemodel);
                table.setRowSorter(sorter);
                fitTableColumns(table);//表格列宽自适应
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBTableModel.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning(ex.getLocalizedMessage()).setVisible(true);
        }
    }

    //表格列宽自适应方法
    private synchronized void fitTableColumns(JTable myTable) {
        JTableHeader header = myTable.getTableHeader();
        int rowCount = myTable.getRowCount();

        Enumeration columns = myTable.getColumnModel().getColumns();
        while (columns.hasMoreElements()) {
            TableColumn column = (TableColumn) columns.nextElement();
            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            int width = (int) myTable.getTableHeader().getDefaultRenderer().getTableCellRendererComponent(myTable, column.getIdentifier(), false, false, -1, col).getPreferredSize().getWidth();
            for (int row = 0; row < rowCount; row++) {
                int preferedWidth = (int) myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable,
                        myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            header.setResizingColumn(column); // 此行很重要
            column.setWidth(width + myTable.getIntercellSpacing().width);
        }
    }

    //2.多线程执行sql并读取结果集到表，无提示等待信息,用于行情显示等。
    public void fetchDataToTable(Connection conn, String sql, Vector columnType, boolean addSerial, boolean isColorGrid) {
        Vector content = new Vector();
        Vector header = new Vector();
        st.setSTatus(false);
        this.isColorGrid = isColorGrid;

        //执行sql并读取结果集到表，多线程中执行，有提示等待信息。
        DBSQL r = new DBSQL(conn, sql, content, header, columnType, addSerial, st);
        Thread t1 = new Thread(r);//线程1:执行sql，读取结果集
        SetTableData r2 = new SetTableData(table,content, header, st, isColorGrid);
        Thread t2 = new Thread(r2);//线程2:设置表的模型数据
        t1.start();//启动执行sql的独立线程t1
        SwingUtilities.invokeLater(t2);//在Swing线程中执行设置表模型的线程t2,
    }

    //3.多线程执行sql并读取结果集到表，有提示等待信息：用于有较大量计算的复杂sql并返回返回结果集列表
    public synchronized void fetchDataToTable(Connection conn, String sql, Vector columnType, boolean addSerial, String msg) {
        Vector content = new Vector();
        Vector header = new Vector();
        //分别创建三个线程
        st.setSTatus(false);
        SysMessage sm = new SysMessage(msg, st);
        Thread t1 = new Thread(sm);//线程1:提示信息显示

        DBSQL r = new DBSQL(conn, sql, content, header, columnType, addSerial, st);
        Thread t2 = new Thread(r);//线程2:执行sql，读取结果集

        SetTableData r3 = new SetTableData(table,content, header, st, false);
        Thread t3 = new Thread(r3);//线程3:设置表的模型数据
        //分别启动三个线程
        SwingUtilities.invokeLater(t1);//有对Swing组件操作,需要在Swing线程中执行
        t2.start();//无对Swing组件操作，调用sql，比较费时，另外启动一个独立线程来执行
        SwingUtilities.invokeLater(r3);//有对Swing组件操作,需要在Swing线程中执行
    }
    //根据表格列名读取某行某列数据

    public String getValueByColumnName(int r, String columnName) {
        String value = "";
        boolean found = false;
        TableModel m = table.getModel();
        int cc = m.getColumnCount();
        for (int i = 0; i < cc; i++) {
            //System.out.println(i+m.getColumnName(i)+":"+columnName);
            if (m.getColumnName(i).trim().equalsIgnoreCase(columnName.trim())) {
                Object value_O = m.getValueAt(r, i);
                if(value_O!=null){
                    value=value_O.toString().trim();
                }
                found = true; 
                 i=cc;
            }
        }
        if (!found) {
            new JFrameWarning("表格中列名不存在: " + columnName.toString()).setVisible(true);
        }
        return value;
    }
}

class DBSQL implements Runnable {

    Connection conn;
    String sql;
    //JTable table;
    Vector content = new Vector();
    Vector header = new Vector();
    Vector columnType;
    boolean addSerial;
    SQLStatus st;

    public DBSQL(Connection conn, String sql, Vector content, Vector header, Vector columnType, boolean addSerial, SQLStatus st) {
        this.conn = conn;
        this.sql = sql;
        this.content = content;
        this.header = header;
        this.columnType = columnType;
        this.addSerial = addSerial;
        this.st = st;
    }

    public void run() {
        System.out.println("Thread2:DBSQL--fetchData()");
        st.setSTatus(false);
        fetchData(conn, sql, content, header, columnType, addSerial);
        System.out.println("Thread2:DBSQL--fetchData() finish !");
        st.setSTatus(true);
    }

    public synchronized void fetchData(Connection conn, String sql, Vector content, Vector header, Vector columnType, boolean addSerial) {
        Statement stat;
        try { //执行SQL
            stat = (Statement) conn.createStatement();
            boolean hasResultSet = stat.execute(sql);
            while (!hasResultSet) {//读取返回结果
                hasResultSet = stat.getMoreResults();
            }
            if (hasResultSet) {
                //先读出表头及列属性（列类型名),并预设列宽)
                ResultSet SqlResult = stat.getResultSet();
                ResultSetMetaData metaData = SqlResult.getMetaData();
                int columnCount = metaData.getColumnCount();
                int rowNo = 1;
                columnType.clear();
                if (addSerial) {
                    header.add("序号");
                    JDBColumn dbcol = new JDBColumn();
                    dbcol.setTypeName("int");
                    dbcol.setcolumnWidth(8);
                    columnType.add(dbcol);
                }
                for (int i = 1; i <= columnCount; i++) {
                    String name = metaData.getColumnName(i);
                    String type = metaData.getColumnTypeName(i);
                    int w = metaData.getColumnDisplaySize(i);
                    System.out.println(i + ":" + name + ":" + type + ":" + w);
                    JDBColumn dbcol = new JDBColumn();
                    dbcol.setTypeName(type);
                    dbcol.setcolumnWidth(w);
                    columnType.add(dbcol);
                    header.add(name);
                }
                //读出各行,并按列类型进行格式化输出;
                DecimalFormat myFormatter = new DecimalFormat("###,###,###,###.##");
                String sqlInt = "int,bigint,tinyint,smallint";
                String sqlFloat = "float,real,money,numeric";
                while (SqlResult.next()) {
                    Vector row = new Vector();
                    if (addSerial) {
                        Integer r = Integer.valueOf(rowNo);
                        row.add(r);
                        rowNo++;
                    }
                    for (int i = 1; i <= columnCount; i++) {
                        String type = metaData.getColumnTypeName(i);
                        if (type.equals("int") || type.equals("tinyint") || type.equals("smallit")) {
                            int intCell = SqlResult.getInt(i);
                            row.add(Integer.valueOf(intCell));
                        } else if (type.equals("bigint")) {
                            long longCell = SqlResult.getLong(i);
                            row.add(Long.valueOf(longCell));
                        } else if (type.equals("float") || type.equals("numeric") || type.equals("real")) {
                            float floatCell = SqlResult.getFloat(i);
                            row.add(Float.valueOf(floatCell));
                        } else if (type.equals("money")) {
                            double doubleCell = SqlResult.getDouble(i);
                            row.add(Double.valueOf(doubleCell));
                        } else {
                            String s = SqlResult.getString(i);
                            if (s == null) {
                                s = "";
                            }
                            row.add(s);
                        }
                    }
                    content.add(row);
                }
                SqlResult.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBTableModel.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning(ex.getLocalizedMessage()).setVisible(true);
        }
    }
}

class SetTableData implements Runnable {

    Connection conn;
    String sql;
    JTable table;
    Vector content;
    Vector header;
    SQLStatus st;
    boolean isColorGrid = false;

    public SetTableData(JTable table,Vector content, Vector header, SQLStatus st, boolean isColorGrid) {
        this.table=table;
        this.content = content;
        this.header = header;
        this.st = st;
        this.isColorGrid = isColorGrid;
    }

    public void run() {
        int counter = 1;
        while (!st.getStatus()) {
            System.out.println("Thread3:SetTableData--Waiting.." + counter);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(JDBTableModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            counter++;
        }
        System.out.println("Thread3:SetTableData--setData()");
        setData(table);
        System.out.println("Thread3:SetTableData--setData() finish !");
    }

    public synchronized void setData(JTable table) {
        TableModel mytablemodel = new DefaultTableModel(content, header) {
            //此方法可以排序

            @Override
            public Class<?> getColumnClass(int column) {
                if (this.getRowCount() > 0 && column < this.getColumnCount()) {
                    if (getValueAt(0, column) != null) {
                        return getValueAt(0, column).getClass();
                    } else {
                        return Object.class;
                    }
                } else {
                    return Object.class;
                }
            }
        };
        table.setModel(mytablemodel);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(mytablemodel);
        table.setRowSorter(sorter);
        fitTableColumns(table);//表格列宽自适应
        if (isColorGrid) {
            makeFace(table);//设置表格颜色
        }
    }
    //表格列宽自适应方法

    private synchronized void fitTableColumns(JTable myTable) {
        JTableHeader header = myTable.getTableHeader();
        int rowCount = myTable.getRowCount();

        Enumeration columns = myTable.getColumnModel().getColumns();
        while (columns.hasMoreElements()) {
            TableColumn column = (TableColumn) columns.nextElement();
            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            int width = (int) myTable.getTableHeader().getDefaultRenderer().getTableCellRendererComponent(myTable, column.getIdentifier(), false, false, -1, col).getPreferredSize().getWidth();
            for (int row = 0; row < rowCount; row++) {
                int preferedWidth = (int) myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable,
                        myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            header.setResizingColumn(column); // 此行很重要
            column.setWidth(width + myTable.getIntercellSpacing().width);
        }
    }
    //设置表格颜色

    private synchronized void makeFace(JTable table) {
        try {
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {

                @Override
                public Component getTableCellRendererComponent(JTable table,
                        Object value, boolean isSelected, boolean hasFocus,
                        int row, int column) {
                    if (row % 2 == 0) {
                        setBackground(Color.white); //设置奇数行底色
                        setForeground(Color.black);
                    } else if (row % 2 == 1) {
                        setBackground(new Color(204, 255, 255));  //设置偶数行底色
                        setForeground(Color.blue);
                    }
                    return super.getTableCellRendererComponent(table, value,
                            isSelected, hasFocus, row, column);
                }
            };
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
            }
        } catch (Exception ex) {
            Logger.getLogger(SetTableData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}