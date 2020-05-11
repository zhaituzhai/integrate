package com.zhaojm.study;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author zhaojm
 * @date 2020-05-07 11:03
 */
public class HelloWorldTest {

    @Test
    public void test() {
        List<String> strList = new ArrayList<>();
        strList.add("hello");
        strList.add("world");
        strList.forEach(s -> {
            if (s.equals("hello")) {
                return;
            }
            System.out.println(s);
        });
    }

    @Test
    public void testJOSNToString() {
        HashMap<String, String> extendMapperMap = null;
        HashMap<String, String> extendMapperMap1 = new HashMap<>();

        setExtendMapperMap(extendMapperMap);
        setExtendMapperMap(extendMapperMap1);
    }

    @Test
    public void testJOSNToMap() {
        String extendMapper = null;
        System.out.println(JSONObject.toJSONString(getExtendMapperMap(extendMapper)));
        extendMapper = "";
        System.out.println(JSONObject.toJSONString(getExtendMapperMap(extendMapper)));
        extendMapper = "null";
        System.out.println(JSONObject.toJSONString(getExtendMapperMap(extendMapper)));
    }

    public HashMap<String, String> getExtendMapperMap(String extendMapper) {
        HashMap<String, String> extendMapperMap = null;
        if (StringUtils.isNotEmpty(extendMapper) && !"null".equalsIgnoreCase(extendMapper)) {
            extendMapperMap = JSONObject.parseObject(extendMapper, HashMap.class);
        } else if ("null".equalsIgnoreCase(extendMapper) || StringUtils.isEmpty(extendMapper)) {
            extendMapperMap = new HashMap();
        }
        return extendMapperMap;
    }

    public void setExtendMapperMap(HashMap<String, String> extendMapperMap) {
        if (null == extendMapperMap) {
            extendMapperMap = new HashMap<>();
        }
        System.out.println(JSONObject.toJSONString(extendMapperMap));
    }
}
