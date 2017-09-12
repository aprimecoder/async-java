package com.patsnap.inno.common.async;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created by zhong.zhao on 2017/9/11.
 * Email zhaozhong@patsnap.com
 */
public class TaskManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskManager.class);

    public String submit(Callable callable,final String taskId) {

        LOGGER.info("thread : {} submit task : {}",Thread.currentThread().getId(),taskId);

        final TaskHolder taskHolder = TaskThreadLocal.getTaskHolder();

        taskHolder.set(taskId,null);

        ListenableFuture listenableFuture
                = ExecutorManager.getInstance().getListeningExecutorService().submit(callable);

        Futures.addCallback(listenableFuture, new FutureCallback<Object>() {

            public void onSuccess(Object result) {

                taskHolder.set(taskId,result);
            }

            public void onFailure(Throwable thrown) {
                taskHolder.set(taskId,thrown.getMessage());
            }
        });

        FutureHolder futureHolder = TaskThreadLocal.getFutureHolder();

        futureHolder.set(taskId,listenableFuture);

        return taskId;
    }

    public Object getResult(String taskId) {

        LOGGER.info("thread : {} task : {} get result.",Thread.currentThread().getId(),taskId);

        TaskHolder taskHolder = TaskThreadLocal.getTaskHolder();
        Object result = taskHolder.get(taskId);

        if (null == result) {
            FutureHolder futureHolder = TaskThreadLocal.getFutureHolder();
            try {
                return futureHolder.get(taskId).get();
            } catch (InterruptedException e) {
                LOGGER.info("thread : {} task : {} get result error : {}.",
                        Thread.currentThread().getId(),taskId,e.getMessage());
            } catch (ExecutionException e) {

                LOGGER.info("thread : {} task : {} get result error : {}.",
                        Thread.currentThread().getId(),taskId,e.getMessage());
            }

            return null;

        } else {

            return result;
        }

    }
}
