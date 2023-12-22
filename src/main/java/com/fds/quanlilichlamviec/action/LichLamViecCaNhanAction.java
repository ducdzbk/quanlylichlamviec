package com.fds.quanlilichlamviec.action;

import com.fds.quanlilichlamviec.dto.req.LichLamViecCaNhanReqDTO;
import com.fds.quanlilichlamviec.entity.T_Model.LichLamViecCaNhan;
import org.springframework.data.domain.Page;

public interface LichLamViecCaNhanAction {
    LichLamViecCaNhan findLichLamViecCaNhan(String id);

    LichLamViecCaNhan addLichLamViecCaNhan(LichLamViecCaNhanReqDTO lichLamViecCaNhanReqDTO);

    LichLamViecCaNhan updateLichLamViecCaNhan(String id, LichLamViecCaNhanReqDTO lichLamViecCaNhanReqDTO);

    void deleteLichLamViecCaNhan(String id);

    Page<LichLamViecCaNhan> filter(String keyword, Integer page, Integer size,
                          String orderFields, String orderTypes);
}


