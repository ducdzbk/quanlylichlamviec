package com.fds.quanlilichlamviec.entity.C_Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fds.quanlilichlamviec.constant.DBConstant;
import com.fds.quanlilichlamviec.constant.StringPool;
import com.fds.quanlilichlamviec.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = DBConstant.C_LOAILICH)
public class LoaiLich extends BaseModel<LoaiLich> {


    @JsonProperty("MaMuc")
    @Field(value = "MaMuc", order = 1)
    public String maMuc = StringPool.BLANK;

    @JsonProperty("TenMuc")
    @Field(value = "TenMuc", order = 2)
    public String tenMuc = StringPool.BLANK;

    @JsonProperty("MaNhom")
    @Field(value = "MaNhom", order = 2)
    public String maNhom = StringPool.BLANK;

    public LoaiLich() {
        super(true);
    }
    public LoaiLich(boolean isUpdate) {
        super(isUpdate);
    }

    @Override
    public void setType(String type) {
        super.setType(DBConstant.C_LOAILICH);
    }

    @Override
    public String getPrimKey() {

        String primKey = this.get_id() != null ? this.get_id().toHexString() : "";

        super.setPrimKey(primKey);

        return primKey;
    }

}
