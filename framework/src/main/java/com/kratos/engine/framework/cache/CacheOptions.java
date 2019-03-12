package com.kratos.engine.framework.cache;


import com.kratos.engine.framework.common.utils.TimeUtil;
import lombok.Builder;
import lombok.Getter;

/**
 * 缓存相关配置
 *
 * @author herton
 */
@Getter
@Builder(toBuilder = true)
public class CacheOptions {
    private final static int DEFAULT_INITIAL_CAPACITY = 1024;
    private final static int DEFAULT_MAXIMUM_SIZE = 65536;
    private final static int DEFAULT_EXPIRE_AFTER_ACCESS_SECONDS = (int) (5 * TimeUtil.ONE_HOUR / TimeUtil.ONE_MILLSECOND);
    private final static int DEFAULT_EXPIRE_AFTER_WRITE_SECONDS = (int) (5 * TimeUtil.ONE_HOUR / TimeUtil.ONE_MILLSECOND);

    private int initialCapacity = DEFAULT_INITIAL_CAPACITY;
    private int maximumSize = DEFAULT_MAXIMUM_SIZE;
    private int expireAfterAccessSeconds = DEFAULT_EXPIRE_AFTER_ACCESS_SECONDS;
    private int expireAfterWriteSeconds = DEFAULT_EXPIRE_AFTER_WRITE_SECONDS;
}
