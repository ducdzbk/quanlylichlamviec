package com.fds.quanlilichlamviec.entity.ExtraModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fds.quanlilichlamviec.constant.DBConstant;
import com.fds.quanlilichlamviec.constant.StringPool;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
public class LichNghi_LoaiLich {
    @JsonProperty("type")
    @Field(value = "@type", order = 5)
    public String type = DBConstant.C_LOAILICH;

    @JsonProperty("MaMuc")
    @Field(value = "MaMuc", order = 1)
    public String maMuc = StringPool.BLANK;

    @JsonProperty("TenMuc")
    @Field(value = "TenMuc", order = 2)
    public String tenMuc = StringPool.BLANK;
}
