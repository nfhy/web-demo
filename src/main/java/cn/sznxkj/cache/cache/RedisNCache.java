package cn.sznxkj.cache.cache;

import redis.clients.jedis.Jedis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by wangll on 2017/1/16.
 */
public class RedisNCache implements NCache {

    private static Jedis cache;

    private RedisNCache(){}

    private static volatile RedisNCache instance;

    public static RedisNCache getInstance(String url, int port) {
        if (null == instance) {
            synchronized (RedisNCache.class) {
                if (null == instance) {
                    instance = new RedisNCache();
                    cache = new Jedis(url, port);
                }
            }
        }
        return instance;
    }

    @Override
    public Object getByKey(String key) {
        return SerializeUtil.unserialize(cache.get(key.getBytes()));
    }

    @Override
    public boolean containsKey(String key) {
        return cache.exists(key.getBytes());
    }

    @Override
    public void putByKeyValue(String key, Object value) {
        System.out.println(key + value.toString());
        long n = System.currentTimeMillis();
        cache.set(key.getBytes(), SerializeUtil.serialize(value));
        System.out.println(System.currentTimeMillis() - n);
    }

    @Override
    public void clear() {
    }

    @Override
    public NCacheType cacheType() {
        return NCacheType.REDIS;
    }

    static class SerializeUtil {
        static byte[] serialize(Object object) {
            ObjectOutputStream oos;
            ByteArrayOutputStream baos;
            try {
//序列化
                baos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(baos);
                oos.writeObject(object);
                byte[] bytes = baos.toByteArray();
                return bytes;
            } catch (Exception e) {

            }
            return null;
        }

        static Object unserialize(byte[] bytes) {
            ByteArrayInputStream bais;
            try {
//反序列化
                bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                return ois.readObject();
            } catch (Exception e) {

            }
            return null;
        }
    }

}
