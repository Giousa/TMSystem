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

    private String name;

    private Integer gender;

    private String icon;

    private long birthday;

    private String phone;

    private String address;

    private String guardian1;

    private String guardian1Phone;

    private String guardian2;

    private String guardian2Phone;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGuardian1() {
        return guardian1;
    }

    public void setGuardian1(String guardian1) {
        this.guardian1 = guardian1;
    }

    public String getGuardian1Phone() {
        return guardian1Phone;
    }

    public void setGuardian1Phone(String guardian1Phone) {
        this.guardian1Phone = guardian1Phone;
    }

    public String getGuardian2() {
        return guardian2;
    }

    public void setGuardian2(String guardian2) {
        this.guardian2 = guardian2;
    }

    public String getGuardian2Phone() {
        return guardian2Phone;
    }

    public void setGuardian2Phone(String guardian2Phone) {
        this.guardian2Phone = guardian2Phone;
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
