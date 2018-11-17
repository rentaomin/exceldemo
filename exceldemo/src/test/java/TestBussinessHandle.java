/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestBussinessHandle
 * Author:   Dell
 * Date:     2018/10/31 16:16
 * Description: 业务处理测试类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */


import util.JdbcUtil;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * 〈Coding never to stop〉<br>
 * 〈业务处理测试类〉
 *
 * @author zombie
 * @create 2018/10/31
 * @since 1.0.0
 */
public class TestBussinessHandle {


    private Connection conn;


    public void getTemplateData() {
        String sql = "select * from pay where id = 65536";

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = JdbcUtil.getConn();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Pay pay = new Pay();
                int id = resultSet.getInt("id");
                pay.setId(id);
                String operateperson = resultSet.getString("operateperson");
                pay.setOperateperson(operateperson);
                String policynum = resultSet.getString("policynum");
                pay.setPolicynum(policynum);
                String clausecode = resultSet.getString("clausecode");
                pay.setClausecode(clausecode);
                String responsibilitycode = resultSet.getString("responsibilitycode");
                pay.setResponsibilitycode(responsibilitycode);

                String expensename = resultSet.getString("expensename");
                pay.setExpensename(expensename);
                String expensetype = resultSet.getString("expensetype");
                pay.setExpensetype(expensetype);
                String idnum = resultSet.getString("idnum");
                pay.setIdnum(idnum);
                String syndromenum = resultSet.getString("syndromenum");
                pay.setSyndromenum(syndromenum);
                String personalnum = resultSet.getString("personalnum");
                pay.setPersonalnum(personalnum);

                String name = resultSet.getString("name");
                pay.setName(name);
                String outdangertime = resultSet.getString("outdangertime");
                pay.setOutdangertime(outdangertime);
                String hospitalizeddate = resultSet.getString("hospitalizeddate");
                pay.setHospitalizeddate(hospitalizeddate);
                String leavedate = resultSet.getString("leavedate");
                pay.setLeavedate(leavedate);
                String medicalcode = resultSet.getString("medicalcode");
                pay.setMedicalcode(medicalcode);

                String visitserialnum = resultSet.getString("visitserialnum");
                pay.setVisitserialnum(visitserialnum);
                String coordinatecode = resultSet.getString("coordinatecode");
                pay.setCoordinatecode(coordinatecode);
                String documentnum = resultSet.getString("documentnum");
                pay.setDocumentnum(documentnum);
                String medicalcategory = resultSet.getString("medicalcategory");
                pay.setMedicalcategory(medicalcategory);
                String diseasecode = resultSet.getString("diseasecode");
                pay.setDiseasecode(diseasecode);

                String diseasename = resultSet.getString("diseasename");
                pay.setDiseasename(diseasename);
                String leavereason = resultSet.getString("leavereason");
                pay.setLeavereason(leavereason);
                String leavediseasenum = resultSet.getString("leavediseasenum");
                pay.setLeavediseasenum(leavediseasenum);
                String leavediseasename = resultSet.getString("leavediseasename");
                pay.setLeavediseasename(leavediseasename);
                String totalmedicalexpenses = resultSet.getString("totalmedicalexpenses");
                pay.setTotalmedicalexpenses(totalmedicalexpenses);

                String overalexpenses = resultSet.getString("overalexpenses");
                pay.setOveralexpenses(overalexpenses);
                String complianceexpenses = resultSet.getString("complianceexpenses");
                pay.setComplianceexpenses(complianceexpenses);
                String bigdisaseexpenses = resultSet.getString("bigdisaseexpenses");
                pay.setBigdisaseexpenses(bigdisaseexpenses);
                String medicalexpenses = resultSet.getString("medicalexpenses");
                pay.setMedicalexpenses(medicalexpenses);
                String uncomplianceexpenses = resultSet.getString("uncomplianceexpenses");
                pay.setUncomplianceexpenses(uncomplianceexpenses);

                String medicalpersoncategory = resultSet.getString("medicalpersoncategory");
                pay.setMedicalcategory(medicalcategory);
                String riskcategory = resultSet.getString("riskcategory");
                pay.setRiskcategory(riskcategory);
                String selfpayamount = resultSet.getString("selfpayamount");
                pay.setSelfpayamount(selfpayamount);
                String satisifyrangepay = resultSet.getString("satisifyrangepay");
                pay.setSatisifyrangepay(satisifyrangepay);
                String yearofacount = resultSet.getString("yearofacount");
                pay.setYearofacount(yearofacount);

                String cleartime = resultSet.getString("cleartime");
                pay.setCleartime(cleartime);

                //规则校验
                boolean isSatisfiedRule = this.verifyDataRule(pay);

                if (isSatisfiedRule) {
                    this.handleSatisfiedData(pay, conn);
                }
                System.out.println(pay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close();
        }
    }

    /**
     * 处理符合规则的数据
     *
     * @param pay
     */
    private void handleSatisfiedData(Pay pay, Connection conn) {

        this.saveSatisfiedDataToCorePay(pay, conn);

        this.saveOperationRecord(pay, conn);
    }

    /**
     * 保存此次操作记录
     *
     * @param pay
     */
    private void saveOperationRecord(Pay pay, Connection conn) {
        String sql = "insert into pay_operation_record (id,payid,flag,errorMsg) " +
                "values (SEQ_pay_operation_record.Nextval,?,?,?) ";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, pay.getId());
            preparedStatement.setString(2, "1");
            preparedStatement.setString(3, pay.getErrorMsg());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存符合规则的数据到核心业务表
     *
     * @param pay
     */
    private void saveSatisfiedDataToCorePay(Pay pay, Connection conn) {
        String sql = "insert into pay_core values (SEQ_pay_core.Nextval," +
                "?,?,?,?,?, " +
                "?,?,?,?,?," +
                "?,?,?,?,?, " +
                "?,?,?,?,?," +
                "?,?,?,?,?, " +
                "?,?,?,?,?," +
                "?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, pay.getOperateperson());
            preparedStatement.setString(2, String.valueOf(pay.getPolicynum()));
            preparedStatement.setInt(3, Integer.valueOf(pay.getClausecode()));
            preparedStatement.setInt(4, Integer.valueOf(pay.getResponsibilitycode()));
            preparedStatement.setString(5, pay.getExpensename());

            preparedStatement.setString(6, pay.getExpensetype());
            preparedStatement.setString(7, String.valueOf(pay.getIdnum()));
            preparedStatement.setString(8, pay.getSyndromenum());
            preparedStatement.setString(9, pay.getPersonalnum());
            preparedStatement.setString(10, pay.getName());

            try {
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(pay.getOutdangertime());
                preparedStatement.setDate(11, new java.sql.Date(date.getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            preparedStatement.setString(12, pay.getHospitalizeddate());
            preparedStatement.setString(13, pay.getLeavedate());
            preparedStatement.setString(14, pay.getMedicalcode());
            preparedStatement.setString(15, pay.getVisitserialnum());

            preparedStatement.setString(16, pay.getCoordinatecode());
            preparedStatement.setString(17, pay.getDocumentnum());
            preparedStatement.setString(18, pay.getMedicalcategory());
            System.out.println("getMedicalcategory：" + pay.getMedicalcategory());
            preparedStatement.setString(19, pay.getDiseasecode());
            preparedStatement.setString(20, pay.getDiseasename());

            preparedStatement.setString(21, pay.getLeavereason());
            preparedStatement.setString(22, pay.getLeavediseasenum());
            preparedStatement.setString(23, pay.getLeavediseasename());
            preparedStatement.setString(24, pay.getTotalmedicalexpenses());
            preparedStatement.setString(25, pay.getOveralexpenses());

            preparedStatement.setString(26, pay.getComplianceexpenses());
            preparedStatement.setString(27, pay.getBigdisaseexpenses());
            preparedStatement.setString(28, pay.getMedicalexpenses());
            preparedStatement.setString(29, pay.getUncomplianceexpenses());
            preparedStatement.setString(30, pay.getMedicalpersoncategory());

            preparedStatement.setString(31, pay.getRiskcategory());
            preparedStatement.setString(32, pay.getSelfpayamount());
            preparedStatement.setString(33, pay.getSatisifyrangepay());
            preparedStatement.setString(34, pay.getYearofacount());
            preparedStatement.setString(35, pay.getCleartime());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 业务规则判断
     *
     * @param pay
     */
    private boolean verifyDataRule(Pay pay) {
        boolean isSatisfiedRule = true;
        if (pay != null) {
            // operateperson 长度是符满足200
            String operateperson = pay.getOperateperson();
            if (operateperson.length() > 200) {
                isSatisfiedRule = false;
                String errorMsg = "operateperson 字段内容长度超出规定范围！";
                pay.setErrorMsg(errorMsg);
            }
        }
        return isSatisfiedRule;
    }

    public static void main(String[] args) {
        TestBussinessHandle testBussinessHandle = new TestBussinessHandle();
        testBussinessHandle.getTemplateData();
    }
}
