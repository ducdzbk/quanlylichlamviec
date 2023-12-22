package com.fds.quanlilichlamviec.config;


import com.fds.quanlilichlamviec.context.ServiceContext;
import com.fds.quanlilichlamviec.context.ServiceContextHolder;
import com.fds.quanlilichlamviec.model.NguoiThucHien;
import com.fds.quanlilichlamviec.model.UserInfoModel;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NguoiThucHienAudtiting implements AuditorAware<NguoiThucHien> {

    @Override
    public Optional<NguoiThucHien> getCurrentAuditor() {

        ServiceContext serviceContext = ServiceContextHolder.getContext();
        UserInfoModel userInfoModel = serviceContext.getUserInfoModel();

        return Optional.of(userInfoModel.getNguoiThucHien());
    }

}
