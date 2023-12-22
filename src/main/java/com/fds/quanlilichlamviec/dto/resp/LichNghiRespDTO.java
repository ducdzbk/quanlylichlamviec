package com.fds.quanlilichlamviec.dto.resp;

import com.fds.quanlilichlamviec.dto.req.LichNghiReqDTO;
import com.fds.quanlilichlamviec.entity.T_Model.LichNghi;

public class LichNghiRespDTO extends BaseRespDTO<LichNghiReqDTO, LichNghi> {
    public LichNghiRespDTO(String errorCode, String messageCode, String message, LichNghiReqDTO req, LichNghi resp) {
        super(errorCode, messageCode, message, req, resp);
    }
}
