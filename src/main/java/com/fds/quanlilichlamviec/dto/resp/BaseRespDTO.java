package com.fds.quanlilichlamviec.dto.resp;

/**
 * @param <T>
 * @param <E>
 * @author trungnt
 */
public class BaseRespDTO<T, E> {

    public String errorCode;

    public String messageCode;

    public String message;

    public long systemTime;

    public T req;

    public E resp;

    public BaseRespDTO(String errorCode, String messageCode, String message, T req, E resp) {
        this.errorCode = errorCode;
        this.messageCode = messageCode;
        this.message = message;
        this.systemTime = System.currentTimeMillis();
        this.req = req;
        this.resp = resp;
    }
}
