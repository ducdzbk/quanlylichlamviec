package com.fds.quanlilichlamviec.dto.resp;

import com.fds.quanlilichlamviec.dto.req.LoaiLichReqDTO;
import com.fds.quanlilichlamviec.entity.C_Model.LoaiLich;

public class LoaiLichRespDTO extends BaseRespDTO<LoaiLichReqDTO, LoaiLich> {
    public LoaiLichRespDTO(String errorCode, String messageCode, String message, LoaiLichReqDTO req, LoaiLich resp) {
        super(errorCode, messageCode, message, req, resp);
    }
}
