package com.fds.quanlilichlamviec.action.impl;

import com.fds.flex.common.ultility.RestfullUtil;
import com.fds.flex.common.ultility.Validator;
import com.fds.quanlilichlamviec.action.LoaiLichAction;
import com.fds.quanlilichlamviec.constant.Constant;
import com.fds.quanlilichlamviec.constant.NotificationConstant;
import com.fds.quanlilichlamviec.dto.req.LoaiLichReqDTO;
import com.fds.quanlilichlamviec.entity.C_Model.LoaiLich;
import com.fds.quanlilichlamviec.exception.BadRequestException;
import com.fds.quanlilichlamviec.exception.ConflicException;
import com.fds.quanlilichlamviec.exception.NotfoundException;
import com.fds.quanlilichlamviec.service.LoaiLichService;
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
public class LoaiLichActionImpl implements LoaiLichAction {

    @Autowired
    private LoaiLichService loaiLichService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LoaiLich findLoaiLich(String id) {
        if (Validator.isNull(id)) {
            throw new BadRequestException(RestfullUtil.RespCode.FIELD_NULL_OR_EMPRY_ERROR.getCode(),
                    NotificationConstant.LOAILICH_ID_NULL_EMPTY,
                    MessageUtil.responseMessage(NotificationConstant.LOAILICH_ID_NULL_EMPTY));
        }
        Optional<LoaiLich> loaiLichOptional = loaiLichService.findById(id);
        if (!loaiLichOptional.isPresent()) {
            throw new NotfoundException(RestfullUtil.RespCode.NOT_FOUND_ENTRY_ERROR.getCode(),
                    NotificationConstant.LOAILICH_DOCUMENT_NOT_FOUND,
                    MessageUtil.responseMessage(NotificationConstant.LOAILICH_DOCUMENT_NOT_FOUND));
        }
        return loaiLichOptional.get();
    }

    @Override
    public LoaiLich addLoaiLich(LoaiLichReqDTO loaiLichReqDTO) {
        LoaiLich loaiLich = new LoaiLich(false);

        modelMapper.map(loaiLichReqDTO, loaiLich);

        Optional<LoaiLich> loaiLichOpt = loaiLichService.findByMaMuc(loaiLich.getMaMuc());

        if (loaiLichOpt.isPresent()) {
            throw new ConflicException(RestfullUtil.RespCode.CONFLIC_ERROR.getCode(), "loaiLich.mamuc_conflic",
                    RestfullUtil.RespCode.CONFLIC_ERROR.getMsg());
        }

        // TODO set NguoiTaoLap set NguoiCapNhat

        return loaiLichService.updateLoaiLich(loaiLich, false);
    }

    @Override
    public LoaiLich updateLoaiLich(String id, LoaiLichReqDTO loaiLichReqDTO) {

        if (Validator.isNull(id)) {
            throw new BadRequestException(RestfullUtil.RespCode.FIELD_NULL_OR_EMPRY_ERROR.getCode(),
                    "loaiLich.id_null_or_empty", RestfullUtil.RespCode.FIELD_NULL_OR_EMPRY_ERROR.getMsg());
        }

        Optional<LoaiLich> loaiLichOpt = loaiLichService.findById(id);

        if (!loaiLichOpt.isPresent()) {
            throw new NotfoundException(RestfullUtil.RespCode.NOT_FOUND_ENTRY_ERROR.getCode(),
                    "loaiLich_notfound_entry", RestfullUtil.RespCode.NOT_FOUND_ENTRY_ERROR.getMsg());
        }

        LoaiLich loaiLich = loaiLichOpt.get();

        modelMapper.map(loaiLichReqDTO, loaiLich);

        loaiLichOpt = loaiLichService.findByMaMuc(loaiLich.getMaMuc());

        if (loaiLichOpt.isPresent() && !loaiLichOpt.get().get_id().toHexString().equals(id)) {
            throw new ConflicException(RestfullUtil.RespCode.CONFLIC_ERROR.getCode(), "loaiLich.mamuc_conflic",
                    RestfullUtil.RespCode.CONFLIC_ERROR.getMsg());
        }

        // TODO set NguoiTaoLap set NguoiCapNhat

        return loaiLichService.updateLoaiLich(loaiLich, true);
    }


    @Override
    public void deleteLoaiLich(String id) {
        if (Validator.isNull(id)) {
            throw new BadRequestException(RestfullUtil.RespCode.FIELD_NULL_OR_EMPRY_ERROR.getCode(),
                    "loaiLich.id_null_or_empty", RestfullUtil.RespCode.FIELD_NULL_OR_EMPRY_ERROR.getMsg());
        }

        Optional<LoaiLich> loaiLichOpt = loaiLichService.findById(id);

        if (!loaiLichOpt.isPresent()) {
            throw new NotfoundException(RestfullUtil.RespCode.NOT_FOUND_ENTRY_ERROR.getCode(),
                    "loaiLich_notfound_entry", RestfullUtil.RespCode.NOT_FOUND_ENTRY_ERROR.getMsg());
        }

        loaiLichService.deleteLoaiLich(loaiLichOpt.get());
    }


    @Override
    public Page<LoaiLich> filter(String keyword, Integer page, Integer size, String orderFields, String orderTypes) {
        if (page == -1 && size == -1) {
            page = 0;
            size = loaiLichService.countAll().intValue() > Constant.DEFAULT_MAX_PAGE_SIZE ? loaiLichService.countAll().intValue() : Constant.DEFAULT_MAX_PAGE_SIZE;
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

        Sort sort = QLLLVUtil.sortBuilder(orderFields, orderTypes, LoaiLich.class);

        Pageable pageable = PageRequest.of(page, size, sort);

        return loaiLichService.filter(keyword, pageable);
    }
}

