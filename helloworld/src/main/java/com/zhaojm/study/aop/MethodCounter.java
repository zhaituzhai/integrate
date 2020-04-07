package com.zhaojm.study.aop;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author zhaojm
 * @date 2020/4/3 22:23
 */
public class MethodCounter implements Serializable {
    private HashMap<String, Integer> map = new HashMap<>();
    private int allCount;
    protected void count(Method m){
        count(m.getName());

    }

    public void count(String methodName) {
        Integer i = map.get(methodName);
        i = (null != i) ? new Integer(i + 1) : new Integer(1);
        map.put(methodName, i);
        ++allCount;
    }

    public int getCalls(String methodName) {
        Integer i = map.get(methodName);
        return null != i ? i : 0;
    }

    public int getAllCount() {
        return allCount;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == this.getClass();
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
