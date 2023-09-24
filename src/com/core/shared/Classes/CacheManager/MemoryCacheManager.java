package com.core.shared.Classes.CacheManager;

import java.util.HashMap;
import java.util.List;

import com.core.shared.Abstructions.CacheManager.ICacheManager;
import com.core.shared.Abstructions.LoggerManager.ILoggerManager;
import com.core.shared.Classes.LoggerManager.LoggerMessage;
import com.core.shared.Enums.LoggerMessageTypeEnum;

public class MemoryCacheManager  implements ICacheManager{

    private static MemoryCacheManager insatCacheManager;
    private static HashMap<Object,HashMap<Object,Object>> cache = new HashMap<>();
    private ILoggerManager logger;
    private MemoryCacheManager(){
        
    }

    public static MemoryCacheManager getInstance(){
        if(insatCacheManager==null){
            insatCacheManager = new MemoryCacheManager();
        }
        return insatCacheManager;
    }
    
    @Override
    public Object put(Object group,Object key, Object value) {
        try{
            HashMap<Object,Object> data = cache.get(group);
            if(data==null)
            data=new HashMap<>();

            data.put(key,value);
            cache.put(group,data);
            return value;
        }catch(Exception e){
            if(logger!=null)
                logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
        }
        return null;
    }

    @Override
    public Object get(Object group,Object key) {
        try{
            HashMap<Object,Object> data = cache.get(group);
            if(data==null)
            return null;

            Object value = data.get(key);
            return value;
        }catch(Exception e){
            if(logger!=null)
                logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
        }
        return null;
    }

    @Override
    public Object delete(Object group,Object key) {
        try{
            HashMap<Object,Object> data = cache.get(group);
            if(data==null)
            return null;

            Object value = data.get(key);

            data.remove(key);
            cache.put(group, data);
            return value;
        }catch(Exception e){
            if(logger!=null)
                logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
        }
        return null;
    }

    @Override
    public void clear(Object group) {
        try{
            cache.remove(group);
        }catch(Exception e){
            if(logger!=null)
                logger.log(new LoggerMessage(null, e.getMessage(), LoggerMessageTypeEnum.EXCEPTION));
        }
    }

    @Override
    public ICacheManager init(ILoggerManager logger) {
        this.logger=logger;
        return this;
    }
    
}
