package com.zmm.tmsystem.bean;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/9/5
 * Email:65489469@qq.com
 */
public class MoneyBean extends BaseEntity{


    /**
     * id : e513a8c3-34ce-4f8b-a5ea-cd518725e49b
     * studentId : 4fdd4fec-9c8b-4086-8894-1144be9487c5
     * surplus : 0
     * lastPay : 0
     * lastDeposit : 0
     * totalPay : 0
     * totalDeposit : 0
     * timeStr : null
     * createTime : 1536132696000
     * updateTime : 1536132696000
     * active : 1
     */

    private String id;
    private String studentId;
    private float surplus;
    private float lastPay;
    private float lastDeposit;
    private float totalPay;
    private float totalDeposit;
    private long createTime;
    private long updateTime;
    private int active;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public float getSurplus() {
        return surplus;
    }

    public void setSurplus(float surplus) {
        this.surplus = surplus;
    }

    public float getLastPay() {
        return lastPay;
    }

    public void setLastPay(float lastPay) {
        this.lastPay = lastPay;
    }

    public float getLastDeposit() {
        return lastDeposit;
    }

    public void setLastDeposit(float lastDeposit) {
        this.lastDeposit = lastDeposit;
    }

    public float getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(float totalPay) {
        this.totalPay = totalPay;
    }

    public float getTotalDeposit() {
        return totalDeposit;
    }

    public void setTotalDeposit(float totalDeposit) {
        this.totalDeposit = totalDeposit;
    }


    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "MoneyBean{" +
                "id='" + id + '\'' +
                ", studentId='" + studentId + '\'' +
                ", surplus=" + surplus +
                ", lastPay=" + lastPay +
                ", lastDeposit=" + lastDeposit +
                ", totalPay=" + totalPay +
                ", totalDeposit=" + totalDeposit +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", active=" + active +
                '}';
    }
}
