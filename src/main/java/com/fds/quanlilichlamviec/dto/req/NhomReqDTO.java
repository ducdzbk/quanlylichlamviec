package com.fds.quanlilichlamviec.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.quanlilichlamviec.entity.ExtraModel.Nhom_ThanhVien;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NhomReqDTO {

    @JsonProperty("MaNhom")

    public String maNhom = StringPool.BLANK;

    @JsonProperty("TenNhom")

    public String tenNhom = StringPool.BLANK;

    @JsonProperty("Nhom_ThanhVien")

    public List<Nhom_ThanhVien> nhom_thanhVien = new ArrayList<>();
}
