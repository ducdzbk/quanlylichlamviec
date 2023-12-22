package com.fds.quanlilichlamviec.action;

import com.fds.quanlilichlamviec.dto.req.ThanhVienReqDTO;

import com.fds.quanlilichlamviec.entity.T_Model.ThanhVien;
import org.springframework.data.domain.Page;

public interface ThanhVienAction {
    ThanhVien findThanhVien(String id);

    ThanhVien addThanhVien(ThanhVienReqDTO thanhVienReqDTO);

    ThanhVien updateThanhVien(String id, ThanhVienReqDTO thanhVienReqDTO);

    void deleteThanhVien(String id);

    Page<ThanhVien> filter(String keyword, Integer page, Integer size,
                          String orderFields, String orderTypes);
}


