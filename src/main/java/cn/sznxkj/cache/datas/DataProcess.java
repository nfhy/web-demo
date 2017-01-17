package cn.sznxkj.cache.datas;

import cn.sznxkj.cache.cache.NCacheType;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wangll on 2017/1/15.
 */
public interface DataProcess {

    /**
     * 处理数据
     * @return
     */
    HashMap<String, Object> process();

    /**
     * 查询数据
     * @return
     */
    //ArrayList<T> draw();

    /**
     * 放入strongCache还是softCache
     * @return
     */
    NCacheType getCacheType();

}
