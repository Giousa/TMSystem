package com.zmm.tmsystem.bean;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/17
 * Time:下午4:42
 */

public class TermBean extends BaseEntity{

    private String id;

    private String teacherId;

    private String title;

    private Integer year;

    private Integer month;

    private String term;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
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

    @Override
    public String toString() {
        return "TermBean{" +
                "id='" + id + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", term='" + term + '\'' +
                ", address='" + address + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
