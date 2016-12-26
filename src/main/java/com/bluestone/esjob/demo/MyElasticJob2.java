package com.bluestone.esjob.demo;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * 作者: smartlv 日期：2016/12/20.
 */
// Dataflow类型用于处理数据流。分别用于抓取(fetchData)和处理(processData)数据。
public class MyElasticJob2 implements DataflowJob
{
    @Override
    public List fetchData(ShardingContext shardingContext)
    {
        System.out.println(shardingContext);
        List<String> data = Lists.newArrayList();
        switch (shardingContext.getShardingItem())
        {
        case 0:
            System.out.println("get data from database by sharding item 1");// get data from database by sharding item 0
            return data;
        case 1:
            data = Lists.newArrayList(); // get data from database by sharding item 1
            return data;
        case 2:
            data = Lists.newArrayList();/// get data from database by sharding item 2
            return data;
        default:
        }
        return data;
    }

    @Override
    public void processData(ShardingContext shardingContext, List data)
    {
        // process data
        System.out.println("you have List data,you make your love");
    }
    // 流式处理数据只有fetchData方法的返回值为null或集合长度为空时，作业才停止抓取，否则作业将一直运行下去；
    // 非流式处理数据则只会在每次作业执行过程中执行一次fetchData方法和processData方法，随即完成本次作业。
    // 如果采用流式作业处理方式，建议processData处理数据后更新其状态，避免fetchData再次抓取到，从而使得作业永不停止。流式数据处理参照TbSchedule设计，适用于不间歇的数据处理。
}
