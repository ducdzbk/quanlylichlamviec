package com.fds.quanlilichlamviec.entity.T_Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.quanlilichlamviec.constant.DBConstant;
import com.fds.quanlilichlamviec.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
@Getter
@Setter
@Document(collection = DBConstant.T_THANHVIEN)

public class ThanhVien extends BaseModel<ThanhVien> {

    @JsonProperty("MaDinhDanh")
    @Field(value = "MaDinhDanh", order = 2)
    public String maDinhDanh = StringPool.BLANK;
    @JsonProperty("HoVaTen")
    @Field(value = "HoVaTen", order = 3)
    public String hoVaTen = StringPool.BLANK;
    @JsonProperty("ThuDienTu")
    @Field(value = "ThuDienTu", order = 4)
    public String thuDienTu = StringPool.BLANK;
    @JsonProperty("ChucDanh")
    @Field(value = "ChucDanh", order = 5)
    public String chucDanh = StringPool.BLANK;
    @JsonProperty("SoDienThoai")
    @Field(value = "SoDienThoai", order = 6)
    public String soDienThoai = StringPool.BLANK;
    @JsonProperty("Username")
    @Field(value = "Username", order = 7)
    public String username = StringPool.BLANK;
    @JsonProperty("Password")
    @Field(value = "Password", order = 8)
    public String password = StringPool.BLANK;
    @JsonProperty("VaiTroSuDung")
    @Field(value = "VaiTroSuDung", order = 9)
    public String vaiTroSuDung = StringPool.BLANK;

    public ThanhVien() {
        super(true);
    }

    public ThanhVien(boolean isUpdate) {
        super(isUpdate);
    }

    @Override
    public void setType(String type) {
        super.setType(DBConstant.T_THANHVIEN);
    }

    @Override
    public String getPrimKey() {

        String primKey = this.get_id() != null ? this.get_id().toHexString() : "";

        super.setPrimKey(primKey);

        return primKey;
    }


}
