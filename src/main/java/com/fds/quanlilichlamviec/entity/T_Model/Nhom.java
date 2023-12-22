package com.fds.quanlilichlamviec.entity.T_Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.quanlilichlamviec.constant.DBConstant;
import com.fds.quanlilichlamviec.entity.BaseModel;

import com.fds.quanlilichlamviec.entity.ExtraModel.Nhom_ThanhVien;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = DBConstant.T_NHOM)
public class Nhom extends BaseModel {
    public Nhom() {
        super(true);
    }

    public Nhom(boolean isUpdate) {
        super(isUpdate);
    }

    @Override
    public void setType(String type) {
        super.setType(DBConstant.T_NHOM);
    }

    @Override
    public String getPrimKey() {

        String primKey = this.get_id() != null ? this.get_id().toHexString() : "";

        super.setPrimKey(primKey);

        return primKey;
    }

    @Indexed(unique = true)
    @JsonProperty("MaNhom")
    @Field(value = "MaNhom", order = 2)
    public String maNhom = StringPool.BLANK;

    @JsonProperty("MaDinhDanh")
    @Field(value = "MaDinhDanh", order = 3)
    public String maDinhDanh = StringPool.BLANK;

    @JsonProperty("TenNhom")
    @Field(value = "TenNhom", order = 4)
    public String tenNhom = StringPool.BLANK;

    @JsonProperty("Nhom_ThanhVien")
    @Field(value = "Nhom_ThanhVien", order = 5)
    public List<Nhom_ThanhVien> nhom_ThanhVien = new ArrayList<>();

}
