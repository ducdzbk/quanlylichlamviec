package com.fds.quanlilichlamviec.entity.T_Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.quanlilichlamviec.constant.DBConstant;
import com.fds.quanlilichlamviec.entity.BaseModel;
import com.fds.quanlilichlamviec.entity.ExtraModel.LichLamViecCaNhan_ChiaSe;
import com.fds.quanlilichlamviec.entity.ExtraModel.LichNghi_LoaiLich;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document(collection = DBConstant.T_LICHLAMVIECCANHAN)
public class LichLamViecCaNhan extends BaseModel<LichLamViecCaNhan> {
    @JsonProperty("MaDinhDanh")
    @Field(value = "MaDinhDanh", order = 3)
    public String maDinhDanh = StringPool.BLANK;

    @JsonProperty("TieuDe")
    @Field(value = "TieuDe", order = 4)
    public String tieuDe = StringPool.BLANK;

    @JsonProperty("LoaiLich")
    @Field(value = "LoaiLich", order = 5)
    public LichNghi_LoaiLich loaiLich = new LichNghi_LoaiLich();

    @JsonProperty("MoTaCV")
    @Field(value = "MoTaCV", order = 6)
    public String moTaCV = StringPool.BLANK;

    @JsonProperty("ChiaSe")
    @Field(value = "ChiaSe", order = 7)
    public List<LichLamViecCaNhan_ChiaSe> chiaSe=new ArrayList<>();

    @JsonProperty("NgayBatDau")
    @Field(value = "NgayBatDau", order = 8)
    public Date ngayBatDau ;
    @JsonProperty("NgayKetThuc")
    @Field(value = "NgayKetThuc", order = 9)
    public Date ngayKetThuc;

    public LichLamViecCaNhan() {
        super(true);
    }

    public LichLamViecCaNhan(boolean isUpdate) {
        super(isUpdate);
    }

    @Override
    public void setType(String type) {
        super.setType(DBConstant.T_LICHLAMVIECCANHAN);
    }

    @Override
    public String getPrimKey() {

        String primKey = this.get_id() != null ? this.get_id().toHexString() : "";

        super.setPrimKey(primKey);

        return primKey;
    }


}
