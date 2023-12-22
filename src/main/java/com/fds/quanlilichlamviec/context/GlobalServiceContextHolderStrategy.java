package com.fds.quanlilichlamviec.context;

import org.springframework.util.Assert;

/**
 * @author trungnt
 */
final class GlobalServiceContextHolderStrategy implements ServiceContextHolderStrategy {

    private static ServiceContext contextHolder;

    @Override
    public void clearContext() {
        contextHolder = null;
    }

    @Override
    public ServiceContext getContext() {
        if (contextHolder == null) {
            contextHolder = new ServiceContextImpl();
        }
        return contextHolder;
    }

    @Override
    public void setContext(ServiceContext context) {
        Assert.notNull(context, "Only non-null ServiceContext instances are permitted");
        contextHolder = context;
    }

    @Override
    public ServiceContext createEmptyContext() {
        return new ServiceContextImpl();
    }
}
