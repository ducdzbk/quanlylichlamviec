package com.fds.quanlilichlamviec.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.quanlilichlamviec.entity.ExtraModel.LichLamViecCaNhan_ChiaSe;
import com.fds.quanlilichlamviec.entity.ExtraModel.LichNghi_LoaiLich;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class LichLamViecCaNhanReqDTO {
    @JsonProperty("TieuDe")

    public String tieuDe ;

    @JsonProperty("LoaiLich")

    public LichNghi_LoaiLich loaiLich ;

    @JsonProperty("MoTaCV")

    public String moTaCV ;

    @JsonProperty("ChiaSe")

    public List<LichLamViecCaNhan_ChiaSe> chiaSe;

    @JsonProperty("NgayBatDau")
    public Date ngayBatDau ;

    @JsonProperty("NgayKetThuc")
    public Date ngayKetThuc;
}
