package com.patsnap.inno.common.async;

/**
 * Created by zhong.zhao on 2017/9/12.
 * Email zhaozhong@patsnap.com
 */
public class TaskThreadLocal {


    private static final ThreadLocal<TaskHolder> taskHolderThreadLocal = new ThreadLocal<>();

    private static final ThreadLocal<FutureHolder> futureHolderThreadLocal = new ThreadLocal<>();

    public static void setTaskHolder(TaskHolder value) {

        taskHolderThreadLocal.set(value);
    }

    public static void unsetTaskHolder() {

        taskHolderThreadLocal.remove();
    }

    public static TaskHolder getTaskHolder() {

        TaskHolder taskHolder = taskHolderThreadLocal.get();
        if (null == taskHolder) {
            taskHolderThreadLocal.set(new TaskHolder());
        }

        return taskHolderThreadLocal.get();
    }


    public static void setFutureHolder(FutureHolder futureHolder) {

        futureHolderThreadLocal.set(futureHolder);
    }

    public static void unsetFutureHolder() {

        futureHolderThreadLocal.remove();
    }

    public static FutureHolder getFutureHolder() {

        FutureHolder futureHolder = futureHolderThreadLocal.get();
        if (null == futureHolder) {
            futureHolderThreadLocal.set(new FutureHolder());
        }

        return futureHolderThreadLocal.get();
    }
}
