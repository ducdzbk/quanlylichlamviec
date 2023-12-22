package com.fds.quanlilichlamviec.filter;

import com.fds.flex.common.ultility.RestfullUtil;
import com.fds.quanlilichlamviec.constant.NotificationConstant;
import com.fds.quanlilichlamviec.constant.StringPool;
import com.fds.quanlilichlamviec.context.ServiceContextHolder;
import com.fds.quanlilichlamviec.context.ServiceContextImpl;
import com.fds.quanlilichlamviec.entity.T_Model.ThanhVien;
import com.fds.quanlilichlamviec.exception.NotfoundException;
import com.fds.quanlilichlamviec.model.NguoiThucHien;
import com.fds.quanlilichlamviec.model.UserInfoModel;
import com.fds.quanlilichlamviec.service.ThanhVienService;
import com.fds.quanlilichlamviec.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@Order(1)
@Component
@Slf4j
public class RequestFilter implements Filter {

    @Autowired
    public ThanhVienService thanhVienService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        log.info(httpServletRequest.getRequestURL().toString() + "|" + httpServletRequest.getMethod() + "|" + "|"
                + httpServletRequest.getRequestURI());
        if (httpServletRequest.getRequestURI().contains("/v1/")) {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String authorization = ((HttpServletRequest) request).getHeader("Authorization");
            if (authorization != null) {

                authorization = authorization.replace("Basic ", StringPool.BLANK);
                authorization = new String(Base64.getDecoder().decode(authorization.getBytes()));
                String[] basicInfo = authorization.split(com.fds.flex.common.utility.string.StringPool.COLON);
                if (basicInfo != null && basicInfo.length == 2) {
                    String username = basicInfo[0];
                    Optional<ThanhVien> thanhVienOpt = thanhVienService.findByUsername(username);
                    if(!thanhVienOpt.isPresent()){
                        throw new NotfoundException(RestfullUtil.RespCode.NOT_FOUND_ENTRY_ERROR, NotificationConstant.THANHVIEN_DOCUMENT_NOT_FOUND, MessageUtil.responseMessage(NotificationConstant.THANHVIEN_DOCUMENT_NOT_FOUND));
                    }
                    UserInfoModel userInfoModel = new UserInfoModel();
                    userInfoModel.setThanhVien(thanhVienOpt.get());
                    userInfoModel.setUserName(username);
                    NguoiThucHien nguoiThucHien=new NguoiThucHien();
                    modelMapper.map(thanhVienOpt.get(), nguoiThucHien);
                    userInfoModel.setNguoiThucHien(nguoiThucHien);
                    // tạo contructor và set dữ liệu userInfoModel
                    ServiceContextImpl serviceContext = new ServiceContextImpl.Builder()
                            .setUserInfoModel(userInfoModel).build();
                     // set vùng là GLOBAL
                    ServiceContextHolder.setStrategyName(ServiceContextHolder.MODE_GLOBAL);
                    // lưu Servicecontext vào Holder
                    ServiceContextHolder.setContext(serviceContext);
                }

            }
        }
        chain.doFilter(request, response);
    }
}