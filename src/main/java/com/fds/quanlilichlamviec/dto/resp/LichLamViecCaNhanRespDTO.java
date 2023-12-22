package com.fds.quanlilichlamviec.dto.resp;

import com.fds.quanlilichlamviec.dto.req.LichLamViecCaNhanReqDTO;
import com.fds.quanlilichlamviec.entity.T_Model.LichLamViecCaNhan;

public class LichLamViecCaNhanRespDTO extends BaseRespDTO<LichLamViecCaNhanReqDTO, LichLamViecCaNhan> {
    public LichLamViecCaNhanRespDTO(String errorCode, String messageCode, String message, LichLamViecCaNhanReqDTO req, LichLamViecCaNhan resp) {
        super(errorCode, messageCode, message, req, resp);
    }
}
