package cn.sznxkj.exposed;

import com.sun.tracing.dtrace.ModuleAttributes;
import net.sf.json.JSONObject;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangll on 2017/1/16.
 */
@SessionAttributes(names="test")
@Controller("expose")
@RequestMapping("/expose")
@Scope("prototype")
public class Exposed {

    @Autowired
    private MyService service;

    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @RequestMapping(value = "/cache/{dicCode}.do", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    @ResponseBody
    public void input(HttpServletRequest req, HttpServletResponse res, @PathVariable("dicCode") String dicCode, @RequestBody Map json) throws Exception {
        String msg;
        Object data;
        if (null == json || json.isEmpty()) {
            msg = "parameters can`t be null";
            data = new Object();
        }
        else {
            String[] params = json.getOrDefault("params", "").toString().split(",");
            data = service.cacheData(dicCode, params);
            msg = "success";
        }
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("msg", msg);
        res.setContentType("text/html;charset=UTF-8");
        res.getWriter().write(JSONObject.fromObject(reMap).toString());
        return;
    }

}
