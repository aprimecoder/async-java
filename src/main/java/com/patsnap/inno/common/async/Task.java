package com.patsnap.inno.common.async;

import com.google.common.util.concurrent.FutureCallback;
import com.sun.istack.internal.Nullable;

import java.util.concurrent.Callable;

/**
 * Created by zhong.zhao on 2017/9/12.
 * Email zhaozhong@patsnap.com
 */
public interface Task extends Callable,FutureCallback {


    @Override
    Object call() throws Exception;

    @Override
    void onSuccess(@Nullable Object t);

    @Override
    void onFailure(Throwable throwable);
}
