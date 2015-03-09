/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modules;

import java.io.*;
import java.util.*;
import java.sql.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            Logger logger1 = Logger.getLogger(Main.class.getName());
            FileHandler fileHandler = new FileHandler("客服系统日志.log", false);
            logger1.addHandler(fileHandler);
            Main.logger = logger1;
            try {
                conn = getConnection();
                setBranchName("branch.ini");
                // setBranchName("branch_UTF8.ini");
            } catch (IOException ex) {
                Main.logger.warning(ex.getMessage());
                new JFrameWarning(ex.getLocalizedMessage()).setVisible(true);
            } catch (SQLException ex) {
                Main.logger.warning(ex.getMessage());
                new JFrameWarning(ex.getLocalizedMessage()).setVisible(true);
            }
            new JFrameLogin().setVisible(true);
        } catch (Exception ex) {
            Main.logger.warning(ex.getMessage());
            new JFrameWarning(ex.getLocalizedMessage()).setVisible(true);
        }
    }

    public static void setBranchName(String filename)
            throws FileNotFoundException, IOException {
        String id = "18";
        FileReader fr = null;
        fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while (line != null) {
            if (line.startsWith("branchID")) {
                int n = line.indexOf('=');
                id = line.substring(n + 1);
                branchID = Integer.valueOf(id);
                System.out.println("branchID=" + branchID);
            }
            if (line.startsWith("branchName")) {
                int n = line.indexOf('=');
                branchName = line.substring(n + 1);
                System.out.println("branchName=" + branchName);
            }
            if (line.startsWith("MAX_IDLE_TIME")) {
                int n = line.indexOf('=');
                MAX_IDLE_TIME = Integer.valueOf(line.substring(n + 1).toString());
                System.out.println("MAX_IDLE_TIME=" + MAX_IDLE_TIME);
                MAX_IDLE_TIME = MAX_IDLE_TIME * 6;
            }
            line = br.readLine();
        }
    }

    public static Connection getConnection()
            throws IOException, SQLException {
        Properties props = new Properties();
        FileInputStream in = new FileInputStream("database.properties");
        props.load(in);
        in.close();
        String drivers = props.getProperty("jdbc.drivers");
        if (drivers != null) {
            System.setProperty("jdbc.drivers", drivers);
        }
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");
        return DriverManager.getConnection(url, username, password);
    }
    public static Connection conn;
    public static String branchName = "华西证券成都东一环路营业部";
    public static int branchID = 18;
    public static String author = "蒋友全";
    public static Logger logger;
    public static String workFlow = "先回访再服务";
    public static int MAX_IDLE_TIME = 30;//最大空闲时间，缺省值300秒
}