package com.fds.quanlilichlamviec.context;

/**
 * @author trungnt
 */
public interface ServiceContextHolderStrategy {

    void clearContext();

    ServiceContext getContext();

    void setContext(ServiceContext context);

    ServiceContext createEmptyContext();
}
