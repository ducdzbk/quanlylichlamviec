package com.fds.quanlilichlamviec.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fds.quanlilichlamviec.constant.StringPool;
import com.fds.quanlilichlamviec.entity.ExtraModel.LichNghi_LoaiLich;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Getter
@Setter
public class LichNghiReqDTO {
    @JsonProperty("TenLich")

    public String tenLich ;
    @JsonProperty("Nam")

    public Integer nam ;
    @JsonProperty("LoaiLich")

    public LichNghi_LoaiLich loaiLich ;
    @JsonProperty("NgayBatDau")

    public Date ngayBatDau ;
    @JsonProperty("NgayKetThuc")

    public Date ngayKetThuc;
}
