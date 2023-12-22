package com.fds.quanlilichlamviec.context;


import com.fds.quanlilichlamviec.model.UserInfoModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author trungnt
 */
@NoArgsConstructor
@AllArgsConstructor
public class ServiceContextImpl implements ServiceContext {

    private static final long serialVersionUID = 1L;

    private UserInfoModel userInfoModel;

    @Override
    public UserInfoModel getUserInfoModel() {

        return userInfoModel;
    }

    @Override
    public void setUserInfoModel(UserInfoModel userInfoModel) {
        this.userInfoModel = userInfoModel;

    }

    public static class Builder {

        private UserInfoModel userInfoModel;

        public Builder() {

        }

        public Builder setUserInfoModel(UserInfoModel userInfoModel) {
            this.userInfoModel = userInfoModel;
            return this;
        }

        public ServiceContextImpl build() {

            ServiceContextImpl contextImpl = new ServiceContextImpl();

            contextImpl.setUserInfoModel(userInfoModel);

            return contextImpl;
        }

    }

}
