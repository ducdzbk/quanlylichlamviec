package com.fds.quanlilichlamviec.entity.ExtraModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.quanlilichlamviec.constant.DBConstant;
import org.springframework.data.mongodb.core.mapping.Field;

public class Nhom_ThanhVien {
    @JsonProperty("type")
    @Field(value = "@type", order = 0)
    public String type = DBConstant.T_THANHVIEN;

    @JsonProperty("MaDinhDanh")
    @Field(value = "MaDinhDanh", order = 1)
    public  String maDinhDanh= StringPool.BLANK;

    @JsonProperty("HoVaTen")
    @Field(value = "HoVaTen", order = 2)
    public String hoVaTen = StringPool.BLANK;
}
