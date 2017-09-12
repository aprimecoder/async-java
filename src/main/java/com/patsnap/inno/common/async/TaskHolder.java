package com.patsnap.inno.common.async;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhong.zhao on 2017/9/12.
 * Email zhaozhong@patsnap.com
 */
public class TaskHolder {

    private final Map<String,Object> taskMap = new HashMap<>();

    public Object get(String taskId) {

        return taskMap.get(taskId);
    }

    public void set(String taskId,Object obj) {

        taskMap.put(taskId,obj);
    }
}
