package com.bluestone.esjob.demo;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;

/**
 * 作者: smartlv 日期：2016/12/20. 每台作业节点均执行的监听
 */
public class MyElasticJobListener implements ElasticJobListener
{
    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts)
    {
        System.out.println("befor job");
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts)
    {
        System.out.println("after job");
    }
}
