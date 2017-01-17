package cn.sznxkj.cache;

import cn.sznxkj.cache.cache.*;
import cn.sznxkj.cache.datas.DataProcess;
import cn.sznxkj.cache.works.WorkPool;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wangll on 2017/1/15.
 *
 */
public class NCacheClient {

    private static HashMap<NCacheType, NCache> caches;

    public NCacheClient() {}

    private String url;
    public void setUrl(String url) { this.url = url; }
    private int port;
    public void setPort(int port) { this.port = port; }
    /**
     * 初始化ncache，获取所有work，启动工作线程，开始循环更新缓存
     */
    @PostConstruct
    public void init() {
        caches = new HashMap<>();
        caches.put(NCacheType.SOFT, SoftNCache.getInstance());
        caches.put(NCacheType.STRONG, StrongNCache.getInstance());
        caches.put(NCacheType.REDIS, RedisNCache.getInstance(url, port));
        WorkPool.start();
    }

    public Object getByKey(String key) {
        Object o = null;
        for (NCache cache : caches.values()) {
            if ((o = cache.getByKey(key)) != null) {
                return o;
            }
        }
        return o;
    }

    public static void putAllInCache(HashMap<String, Object> data, NCacheType cacheType) {
        if (null != data && ! data.isEmpty()) {
            NCache cache = caches.get(cacheType);
            if (null != cache) {
                data.keySet().forEach((key) -> {
                    cache.putByKeyValue(key, data.get(key));
                });
            }
        }
    }

    public static void addWork(String key, DataProcess dp) {
        WorkPool.addWork(key, dp);
    }

    public static void main(String ... args) {}
}
