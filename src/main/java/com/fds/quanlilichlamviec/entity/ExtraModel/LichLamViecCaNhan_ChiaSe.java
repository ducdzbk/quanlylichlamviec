package com.fds.quanlilichlamviec.entity.ExtraModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fds.quanlilichlamviec.constant.DBConstant;
import com.fds.quanlilichlamviec.constant.StringPool;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
public class LichLamViecCaNhan_ChiaSe {
    @Field(value = "@type", order = 1)
    @JsonProperty("@type")
    public String type = DBConstant.T_THANHVIEN;
    @JsonProperty("MaDinhDanh")
    @Field(value = "MaDinhDanh", order = 2)
    public String maDinhDanh = StringPool.BLANK;
    @Field(value = "HoVaTen", order = 3)
    @JsonProperty("HoVaTen")
    public String hoVaTen = StringPool.BLANK;
    @JsonProperty("MaNhom")
    public String maNhom = StringPool.BLANK;
}
