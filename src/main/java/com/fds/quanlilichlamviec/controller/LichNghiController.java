package com.fds.quanlilichlamviec.controller;

import com.fds.flex.common.ultility.RestfullUtil;
import com.fds.quanlilichlamviec.action.LichNghiAction;
import com.fds.quanlilichlamviec.dto.req.LichNghiReqDTO;
import com.fds.quanlilichlamviec.dto.resp.BaseRespDTO;
import com.fds.quanlilichlamviec.dto.resp.ExceptionRespDTO;

import com.fds.quanlilichlamviec.dto.resp.LichNghiRespDTO;
import com.fds.quanlilichlamviec.entity.T_Model.LichNghi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author vietdd
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/quanlylichlamviec/lichnghi")
@Api(value = "API CURD LichNghi entity")
@Slf4j
public class LichNghiController {
    @Autowired
    private LichNghiAction lichNghiAction;

    //@PreAuthorize("hasPermission('com.fds.flex.core.data.entity.C_Model.LichNghi','UPDATE')")
    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}, produces = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @ApiOperation(value = "Create LichNghi collection", notes = "Create LichNghi collection", response = LichNghiRespDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = LichNghiRespDTO.class),
            @ApiResponse(code = 400, message = "Bad request error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 404, message = "Not found error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 409, message = "Conflic error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionRespDTO.class)})
    public ResponseEntity<?> addLichNghi(@RequestBody LichNghiReqDTO request) {

        log.debug("Get body create LichNghi: {} ", new JSONObject(request));

        LichNghi lichNghi = lichNghiAction.addLichNghi(request);

        return ResponseEntity.ok(new LichNghiRespDTO(RestfullUtil.RespCode.SUCCESS.getCode(), "success", "success", request, lichNghi));

    }

    //@PreAuthorize("hasPermission('com.fds.flex.core.data.entity.C_Model.LichNghi','UPDATE')")
    @PostMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.TEXT_PLAIN_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @ApiOperation(value = "Update LichNghi entity", notes = "Update LichNghi entity", response = LichNghiRespDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = LichNghiRespDTO.class),
            @ApiResponse(code = 400, message = "Bad request error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 404, message = "Not found error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 409, message = "Conflic error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionRespDTO.class)})
    public ResponseEntity<?> updateLichNghi(@PathVariable(name = "id") String id, @RequestBody LichNghiReqDTO request) {

        log.debug("Get body update LichNghi: {} ", new JSONObject(request));

        LichNghi LichNghi = lichNghiAction.updateLichNghi(id, request);

        return ResponseEntity.ok(new LichNghiRespDTO(RestfullUtil.RespCode.SUCCESS.getCode(), "success", "success", request, LichNghi));

    }


    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Find LichNghi by Id", notes = "Find LichNghi by Id", response = LichNghiRespDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = LichNghiRespDTO.class),
            @ApiResponse(code = 400, message = "Bad request error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 404, message = "Not found error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionRespDTO.class)})
    public ResponseEntity<?> findLichNghi(@PathVariable(name = "id") String id) {

        log.debug("Find LichNghi: {} ", id);

        LichNghi lichNghi = lichNghiAction.findLichNghi(id);

//        return ResponseEntity.ok("Duc");
        return ResponseEntity.ok(new LichNghiRespDTO(RestfullUtil.RespCode.SUCCESS.getCode(), "success", "success", null, lichNghi));

    }

    //@PreAuthorize("hasPermission('com.fds.flex.core.data.entity.C_Model.LichNghi','DELETE')")
    @DeleteMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.TEXT_PLAIN_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @ApiOperation(value = "Delete LichNghi by id", notes = "Delete LichNghi by id", response = LichNghiRespDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = LichNghiRespDTO.class),
            @ApiResponse(code = 400, message = "Bad request error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 404, message = "Not found error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 409, message = "Conflic error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionRespDTO.class)})
    public ResponseEntity<?> deleteLichNghi(@PathVariable(name = "id") String id) {

        log.debug("Delete LichNghi: {} ", id);

        lichNghiAction.deleteLichNghi(id);

        return ResponseEntity.ok(
                new BaseRespDTO<String, String>(RestfullUtil.RespCode.SUCCESS.getCode(), "success", "success", id, "Delete success"));

    }

    //@PreAuthorize("hasPermission('com.fds.flex.core.data.entity.C_Model.LichNghi','READ')")
    @GetMapping(value = "/filter", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.TEXT_PLAIN_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @ApiOperation(value = "Filter", notes = "Filter", response = Page.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = Page.class),
            @ApiResponse(code = 400, message = "Bad request error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 404, message = "Not found error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionRespDTO.class)})
    public ResponseEntity<?> filter(@RequestParam(name = "keyword", required = false) String keyword,
                                    @RequestParam(name = "thamChieu_maMuc", required = false) String thamChieu_maMuc,
                                    @RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size,
                                    @RequestParam(name = "orderFields", required = false, defaultValue = "MaMuc") String orderFields,
                                    @RequestParam(name = "orderTypes", required = false, defaultValue = "asc") String orderTypes) {

        log.debug("Filter LichNghi: keyword: {}, page:{}, size:{}, orderFields:{}, orderTypes:{}", keyword, page, size,
                orderFields, orderTypes);

        Page<LichNghi> result = lichNghiAction.filter(keyword, page, size, orderFields, orderTypes);

        return ResponseEntity.ok(result);

    }
}
