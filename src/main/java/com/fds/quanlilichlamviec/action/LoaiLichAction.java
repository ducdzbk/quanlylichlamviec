package com.fds.quanlilichlamviec.action;

import com.fds.quanlilichlamviec.dto.req.LoaiLichReqDTO;
import com.fds.quanlilichlamviec.entity.C_Model.LoaiLich;
import org.springframework.data.domain.Page;

public interface LoaiLichAction {
    LoaiLich findLoaiLich(String id);

    LoaiLich addLoaiLich(LoaiLichReqDTO loaiLichReqDTO);

    LoaiLich updateLoaiLich(String id, LoaiLichReqDTO loaiLichReqDTO);

    void deleteLoaiLich(String id);

    Page<LoaiLich> filter(String keyword, Integer page, Integer size,
                          String orderFields, String orderTypes);
}


