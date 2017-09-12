package com.patsnap.inno.common.async;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Created by zhong.zhao on 2017/9/12.
 * Email zhaozhong@patsnap.com
 */
public class TestTaskManager {

    private static final TaskManager TASK_MANAGER = new TaskManager();

    private static final Logger LOGGER = LoggerFactory.getLogger(TestTaskManager.class);

    @Test
    public void test() {

        int taskCount = 2;

        String resultTaskId = null;
        for (int i=0;i<taskCount;i++) {

            resultTaskId = generateTask(i,resultTaskId);
        }

    }

    private String generateTask(final int i,String resultTaskId) {

        final String taskId = UUID.randomUUID().toString();
        LOGGER.info("submit a task : {}!",taskId);
        TASK_MANAGER.submit(() -> {

            LOGGER.info("begin to execute task : {}",taskId);

            Thread.sleep(2000);

            LOGGER.info("end to execute task : {}",taskId);

            return "task" + i;

        },taskId);

        if ((i == 1)&&(resultTaskId != null)) {
            LOGGER.info("begin to get task : {} result!",resultTaskId);
            final String result = (String) TASK_MANAGER.getResult(resultTaskId);
            LOGGER.info("begin to get task : {} result : {}!",resultTaskId,result);
        }

        return taskId;
    }
}
