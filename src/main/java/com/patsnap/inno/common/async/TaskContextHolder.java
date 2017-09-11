package com.patsnap.inno.common.async;

import java.util.concurrent.Callable;

/**
 * Created by zhong.zhao on 2017/9/11.
 * Email zhaozhong@patsnap.com
 */
public class TaskContextHolder {


    public String submit(Callable callable,String taskId) {


        return taskId;
    }

    public Object getResult(String taskId) {

        return null;
    }
}
