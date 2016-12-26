package com.bluestone.esjob.demo;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.AbstractDistributeOnceElasticJobListener;

/**
 * 作者: smartlv 日期：2016/12/20. 2. 分布式场景中仅单一节点执行的监听 若作业处理数据库数据，处理完成后只需一个节点完成数据清理任务即可。 此类型任务处理复杂，需同步分布式环境下作业的状态同步，
 * 提供了超时设置来避免作业不同步导致的死锁，请谨慎使用。
 */
public class TestDistributeOnceElasticJobListener extends AbstractDistributeOnceElasticJobListener
{
    public TestDistributeOnceElasticJobListener(long startedTimeoutMilliseconds, long completedTimeoutMilliseconds)
    {
        super(startedTimeoutMilliseconds, completedTimeoutMilliseconds);
    }

    @Override
    public void doBeforeJobExecutedAtLastStarted(ShardingContexts shardingContexts)
    {

    }

    @Override
    public void doAfterJobExecutedAtLastCompleted(ShardingContexts shardingContexts)
    {

    }
}
