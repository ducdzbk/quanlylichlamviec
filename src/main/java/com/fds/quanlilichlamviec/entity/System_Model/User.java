package com.fds.quanlilichlamviec.entity.System_Model;

import com.fds.quanlilichlamviec.constant.DBConstant;
import com.fds.quanlilichlamviec.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends BaseModel<User> {

    private String username;
    private String password;
    private String thuDienTu;
    private String chucDanh;
    private String vaiTroSuDung;



    public User() {
        super(true);
    }

    public User(boolean isUpdate) {
        super(isUpdate);
    }

    @Override
    public void setType(String type) {
    }

    @Override
    public String getPrimKey() {

        String primKey = this.get_id() != null ? this.get_id().toHexString() : "";

        super.setPrimKey(primKey);

        return primKey;
    }

}
