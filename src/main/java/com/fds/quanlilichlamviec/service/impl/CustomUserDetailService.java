package com.fds.quanlilichlamviec.service.impl;

import com.fds.flex.common.ultility.RestfullUtil;
import com.fds.quanlilichlamviec.constant.NotificationConstant;
import com.fds.quanlilichlamviec.entity.T_Model.ThanhVien;
import com.fds.quanlilichlamviec.exception.UnauthorizedException;
import com.fds.quanlilichlamviec.repository.ThanhVienRepository;
import com.fds.quanlilichlamviec.util.MessageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Service

// check use đ có trong db không, nếu có bắt đầu phân quền cho user đó

public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private ThanhVienRepository thanhVienRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ThanhVien> thanhVienOpt = thanhVienRepository.findByUsername(username);
        if (thanhVienOpt.isPresent()) {
            ThanhVien thanhVien = thanhVienOpt.get();
            try
            {
                Collection<SimpleGrantedAuthority> authorites = new HashSet<>();
                authorites.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                return new User(thanhVien.getUsername(), thanhVien.getPassword(), true, true, true, true, authorites);
            } catch (Exception ex){
                System.out.println(ex);
                throw new UnauthorizedException(RestfullUtil.RespCode.UNAUTHORIZED_ERROR.getCode(), NotificationConstant.UNAUTH_ERROR, MessageUtil.responseMessage(NotificationConstant.UNAUTH_ERROR));
            }
        } else {
            System.out.println("auth error");
            throw new UnauthorizedException(RestfullUtil.RespCode.UNAUTHORIZED_ERROR.getCode(), NotificationConstant.UNAUTH_ERROR, MessageUtil.responseMessage(NotificationConstant.UNAUTH_ERROR));
        }
    }
}
