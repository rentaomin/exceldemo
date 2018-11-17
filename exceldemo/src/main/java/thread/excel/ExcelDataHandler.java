/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ExcelDataHandler
 * Author:   Dell
 * Date:     2018/10/29 14:40
 * Description: 用于处理解析后的Excel 数据
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package thread.excel;

import domain.CellProperties;
import domain.RowProperties;
import org.apache.commons.collections4.CollectionUtils;
import thread.pattern.CacheQueueDataHandler;
import util.CommonUtil;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 * 〈Coding never to stop〉<br>
 * 〈用于处理解析后的Excel数据类〉
 *
 * @author zombie
 * @create 2018/10/29
 * @since 1.0.0
 */
public class ExcelDataHandler implements CacheQueueDataHandler {

    /**
     * 处理自己的业务逻辑
     *
     * @param vector 此处为Excel 行记录集合
     */
    public void handlerData(Vector vector) {
        // 本方法为测试方法，具体实现项目组，可在此具体实现
        this.saveRowProperties(vector);
    }


    /**
     * 该方法为测试保存数据方法，可根据需求实现自己的业务逻辑
     * 保存解析excel的行数据
     *
     * @param vector 此次需要保存的excel 数据集合
     */
    private void saveRowProperties(Vector<RowProperties> vector) {
        if (CollectionUtils.isNotEmpty(vector)) {
            String sql = "insert into pay values\n(SEQ_pay.Nextval,?,?,?,?,?, \n" +
                    "?,?,?,?,?,\n" +
                    "?,?,?,?,?, \n" +
                    "?,?,?,?,?,\n" +
                    "?,?,?,?,?, \n" +
                    "?,?,?,?,?,\n" +
                    "?,?,?,?,?)";
     /*       String sql = "insert into pay values\n(nextval('pay_id_seq'),?,?,?,?,?, \n" +
                    "?,?,?,?,?,\n" +
                    "?,?,?,?,?, \n" +
                    "?,?,?,?,?,\n" +
                    "?,?,?,?,?, \n" +
                    "?,?,?,?,?,\n" +
                    "?,?,?,?,?)";
*/
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            try {
                conn = JdbcUtil.getConn();
                conn.setAutoCommit(false);
                preparedStatement = conn.prepareStatement(sql);
                int count = 0;
                int defaultTranscationNum = CommonUtil.getDefaultTranscationCommitNumber();
                int totalSize = vector.size();
                System.out.println("当前需要保存的数据计数器：" + totalSize);
                for (RowProperties row : vector) {
                    List<CellProperties> cellPropertiesList = row.getCellPropertiesList();
                    int size = cellPropertiesList.size();
                    for (int i = 0; i < size; i++) {
                        preparedStatement.setString((i + 1), cellPropertiesList.get(i).getValue());
                    }
                    preparedStatement.addBatch();
                    count++;
                    if (count == defaultTranscationNum || count == totalSize) {
                        // 保存数据记录
                        // this.saveDataRecord(conn,count);
                        preparedStatement.executeBatch();
                        conn.commit();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                JdbcUtil.close();
            }
        }
    }

    /**
     * 保存本次导入数据库的数据记录
     *
     * @param conn  数据库连接
     * @param count 本次提交事务数
     */
    private void saveDataRecord(Connection conn, int count) {
        try {
            String recordSql = "insert into pay_record (id,tableename,tablecname,currenttranscationcommitnum)" +
                    " values( SEQ_pay_record.Nextval ,'pay','费用表',?)";
            PreparedStatement pstmt = conn.prepareStatement(recordSql);
            pstmt.setInt(1, count);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
