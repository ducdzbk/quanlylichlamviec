package com.fds.quanlilichlamviec.context;

import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;

public class ServiceContextHolder {

    public static final String MODE_GLOBAL = "MODE_GLOBAL";

    private static String strategyName;

    private static ServiceContextHolderStrategy strategy;

    private static void initialize() {
        if (!StringUtils.hasText(strategyName)) {
            // Set default
            strategyName = MODE_GLOBAL;
        }
        if (strategyName.equals(MODE_GLOBAL)) {
            strategy = new GlobalServiceContextHolderStrategy();

        } else {
            // Try to load a custom strategy
            try {
                Class<?> clazz = Class.forName(strategyName);
                Constructor<?> customStrategy = clazz.getConstructor();
                strategy = (ServiceContextHolderStrategy) customStrategy.newInstance();
            } catch (Exception ex) {
                ReflectionUtils.handleReflectionException(ex);
            }
        }

    }

    public static void clearContext() {
        strategy.clearContext();
    }

    public static ServiceContext getContext() {
        return strategy.getContext();
    }

    public static void setContext(ServiceContext context) {
        strategy.setContext(context);
    }

    public static void setStrategyName(String strategyName) {
        ServiceContextHolder.strategyName = strategyName;
        initialize();
    }

    public static ServiceContextHolderStrategy getContextHolderStrategy() {
        return strategy;
    }

    public static ServiceContext createEmptyContext() {
        return strategy.createEmptyContext();
    }

}
