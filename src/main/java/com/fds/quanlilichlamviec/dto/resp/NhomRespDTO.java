package com.fds.quanlilichlamviec.dto.resp;

import com.fds.quanlilichlamviec.dto.req.LoaiLichReqDTO;
import com.fds.quanlilichlamviec.dto.req.NhomReqDTO;
import com.fds.quanlilichlamviec.entity.C_Model.LoaiLich;
import com.fds.quanlilichlamviec.entity.T_Model.Nhom;

public class NhomRespDTO extends BaseRespDTO<NhomReqDTO, Nhom> {
    public NhomRespDTO(String errorCode, String messageCode, String message, NhomReqDTO req,Nhom resp) {
        super(errorCode, messageCode, message, req, resp);
    }
}
