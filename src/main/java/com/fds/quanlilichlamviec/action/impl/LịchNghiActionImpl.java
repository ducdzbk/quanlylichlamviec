package com.fds.quanlilichlamviec.action.impl;

import com.fds.flex.common.ultility.RestfullUtil;
import com.fds.flex.common.ultility.Validator;
import com.fds.quanlilichlamviec.action.LichNghiAction;
import com.fds.quanlilichlamviec.constant.Constant;
import com.fds.quanlilichlamviec.constant.NotificationConstant;
import com.fds.quanlilichlamviec.dto.req.LichNghiReqDTO;
import com.fds.quanlilichlamviec.entity.T_Model.LichNghi;
import com.fds.quanlilichlamviec.exception.BadRequestException;
import com.fds.quanlilichlamviec.exception.NotfoundException;
import com.fds.quanlilichlamviec.service.LichNghiService;
import com.fds.quanlilichlamviec.util.MessageUtil;
import com.fds.quanlilichlamviec.util.QLLLVUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LịchNghiActionImpl implements LichNghiAction {

    @Autowired
    private LichNghiService lichNghiService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LichNghi findLichNghi(String id) {
        if (Validator.isNull(id)) {
            throw new BadRequestException(RestfullUtil.RespCode.FIELD_NULL_OR_EMPRY_ERROR.getCode(),
                    NotificationConstant.LOAILICH_ID_NULL_EMPTY,
                    MessageUtil.responseMessage(NotificationConstant.LOAILICH_ID_NULL_EMPTY));
        }
        Optional<LichNghi> lichNghiOptional = lichNghiService.findById(id);
        if (!lichNghiOptional.isPresent()) {
            throw new NotfoundException(RestfullUtil.RespCode.NOT_FOUND_ENTRY_ERROR.getCode(),
                    NotificationConstant.LOAILICH_DOCUMENT_NOT_FOUND,
                    MessageUtil.responseMessage(NotificationConstant.LOAILICH_DOCUMENT_NOT_FOUND));
        }
        return lichNghiOptional.get();
    }

    @Override
    public LichNghi addLichNghi(LichNghiReqDTO lichNghiReqDTO) {
        LichNghi lichNghi = new LichNghi(false);

        modelMapper.map(lichNghiReqDTO, lichNghi);



        // TODO set NguoiTaoLap set NguoiCapNhat

        return lichNghiService.updateLichNghi(lichNghi, false);
    }

    @Override
    public LichNghi updateLichNghi(String id, LichNghiReqDTO lichNghiReqDTO) {

        if (Validator.isNull(id)) {
            throw new BadRequestException(RestfullUtil.RespCode.FIELD_NULL_OR_EMPRY_ERROR.getCode(),
                    "lichNghi.id_null_or_empty", RestfullUtil.RespCode.FIELD_NULL_OR_EMPRY_ERROR.getMsg());
        }

        Optional<LichNghi> lichNghiOpt = lichNghiService.findById(id);

        if (!lichNghiOpt.isPresent()) {
            throw new NotfoundException(RestfullUtil.RespCode.NOT_FOUND_ENTRY_ERROR.getCode(),
                    "lichNghi_notfound_entry", RestfullUtil.RespCode.NOT_FOUND_ENTRY_ERROR.getMsg());
        }

        LichNghi lichNghi = lichNghiOpt.get();

        modelMapper.map(lichNghiReqDTO, lichNghi);



        // TODO set NguoiTaoLap set NguoiCapNhat

        return lichNghiService.updateLichNghi(lichNghi, true);
    }


    @Override
    public void deleteLichNghi(String id) {
        if (Validator.isNull(id)) {
            throw new BadRequestException(RestfullUtil.RespCode.FIELD_NULL_OR_EMPRY_ERROR.getCode(),
                    "lichNghi.id_null_or_empty", RestfullUtil.RespCode.FIELD_NULL_OR_EMPRY_ERROR.getMsg());
        }

        Optional<LichNghi> lichNghiOpt = lichNghiService.findById(id);

        if (!lichNghiOpt.isPresent()) {
            throw new NotfoundException(RestfullUtil.RespCode.NOT_FOUND_ENTRY_ERROR.getCode(),
                    "lichNghi_notfound_entry", RestfullUtil.RespCode.NOT_FOUND_ENTRY_ERROR.getMsg());
        }

        lichNghiService.deleteLichNghi(lichNghiOpt.get());
    }


    @Override
    public Page<LichNghi> filter(String keyword, Integer page, Integer size, String orderFields, String orderTypes) {
        if (page == -1 && size == -1) {
            page = 0;
            size = lichNghiService.countAll().intValue() > Constant.DEFAULT_MAX_PAGE_SIZE ? lichNghiService.countAll().intValue() : Constant.DEFAULT_MAX_PAGE_SIZE;
        } else {
            if (page == null || page < 0) {
                page = 0;
            }

            if (size == null || size < 0) {
                size = Constant.DEFAULT_MIN_PAGE_SIZE;
            }


            if (size > Constant.DEFAULT_MAX_PAGE_SIZE) {
                size = Constant.DEFAULT_MAX_PAGE_SIZE;
            }
        }

        Sort sort = QLLLVUtil.sortBuilder(orderFields, orderTypes, LichNghi.class);

        Pageable pageable = PageRequest.of(page, size, sort);

        return lichNghiService.filter(keyword, pageable);
    }
}

