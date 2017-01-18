package cn.sznxkj.work;

import cn.sznxkj.cache.cache.NCacheType;
import cn.sznxkj.cache.datas.DataProcess;
import cn.sznxkj.exposed.MyDao;
import cn.sznxkj.exposed.SpringContextUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by wangll on 2017/1/16.
 */
public class DBDataProcess implements DataProcess{

    private MyDao myDao;

    private String key;
    private String sql;
    private String[] params;

    public DBDataProcess(String key, String sql, String ... params) {
        this.sql = sql;
        this.params = params;
        this.key = key;
        this.myDao = SpringContextUtil.getBean(MyDao.class);
    }

    @Override
    public HashMap<String, Object> process() {
        return null;
    }

    @Override
    public NCacheType getCacheType() {
        return NCacheType.REDIS;
    }
}
