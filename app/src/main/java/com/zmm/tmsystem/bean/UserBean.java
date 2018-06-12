package com.zmm.tmsystem.bean;

import com.google.gson.Gson;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/6/6
 * Time:上午11:34
 */

public class UserBean {


    /**
     * id : 496b8ca9-d36b-4649-b89e-9495227b4708
     * phone : 13764503367
     * name : null
     * pic : null
     * datas : null
     * xp : null
     * addressId : null
     * createTime : 1528256029998
     * updateTime : 1528256029998
     */

    private String id;
    private String phone;
    private String name;
    private String pic;
    private String datas;
    private String xp;
    private String addressId;
    private long createTime;
    private long updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    public String getXp() {
        return xp;
    }

    public void setXp(String xp) {
        this.xp = xp;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
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
