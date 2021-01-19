package com.gujun.database.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author : gujun
 * date   : 2021/1/18 17:12
 * desc   :
 */
@Entity
public class TestDataEntity {

    @Id
    Long id_;

    String name;

    @Generated(hash = 1090166979)
    public TestDataEntity(Long id_, String name) {
        this.id_ = id_;
        this.name = name;
    }

    @Generated(hash = 1154556664)
    public TestDataEntity() {
    }

    public Long getId_() {
        return this.id_;
    }

    public void setId_(Long id_) {
        this.id_ = id_;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
