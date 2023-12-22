package com.fds.quanlilichlamviec.dto.resp;

import com.fds.quanlilichlamviec.dto.req.LoaiLichReqDTO;
import com.fds.quanlilichlamviec.dto.req.ThanhVienReqDTO;
import com.fds.quanlilichlamviec.entity.C_Model.LoaiLich;
import com.fds.quanlilichlamviec.entity.T_Model.ThanhVien;

public class    ThanhVienRespDTO extends BaseRespDTO<ThanhVienReqDTO, ThanhVien> {
    public ThanhVienRespDTO(String errorCode, String messageCode, String message, ThanhVienReqDTO req, ThanhVien resp) {
        super(errorCode, messageCode, message, req, resp);
    }
}
