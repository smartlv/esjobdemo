package com.bluestone.esjob.demo;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * 作者: smartlv 日期：2016/12/20.
 */
// 接口有侵入要实现SimpleJob，蛋蛋的疼,跟quartz非常相,但是提供分片和扩容
public class MyElasticJob implements SimpleJob
{// 注册中心仅用于作业注册和监控信息存储
 // 分片项与业务处理解耦，框架只会将分片项分配至各个运行中的作业服务器，开发者需要自行处理分片项与真实数据的对应关系。
    @Override
    public void execute(ShardingContext shardingContext)
    {
        // 业务要被框架侵入，取出任务调度上下文
        System.out.println(shardingContext);

        switch (shardingContext.getShardingItem())
        {
        case 0:
            //
            System.out.println("do something by sharding item 0");
            break;
        case 1:
            System.out.println("do something by sharding item 1");
            break;
        case 2:
            System.out.println("do something by sharding item 2");
            break;
        // case n: ...
        // shardingItemParameter合理使用个性化参数可以让代码更可读，如果配置为0=北京,1=上海,2=广州，那么代码中直接使用北京，上海，广州的枚举值即可完成分片项和业务逻辑的对应关系。
        }
    }
}
// 例如：有一个遍历数据库某张表的作业，现有2台服务器。为了快速的执行作业，那么每台服务器应执行作业的50%。
// 为满足此需求，可将作业分成2片，每台服务器执行1片。作业遍历数据的逻辑应为：服务器A遍历ID以奇数结尾的数据；服务器B遍历ID以偶数结尾的数据。
// 如果分成10片，则作业遍历数据的逻辑应为：每片分到的分片项应为ID%10，而服务器A被分配到分片项0,1,2,3,4；服务器B被分配到分片项5,6,7,8,9，直接的结果就是服务器A遍历ID以0-4结尾的数据；服务器B遍历ID以5-9结尾的数据。
// Elastic-Job-Lite提供最安全的方式执行作业。将分片总数设置为1，并使用多于1台的服务器执行作业，作业将会以1主n从的方式执行。
//
// 一旦执行作业的服务器崩溃，等待执行的服务器将会在下次作业启动时替补执行。开启失效转移功能效果更好，可以保证在本次作业执行时崩溃，备机立即启动替补执行
