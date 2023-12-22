package com.fds.quanlilichlamviec.action;

import com.fds.quanlilichlamviec.dto.req.LichNghiReqDTO;

import com.fds.quanlilichlamviec.entity.T_Model.LichNghi;
import org.springframework.data.domain.Page;

public interface LichNghiAction {
    LichNghi findLichNghi(String id);

    LichNghi addLichNghi(LichNghiReqDTO lichNghiReqDTO);

    LichNghi updateLichNghi(String id, LichNghiReqDTO lichNghiReqDTO);

    void deleteLichNghi(String id);

    Page<LichNghi> filter(String keyword, Integer page, Integer size,
                          String orderFields, String orderTypes);
}


