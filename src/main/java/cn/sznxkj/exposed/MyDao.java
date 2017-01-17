package cn.sznxkj.exposed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * Created by wangll on 2017/1/17.
 */
@Repository
public class MyDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> query(String sql, String ... params) {
        return jdbcTemplate.queryForList(sql, params);
    }

}
