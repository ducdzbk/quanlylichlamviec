package com.fds.quanlilichlamviec.entity.T_Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fds.quanlilichlamviec.constant.DBConstant;
import com.fds.quanlilichlamviec.constant.StringPool;
import com.fds.quanlilichlamviec.entity.BaseModel;
import com.fds.quanlilichlamviec.entity.ExtraModel.LichNghi_LoaiLich;
import com.fds.quanlilichlamviec.util.DateTimeUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Getter

@Setter
@Document(collection = DBConstant.T_LICHNGHI)
public class LichNghi extends BaseModel<LichNghi> {
    @JsonProperty("MaDinhDanh")
    @Field(value = "MaDinhDanh", order = 3)
    public String maDinhDanh = com.fds.flex.common.utility.string.StringPool.BLANK;
    @JsonProperty("TenLich")
    @Field(value = "TenLich", order = 3)
    public String tenLich = StringPool.BLANK;
    @JsonProperty("Nam")
    @Field(value = "Nam", order = 4)
    public Integer nam = 0;
    @JsonProperty("LoaiLich")
    @Field(value = "LoaiLich", order = 5)
    public LichNghi_LoaiLich loaiLich = new LichNghi_LoaiLich();
    @JsonProperty("NgayBatDau")
    @JsonFormat(pattern = DateTimeUtils._GLOBAL_TIME_FORMAT)
    @Field(value = "NgayBatDau", order = 6)
    public Date ngayBatDau;
    @JsonProperty("NgayKetThuc")
    @JsonFormat(pattern = DateTimeUtils._GLOBAL_TIME_FORMAT)
    @Field(value = "NgayKetThuc", order = 7)
    public Date ngayKetThuc;

    public LichNghi() {
        super(true);
    }

    public LichNghi(boolean isUpdate) {
        super(isUpdate);
    }

    @Override
    public void setType(String type) {
        super.setType(DBConstant.T_LICHNGHI);
    }

    @Override
    public String getPrimKey() {

        String primKey = this.get_id() != null ? this.get_id().toHexString() : "";

        super.setPrimKey(primKey);

        return primKey;
    }

}
