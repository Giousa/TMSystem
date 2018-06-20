package com.zmm.tmsystem.bean;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/20
 * Time:下午2:07
 */

public class StudentBean extends BaseEntity {

    private String id;

    private String teacherId;

    private String childcareId;

    private String cramId;

    private String name;

    private String icon;

    private long birthday;

    private Integer type;

    private Integer grade;

    private Integer school;

    private String phone;

    private Integer signDays;

    private String address;

    private long createTime;

    private long updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getChildcareId() {
        return childcareId;
    }

    public void setChildcareId(String childcareId) {
        this.childcareId = childcareId;
    }

    public String getCramId() {
        return cramId;
    }

    public void setCramId(String cramId) {
        this.cramId = cramId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getSchool() {
        return school;
    }

    public void setSchool(Integer school) {
        this.school = school;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSignDays() {
        return signDays;
    }

    public void setSignDays(Integer signDays) {
        this.signDays = signDays;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
