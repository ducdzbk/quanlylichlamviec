package com.fds.quanlilichlamviec.context;

import com.fds.quanlilichlamviec.model.UserInfoModel;

import java.io.Serializable;

public interface ServiceContext extends Serializable {

    UserInfoModel getUserInfoModel();

    void setUserInfoModel(UserInfoModel userInfoModel);
}
