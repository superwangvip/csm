package Modules;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jiang Youquan
 */
//客户信息表client.dbf批量导入,restore client from dbf
public class DBFImporter9 extends Thread {

    String filename = "";
    JPanel msgPanel = null;
    JLabel msgLabel = null;

    public DBFImporter9(String filename, JPanel msgPanel, JLabel msgLabel) {
        this.filename = filename;
        this.msgPanel = msgPanel;
        this.msgLabel = msgLabel;
    }

    @Override
    public void run() {
        MainMenu.TIMER_STEP = 0;
        Date beginTime = new Date();
        System.out.println("正在批量导入数据...");
        try {
            //先清理client表数据
            Connection conn = Main.conn;
            Statement SQL = conn.createStatement();
            SQL.execute("truncate table client");
            //在批量导入数据
            bulkImportDBF(filename, msgPanel, msgLabel);
            //重新生成客户资产分布表与佣金分布表
            SQL.execute("generate_report 0");
            SQL.execute("generate_report1 0");
        } catch (IOException ex) {
            Main.logger.getLogger(DBFImporter9.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("IO异常: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (SQLException ex) {
            Main.logger.getLogger(DBFImporter9.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("sql异常: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (JDBFException ex) {
            Main.logger.getLogger(DBFImporter9.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("dbf异常_1: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (ParseException ex) {
            Main.logger.getLogger(DBFImporter9.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("dbf异常_2: " + ex.getLocalizedMessage()).setVisible(true);
        }
        msgPanel.setVisible(false);
        Date endTime = new Date();
        long elapse = (endTime.getTime() - beginTime.getTime());
        String elapseStr = "";
        if (elapse >= 1000) {
            elapseStr = elapse / 1000 + "秒";
        } else {
            elapseStr = elapse + "毫秒";
        }
        System.out.println("数据导入完毕,耗时:" + elapseStr);
        MainMenu.TIMER_STEP = 1;
    }

    //dbf数据导入:批量导入
    public static void bulkImportDBF(String filename, JPanel msgPanel, JLabel msgLabel)
            throws IOException, SQLException, JDBFException, ParseException, FileNotFoundException, SQLException {

        JDBF dbf = new JDBF(filename, null);
        Connection conn = Main.conn;
        Statement SQL = conn.createStatement();
        conn.setAutoCommit(false);
        int size = 100;
        int maxRec = dbf.header.getNumberOfRecords();
        int m = maxRec / size;
        int m1 = maxRec % size;
        ProgressValues pv = new ProgressValues(0, maxRec);
        ProgressBar pb = new ProgressBar("导入client.dbf", pv);
        System.out.println("\n 读取记录块：记录总数：" + maxRec + ",块大小:" + size + ",块个数：" + m + ",零头：" + m1);
        //1.=================先处理整块数据===================
        for (int i = 0; i < m; i++) {
            Object records[][] = dbf.readBlock(i * size, size);
            SQL.clearBatch();
            for (int j = 0; j < size; j++) {
                Object aobj[] = records[j];//读取记录
                //取出各字段
                String fund_account = aobj[0].toString();
                String name = aobj[1].toString();
                String fund_class = aobj[2].toString();
                String account_status = aobj[3].toString();
                String total_assets = ((Double) aobj[4]).toString();
                String total_assets_h = ((Double) aobj[5]).toString();
                String total_assets_u = ((Double) aobj[6]).toString();
                String stock_market_value = ((Double) aobj[7]).toString();
                String stock_market_value_h = aobj[8].toString();
                String stock_market_value_u = aobj[9].toString();
                String stock_position_ratio = aobj[10].toString();
                String stock_position_ratio_h = aobj[11].toString();
                String stock_position_ratio_u = aobj[12].toString();
                String commision_contribution = aobj[13].toString();
                String commision_contribution_h = aobj[14].toString();
                String commision_contribution_u = aobj[15].toString();
                String agent = aobj[16].toString();
                String telephone = aobj[17].toString();
                String mobile = aobj[18].toString();
                String email = aobj[19].toString();
                String QQ = aobj[20].toString();
                String investment_style = aobj[21].toString();
                String service_type1 = aobj[22].toString();
                String service_type2 = aobj[23].toString();
                String service_type3 = aobj[24].toString();
                String service_type4 = aobj[25].toString();
                String service_type5 = aobj[26].toString();
                String financing_product = aobj[27].toString();
                String service_person_id = aobj[28].toString();
                String service_person = aobj[29].toString();
                String satisfaction_degree = aobj[30].toString();
                String back_caller_id = aobj[31].toString();
                String back_caller = aobj[32].toString();
                String back_calling_time = aobj[33].toString();
                String satisfaction_degree1 = aobj[34].toString();
                String summary = aobj[35].toString();
                String memo = aobj[36].toString();
                String update_time = aobj[37].toString();
                String memoS = aobj[38].toString();
                String assign_time = aobj[39].toString();
                String assign_time1 = aobj[40].toString();
                String call_back_flag = aobj[41].toString();
                String client_class = aobj[42].toString();
                String match_amount = aobj[43].toString();
                String match_amount_h = aobj[44].toString();
                String match_amount_u = aobj[45].toString();
                String QQgroup = aobj[46].toString();
                String opendate = aobj[47].toString();
                String contract_flag = aobj[48].toString();
                String contract_time = aobj[49].toString().trim();
                String id_number = aobj[50].toString().trim();
                String fee_jsxf = aobj[51].toString().trim();
                String contract_analyst_id = aobj[52].toString().trim();
                String contract_analyst = aobj[53].toString().trim();
                String operate_way = aobj[54].toString().trim();
                String agent_status = aobj[55].toString().trim();
                String turnover_rate = aobj[56].toString().trim();
                String commsion_ratio = aobj[57].toString().trim();
                String top_asset = aobj[58].toString().trim();
                String credit_mark = aobj[59].toString().trim();
                String credit_opendate = aobj[60].toString().trim();
                String top_stkval = aobj[61].toString().trim();
                String repo_mark = aobj[62].toString().trim();
                String repo_opendate = aobj[63].toString().trim();
                String es_opendate = aobj[64].toString().trim();
                //构造SQL插入语句
                String SqlStatement = "insert into client values(";
                SqlStatement = SqlStatement + fund_account + ",'" + name + "','" + fund_class + "','";
                SqlStatement = SqlStatement + account_status + "'," + total_assets + ",";
                SqlStatement = SqlStatement + total_assets_h + "," + total_assets_u + "," + stock_market_value;
                SqlStatement = SqlStatement + "," + stock_market_value_h + "," + stock_market_value_u + ",";
                SqlStatement = SqlStatement + stock_position_ratio + "," + stock_position_ratio_h + ",";
                SqlStatement = SqlStatement + stock_position_ratio_u + "," + commision_contribution + ",";
                SqlStatement = SqlStatement + commision_contribution_h + "," + commision_contribution_u + ",'";
                SqlStatement = SqlStatement + agent + "','" + telephone + "','" + mobile + "','" + email + "','";
                SqlStatement = SqlStatement + QQ + "','" + investment_style + "','" + service_type1 + "','" + service_type2 + "','";
                SqlStatement = SqlStatement + service_type3 + "','" + service_type4 + "','" + service_type5 + "','" + financing_product + "',";
                SqlStatement = SqlStatement + service_person_id + ",'" + service_person + "','" + satisfaction_degree + "'," + back_caller_id + ",'";
                SqlStatement = SqlStatement + back_caller + "',";
                //修正可能的空值
                if (back_calling_time.equals("")) {
                    SqlStatement = SqlStatement + "null,'";
                } else {
                    SqlStatement = SqlStatement + "'" + back_calling_time + "','";
                }
                SqlStatement = SqlStatement + satisfaction_degree1 + "','" + summary + "','" + memo + "',";
                if (update_time.equals("")) {
                    SqlStatement = SqlStatement + "null,'" + memoS + "',";
                } else {
                    SqlStatement = SqlStatement + "'" + update_time + "','" + memoS + "',";
                }
                if (assign_time.equals("")) {
                    SqlStatement = SqlStatement + "null,";
                } else {
                    SqlStatement = SqlStatement + "'" + assign_time + "',";
                }
                if (assign_time1.equals("")) {
                    SqlStatement = SqlStatement + "null,";
                } else {
                    SqlStatement = SqlStatement + "'" + assign_time1 + "',";
                }
                SqlStatement = SqlStatement + "'" + call_back_flag + "','" + client_class + "',";
                SqlStatement = SqlStatement + match_amount + "," + match_amount_u + "," + match_amount_h + ",'" + QQgroup + "',";
                if (opendate.equals("")) {
                    SqlStatement = SqlStatement + "null";
                } else {
                    SqlStatement = SqlStatement + "'" + opendate + "'";
                }
                SqlStatement = SqlStatement + ",'" + contract_flag + "',";
                if (contract_time.equals("")) {
                    SqlStatement = SqlStatement + "null,'";
                } else {
                    SqlStatement = SqlStatement + "'" + contract_time + "','";
                }
                SqlStatement = SqlStatement + id_number + "'," + fee_jsxf + "," + contract_analyst_id + ",'" + contract_analyst + "','" + operate_way + "','" + agent_status + "'," +
                        turnover_rate + "," + commsion_ratio + "," + top_asset + ",'" + credit_mark + "','" + credit_opendate + "'," + top_stkval + ",'" + repo_mark + "','" + repo_opendate + "','" + es_opendate + "')";
                //将SQL语句加入到SQL批
                SQL.addBatch(SqlStatement);
                pv.setCurrentValue(i * size + j);
            }
            SQL.executeBatch();//执行SQL批
            conn.commit();//提交
        }
        //2.=================再处理补足一块的剩余数据===================
        Object records[][] = dbf.readBlock(m * size, m1);
        SQL.clearBatch();
        for (int j = 0; j < m1; j++) {
            Object aobj[] = records[j];//读取记录
            //取出各字段
            String fund_account = aobj[0].toString();
            String name = aobj[1].toString();
            String fund_class = aobj[2].toString();
            String account_status = aobj[3].toString();
            String total_assets = ((Double) aobj[4]).toString();
            String total_assets_h = ((Double) aobj[5]).toString();
            String total_assets_u = ((Double) aobj[6]).toString();
            String stock_market_value = ((Double) aobj[7]).toString();
            String stock_market_value_h = aobj[8].toString();
            String stock_market_value_u = aobj[9].toString();
            String stock_position_ratio = aobj[10].toString();
            String stock_position_ratio_h = aobj[11].toString();
            String stock_position_ratio_u = aobj[12].toString();
            String commision_contribution = aobj[13].toString();
            String commision_contribution_h = aobj[14].toString();
            String commision_contribution_u = aobj[15].toString();
            String agent = aobj[16].toString();
            String telephone = aobj[17].toString();
            String mobile = aobj[18].toString();
            String email = aobj[19].toString();
            String QQ = aobj[20].toString();
            String investment_style = aobj[21].toString();
            String service_type1 = aobj[22].toString();
            String service_type2 = aobj[23].toString();
            String service_type3 = aobj[24].toString();
            String service_type4 = aobj[25].toString();
            String service_type5 = aobj[26].toString();
            String financing_product = aobj[27].toString();
            String service_person_id = aobj[28].toString();
            String service_person = aobj[29].toString();
            String satisfaction_degree = aobj[30].toString();
            String back_caller_id = aobj[31].toString();
            String back_caller = aobj[32].toString();
            String back_calling_time = aobj[33].toString();
            String satisfaction_degree1 = aobj[34].toString();
            String summary = aobj[35].toString();
            String memo = aobj[36].toString();
            String update_time = aobj[37].toString();
            String memoS = aobj[38].toString();
            String assign_time = aobj[39].toString();
            String assign_time1 = aobj[40].toString();
            String call_back_flag = aobj[41].toString();
            String client_class = aobj[42].toString();
            String match_amount = aobj[43].toString();
            String match_amount_h = aobj[44].toString();
            String match_amount_u = aobj[45].toString();
            String QQgroup = aobj[46].toString();
            String opendate = aobj[47].toString();
            String contract_flag = aobj[48].toString();
            String contract_time = aobj[49].toString().trim();
            String id_number = aobj[50].toString().trim();
            String fee_jsxf = aobj[51].toString().trim();
            String contract_analyst_id = aobj[52].toString().trim();
            String contract_analyst = aobj[53].toString().trim();
            String operate_way = aobj[54].toString().trim();
            String agent_status = aobj[55].toString().trim();
            String turnover_rate = aobj[56].toString().trim();
            String commsion_ratio = aobj[57].toString().trim();
            String top_asset = aobj[58].toString().trim();
            String credit_mark = aobj[59].toString().trim();
            String credit_opendate = aobj[60].toString().trim();
            String top_stkval = aobj[61].toString().trim();
            String repo_mark = aobj[62].toString().trim();
            String repo_opendate = aobj[63].toString().trim();
            String es_opendate = aobj[64].toString().trim();

            //构造SQL插入语句
            String SqlStatement = "insert into client values(";
            SqlStatement = SqlStatement + fund_account + ",'" + name + "','" + fund_class + "','";
            SqlStatement = SqlStatement + account_status + "'," + total_assets + ",";
            SqlStatement = SqlStatement + total_assets_h + "," + total_assets_u + "," + stock_market_value;
            SqlStatement = SqlStatement + "," + stock_market_value_h + "," + stock_market_value_u + ",";
            SqlStatement = SqlStatement + stock_position_ratio + "," + stock_position_ratio_h + ",";
            SqlStatement = SqlStatement + stock_position_ratio_u + "," + commision_contribution + ",";
            SqlStatement = SqlStatement + commision_contribution_h + "," + commision_contribution_u + ",'";
            SqlStatement = SqlStatement + agent + "','" + telephone + "','" + mobile + "','" + email + "','";
            SqlStatement = SqlStatement + QQ + "','" + investment_style + "','" + service_type1 + "','" + service_type2 + "','";
            SqlStatement = SqlStatement + service_type3 + "','" + service_type4 + "','" + service_type5 + "','" + financing_product + "',";
            SqlStatement = SqlStatement + service_person_id + ",'" + service_person + "','" + satisfaction_degree + "'," + back_caller_id + ",'";
            SqlStatement = SqlStatement + back_caller + "',";
            //修正可能的空值
            if (back_calling_time.equals("")) {
                SqlStatement = SqlStatement + "null,'";
            } else {
                SqlStatement = SqlStatement + "'" + back_calling_time + "','";
            }
            SqlStatement = SqlStatement + satisfaction_degree1 + "','" + summary + "','" + memo + "',";
            if (update_time.equals("")) {
                SqlStatement = SqlStatement + "null,'" + memoS + "',";
            } else {
                SqlStatement = SqlStatement + "'" + update_time + "','" + memoS + "',";
            }
            if (assign_time.equals("")) {
                SqlStatement = SqlStatement + "null,";
            } else {
                SqlStatement = SqlStatement + "'" + assign_time + "',";
            }
            if (assign_time1.equals("")) {
                SqlStatement = SqlStatement + "null,";
            } else {
                SqlStatement = SqlStatement + "'" + assign_time1 + "',";
            }
            SqlStatement = SqlStatement + "'" + call_back_flag + "','" + client_class + "',";
            SqlStatement = SqlStatement + match_amount + "," + match_amount_u + "," + match_amount_h + ",'" + QQgroup + "',";
            if (opendate.equals("")) {
                SqlStatement = SqlStatement + "null";
            } else {
                SqlStatement = SqlStatement + "'" + opendate + "'";
            }
            SqlStatement = SqlStatement + ",'" + contract_flag + "',";
            if (contract_time.equals("")) {
                SqlStatement = SqlStatement + "null,'";
            } else {
                SqlStatement = SqlStatement + "'" + contract_time + "','";
            }
            SqlStatement = SqlStatement + id_number + "'," + fee_jsxf + "," + contract_analyst_id + ",'" + contract_analyst + "','" + operate_way + "','" + agent_status + "'," +
                    turnover_rate + "," + commsion_ratio + "," + top_asset + ",'" + credit_mark + "','" + credit_opendate + "'," + top_stkval + ",'" + repo_mark + "','" + repo_opendate + "','" + es_opendate + "')";

            //将SQL语句加入到SQL批
            SQL.addBatch(SqlStatement);
            pv.setCurrentValue(pv.getCurrentValue() + 1);
        }
        SQL.executeBatch();//执行SQL批
        conn.commit();
        conn.setAutoCommit(true);
        SQL.close();
        dbf.close();
        pv.setCurrentValue(pv.getEndtValue());//进度结束
        msgPanel.setVisible(false);
    }
}
