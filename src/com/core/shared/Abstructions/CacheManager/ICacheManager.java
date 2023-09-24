package com.core.shared.Abstructions.CacheManager;

import com.core.shared.Abstructions.LoggerManager.ILoggerManager;

public interface ICacheManager{
    public Object put(Object group,Object key,Object value);
    public Object get(Object group,Object key);
    public Object delete(Object group,Object key);
    public void clear(Object group);
    public ICacheManager init(ILoggerManager logger);
}
