package com.fds.quanlilichlamviec.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoaiLichReqDTO {
    @JsonProperty("MaMuc")
    public String maMuc;

    @JsonProperty("TenMuc")
    public String tenMuc;
}
