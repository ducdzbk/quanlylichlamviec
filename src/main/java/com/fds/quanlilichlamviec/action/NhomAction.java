package com.fds.quanlilichlamviec.action;

import com.fds.quanlilichlamviec.dto.req.NhomReqDTO;

import com.fds.quanlilichlamviec.entity.T_Model.Nhom;
import org.springframework.data.domain.Page;

public interface NhomAction {
    Nhom findNhom(String id);

    Nhom addNhom(NhomReqDTO NhomReqDTO);

    Nhom updateNhom(String id, NhomReqDTO NhomReqDTO);

    void deleteNhom(String id);

    Page<Nhom> filter(String keyword, Integer page, Integer size,
                          String orderFields, String orderTypes);
}


