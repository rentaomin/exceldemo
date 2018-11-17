/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Pay
 * Author:   Dell
 * Date:     2018/10/31 16:34
 * Description: 费用实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

/**
 * 〈Coding never to stop〉<br>
 * 〈费用实体类〉
 *
 * @author zombie
 * @create 2018/10/31
 * @since 1.0.0
 */
public class Pay {

    private long id;

    private String operateperson;
    private String policynum;
    private String clausecode;
    private String responsibilitycode;
    private String expensename;
    private String expensetype;
    private String idnum;
    private String syndromenum;
    private String personalnum;
    private String name;
    private String outdangertime;
    private String hospitalizeddate;
    private String leavedate;
    private String medicalcode;
    private String visitserialnum;
    private String coordinatecode;
    private String documentnum;
    private String medicalcategory;
    private String diseasecode;
    private String diseasename;
    private String leavereason;
    private String leavediseasenum;
    private String leavediseasename;
    private String totalmedicalexpenses;
    private String overalexpenses;
    private String complianceexpenses;
    private String bigdisaseexpenses;
    private String medicalexpenses;
    private String uncomplianceexpenses;
    private String medicalpersoncategory;
    private String riskcategory;
    private String selfpayamount;
    private String satisifyrangepay;
    private String yearofacount;
    private String cleartime;

    // 参数
    private String errorMsg;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOperateperson() {
        return operateperson;
    }

    public void setOperateperson(String operateperson) {
        this.operateperson = operateperson;
    }

    public String getPolicynum() {
        return policynum;
    }

    public void setPolicynum(String policynum) {
        this.policynum = policynum;
    }

    public String getClausecode() {
        return clausecode;
    }

    public void setClausecode(String clausecode) {
        this.clausecode = clausecode;
    }

    public String getResponsibilitycode() {
        return responsibilitycode;
    }

    public void setResponsibilitycode(String responsibilitycode) {
        this.responsibilitycode = responsibilitycode;
    }

    public String getExpensename() {
        return expensename;
    }

    public void setExpensename(String expensename) {
        this.expensename = expensename;
    }

    public String getExpensetype() {
        return expensetype;
    }

    public void setExpensetype(String expensetype) {
        this.expensetype = expensetype;
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public String getSyndromenum() {
        return syndromenum;
    }

    public void setSyndromenum(String syndromenum) {
        this.syndromenum = syndromenum;
    }

    public String getPersonalnum() {
        return personalnum;
    }

    public void setPersonalnum(String personalnum) {
        this.personalnum = personalnum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOutdangertime() {
        return outdangertime;
    }

    public void setOutdangertime(String outdangertime) {
        this.outdangertime = outdangertime;
    }

    public String getHospitalizeddate() {
        return hospitalizeddate;
    }

    public void setHospitalizeddate(String hospitalizeddate) {
        this.hospitalizeddate = hospitalizeddate;
    }

    public String getLeavedate() {
        return leavedate;
    }

    public void setLeavedate(String leavedate) {
        this.leavedate = leavedate;
    }

    public String getMedicalcode() {
        return medicalcode;
    }

    public void setMedicalcode(String medicalcode) {
        this.medicalcode = medicalcode;
    }

    public String getVisitserialnum() {
        return visitserialnum;
    }

    public void setVisitserialnum(String visitserialnum) {
        this.visitserialnum = visitserialnum;
    }

    public String getCoordinatecode() {
        return coordinatecode;
    }

    public void setCoordinatecode(String coordinatecode) {
        this.coordinatecode = coordinatecode;
    }

    public String getDocumentnum() {
        return documentnum;
    }

    public void setDocumentnum(String documentnum) {
        this.documentnum = documentnum;
    }

    public String getMedicalcategory() {
        return medicalcategory;
    }

    public void setMedicalcategory(String medicalcategory) {
        this.medicalcategory = medicalcategory;
    }

    public String getDiseasecode() {
        return diseasecode;
    }

    public void setDiseasecode(String diseasecode) {
        this.diseasecode = diseasecode;
    }

    public String getDiseasename() {
        return diseasename;
    }

    public void setDiseasename(String diseasename) {
        this.diseasename = diseasename;
    }

    public String getLeavereason() {
        return leavereason;
    }

    public void setLeavereason(String leavereason) {
        this.leavereason = leavereason;
    }

    public String getLeavediseasenum() {
        return leavediseasenum;
    }

    public void setLeavediseasenum(String leavediseasenum) {
        this.leavediseasenum = leavediseasenum;
    }

    public String getLeavediseasename() {
        return leavediseasename;
    }

    public void setLeavediseasename(String leavediseasename) {
        this.leavediseasename = leavediseasename;
    }

    public String getTotalmedicalexpenses() {
        return totalmedicalexpenses;
    }

    public void setTotalmedicalexpenses(String totalmedicalexpenses) {
        this.totalmedicalexpenses = totalmedicalexpenses;
    }

    public String getOveralexpenses() {
        return overalexpenses;
    }

    public void setOveralexpenses(String overalexpenses) {
        this.overalexpenses = overalexpenses;
    }

    public String getComplianceexpenses() {
        return complianceexpenses;
    }

    public void setComplianceexpenses(String complianceexpenses) {
        this.complianceexpenses = complianceexpenses;
    }

    public String getBigdisaseexpenses() {
        return bigdisaseexpenses;
    }

    public void setBigdisaseexpenses(String bigdisaseexpenses) {
        this.bigdisaseexpenses = bigdisaseexpenses;
    }

    public String getMedicalexpenses() {
        return medicalexpenses;
    }

    public void setMedicalexpenses(String medicalexpenses) {
        this.medicalexpenses = medicalexpenses;
    }

    public String getUncomplianceexpenses() {
        return uncomplianceexpenses;
    }

    public void setUncomplianceexpenses(String uncomplianceexpenses) {
        this.uncomplianceexpenses = uncomplianceexpenses;
    }

    public String getMedicalpersoncategory() {
        return medicalpersoncategory;
    }

    public void setMedicalpersoncategory(String medicalpersoncategory) {
        this.medicalpersoncategory = medicalpersoncategory;
    }

    public String getRiskcategory() {
        return riskcategory;
    }

    public void setRiskcategory(String riskcategory) {
        this.riskcategory = riskcategory;
    }

    public String getSelfpayamount() {
        return selfpayamount;
    }

    public void setSelfpayamount(String selfpayamount) {
        this.selfpayamount = selfpayamount;
    }

    public String getSatisifyrangepay() {
        return satisifyrangepay;
    }

    public void setSatisifyrangepay(String satisifyrangepay) {
        this.satisifyrangepay = satisifyrangepay;
    }

    public String getYearofacount() {
        return yearofacount;
    }

    public void setYearofacount(String yearofacount) {
        this.yearofacount = yearofacount;
    }

    public String getCleartime() {
        return cleartime;
    }

    public void setCleartime(String cleartime) {
        this.cleartime = cleartime;
    }


    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "Pay{" +
                "id=" + id +
                ", operateperson='" + operateperson + '\'' +
                ", policynum='" + policynum + '\'' +
                ", clausecode='" + clausecode + '\'' +
                ", responsibilitycode='" + responsibilitycode + '\'' +
                ", expensename='" + expensename + '\'' +
                ", expensetype='" + expensetype + '\'' +
                ", idnum='" + idnum + '\'' +
                ", syndromenum='" + syndromenum + '\'' +
                ", personalnum='" + personalnum + '\'' +
                ", name='" + name + '\'' +
                ", outdangertime='" + outdangertime + '\'' +
                ", hospitalizeddate='" + hospitalizeddate + '\'' +
                ", leavedate='" + leavedate + '\'' +
                ", medicalcode='" + medicalcode + '\'' +
                ", visitserialnum='" + visitserialnum + '\'' +
                ", coordinatecode='" + coordinatecode + '\'' +
                ", documentnum='" + documentnum + '\'' +
                ", medicalcategory='" + medicalcategory + '\'' +
                ", diseasecode='" + diseasecode + '\'' +
                ", diseasename='" + diseasename + '\'' +
                ", leavereason='" + leavereason + '\'' +
                ", leavediseasenum='" + leavediseasenum + '\'' +
                ", leavediseasename='" + leavediseasename + '\'' +
                ", totalmedicalexpenses='" + totalmedicalexpenses + '\'' +
                ", overalexpenses='" + overalexpenses + '\'' +
                ", complianceexpenses='" + complianceexpenses + '\'' +
                ", bigdisaseexpenses='" + bigdisaseexpenses + '\'' +
                ", medicalexpenses='" + medicalexpenses + '\'' +
                ", uncomplianceexpenses='" + uncomplianceexpenses + '\'' +
                ", medicalpersoncategory='" + medicalpersoncategory + '\'' +
                ", riskcategory='" + riskcategory + '\'' +
                ", selfpayamount='" + selfpayamount + '\'' +
                ", satisifyrangepay='" + satisifyrangepay + '\'' +
                ", yearofacount='" + yearofacount + '\'' +
                ", cleartime='" + cleartime + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
