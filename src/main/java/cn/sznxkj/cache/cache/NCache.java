package cn.sznxkj.cache.cache;

/**
 * Created by wangll on 2017/1/15.
 */
public interface NCache {

    Object getByKey(String key);

    boolean containsKey(String key);

    void putByKeyValue(String key, Object value);

    void clear();

    NCacheType cacheType();

}
