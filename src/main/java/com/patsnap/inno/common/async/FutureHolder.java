package com.patsnap.inno.common.async;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhong.zhao on 2017/9/12.
 * Email zhaozhong@patsnap.com
 */
public class FutureHolder {


    private final Map<String,ListenableFuture> futureMap = new HashMap<>();

    public ListenableFuture get(String taskId) {

        return futureMap.get(taskId);
    }

    public void set(String taskId,ListenableFuture listenableFuture) {

        futureMap.put(taskId,listenableFuture);
    }
}
