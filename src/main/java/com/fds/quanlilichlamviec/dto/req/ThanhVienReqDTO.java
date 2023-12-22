package com.fds.quanlilichlamviec.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fds.flex.common.utility.string.StringPool;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
public class ThanhVienReqDTO {
    @JsonProperty("HoVaTen")

    public String hoVaTen;
    @JsonProperty("ThuDienTu")

    public String thuDienTu ;
    @JsonProperty("ChucDanh")

    public String chucDanh ;
    @JsonProperty("SoDienThoai")

    public String soDienThoai ;
    @JsonProperty("TaiKhoan")

    public String taiKhoan ;
    @JsonProperty("MatKhau")

    public String matKhau ;
    @JsonProperty("VaiTroSuDung")

    public String vaiTroSuDung ;
}
