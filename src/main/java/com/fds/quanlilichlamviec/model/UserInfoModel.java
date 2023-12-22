package com.fds.quanlilichlamviec.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.quanlilichlamviec.entity.T_Model.ThanhVien;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class UserInfoModel {

    private static final String CLAIMS = "claims";
    private static final String EMAIL = "email";
    private static final String NAME = "name";
    private static final String IDENTITY_SYSTEM = "identity_system";
    private static final String PREFERRED_USERNAME = "preferred_username";
    private static final String SUBJECT = "subject";
    public String email = StringPool.BLANK;
    public String fullName = StringPool.BLANK;
    public ThanhVien thanhVien = new ThanhVien(false);
    public String id = StringPool.BLANK;
    public JSONObject principalObj;
    public NguoiThucHien nguoiThucHien = new NguoiThucHien();
    public boolean isAdmin;

    public String userName = StringPool.BLANK;

    public UserInfoModel() {
    }


    public JSONObject toJSON() {

        JSONObject object = new JSONObject(this);

        return object;
    }

}
