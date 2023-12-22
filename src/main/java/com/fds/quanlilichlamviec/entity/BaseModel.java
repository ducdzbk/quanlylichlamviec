package com.fds.quanlilichlamviec.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fds.flex.common.ultility.UuidGenerator;
import com.fds.quanlilichlamviec.constant.DBConstant;
import com.fds.quanlilichlamviec.model.NguoiThucHien;
import com.fds.quanlilichlamviec.util.DateTimeUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.Date;


@Slf4j
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseModel<T extends Serializable> implements Serializable, Cloneable {
    @Id
    @MongoId(value = FieldType.OBJECT_ID)//đánh dấu là duy nhất, và tự động tạo id khi có đối tượng mới
    @Field(value = "_id", order = 0)//th tự trường
    @JsonIgnore// không chuyển sang JSON
    public ObjectId _id;
    @Field(value = "uuid", order = 1)
    @Indexed(unique = true)// trường chỉ mục và c tính duy nhất
    public String uuid = "";
    @JsonProperty("@type")
    @Field(value = DBConstant.TYPE, order = 2)//
    public String type;

    @JsonProperty("NguoiTaoLap")
    @Field(value = "NguoiTaoLap", order = 96)
    @CreatedBy
    public NguoiThucHien nguoiTaoLap;

    @LastModifiedBy
    @JsonProperty("NguoiCapNhat")
    @Field(value = "NguoiCapNhat", order = 97)
    public NguoiThucHien nguoiCapNhat;
    @JsonProperty("ThoiGianTao")//chỉ định tên thuộc tính JSON của trường
    @JsonFormat(pattern = DateTimeUtils._GLOBAL_TIME_FORMAT)
    @Field(value = "ThoiGianTao", order = 98)
    @CreatedDate
    public Date thoiGianTao;
    @JsonProperty("ThoiGianCapNhat")
    @JsonFormat(pattern = DateTimeUtils._GLOBAL_TIME_FORMAT)
    @Field(value = "ThoiGianCapNhat", order = 99)
    @LastModifiedDate
    public Date thoiGianCapNhat;
    @Transient // -> ko mapping vao db, không muốn seriable
    public String primKey;

    @Transient // -> ko mapping vao db
    @JsonIgnore
    public boolean isUpdate = false;



    public BaseModel(boolean isUpdate) {
        this.isUpdate = isUpdate;
        Date now = new Date();
        if (!isUpdate) {
            this.setThoiGianTao(now);
            this.setThoiGianCapNhat(now);
            this.setUuid(UuidGenerator.random(this.getClass()));
        } else {
            this.setThoiGianCapNhat(now);
        }
    }

    public T clone() throws CloneNotSupportedException {
        T obj = (T) super.clone();

        return obj;
    }

    public T cloneExclude(String[] filedNames) throws CloneNotSupportedException {
        T obj = clone();
        if (filedNames != null) {
            try {
                for (String filedName : filedNames) {

                    obj.getClass().getField(filedName).set(obj, null);
                }
            } catch (Exception e) {
                log.warn(e.getMessage());
            }
        }
        return obj;
    }


    // tạo ra một bản sao và trước khi sử dụng nó bạn muốn thay đổi 1 vài thuộc tính thì s dùng cloneWithUpdate
    public T cloneWithUpdate(Object... data) throws CloneNotSupportedException {
        T obj = clone();

        if (data != null && data.length % 2 == 0) {
            try {
                for (int i = 0; i < data.length; i = i + 2) {
                    String filedName = (String) data[i];
                    Object value = data[i + 1];
                    log.info("cloneWithUpdate: field = {}, value = {}", filedName, value);
                    obj.getClass().getField(filedName).set(obj, value);
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        } else {
            log.error("data array is null or data length is not even number");
        }
        return obj;
    }

    public T update(Object obj, Object... data) {
        if (data != null && data.length % 2 == 0) {
            try {
                for (int i = 0; i < data.length; i = i + 2) {
                    String filedName = (String) data[i];
                    Object value = data[i + 1];
                    log.info("update: field = {}, value = {}", filedName, value);
                    obj.getClass().getField(filedName).set(obj, value);
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        } else {
            log.error("data array is null or data length is not even number");
        }
        System.out.println("done");
        return (T) obj;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(Date thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public Date getThoiGianCapNhat() {
        return thoiGianCapNhat;
    }

    public void setThoiGianCapNhat(Date thoiGianCapNhat) {
        this.thoiGianCapNhat = thoiGianCapNhat;
    }

    public String getPrimKey() {
        return primKey;
    }

    public void setPrimKey(String primKey) {
        this.primKey = primKey;
    }

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean update) {
        isUpdate = update;
    }
}
