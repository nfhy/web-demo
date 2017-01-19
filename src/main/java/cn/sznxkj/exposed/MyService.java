package cn.sznxkj.exposed;

import cn.sznxkj.cache.NCacheClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by wangll on 2017/1/17.
 */
@Service
public class MyService {

    @Autowired
    private NCacheClient ncc;

    public void newWork(){}

    public HashMap<String, Object> cacheData(String key, String ... params) {
        ncc.getByKey(key);
        return null;
    }

}
