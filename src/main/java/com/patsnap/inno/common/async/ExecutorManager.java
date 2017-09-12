package com.patsnap.inno.common.async;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhong.zhao on 2017/9/12.
 * Email zhaozhong@patsnap.com
 */
public class ExecutorManager {


    private static ExecutorManager executorManager = null;

    private ListeningExecutorService listeningExecutorService;


    private ExecutorManager() {

        init();
    }

    public static synchronized ExecutorManager getInstance() {

        if (null == executorManager) {
            executorManager = new ExecutorManager();
        }

        return executorManager;
    }

    private void init() {

        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(10);

        ExecutorService executorService
                = new ThreadPoolExecutor(5,5,1000,
                TimeUnit.MILLISECONDS,workQueue);

        listeningExecutorService = MoreExecutors.listeningDecorator(executorService);
    }

    public ListeningExecutorService getListeningExecutorService() {

        return listeningExecutorService;
    }
}
