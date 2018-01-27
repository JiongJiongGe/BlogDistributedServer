package com.mybatis.rabbitmq;

import com.google.gson.Gson;
import com.mybatis.service.user.YunKaiUserService;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 消息处理中心
 *
 * Created by yunkai on 2017/8/14.
 */
@Service
public class QueueConsumer implements ChannelAwareMessageListener {

    enum DealResult {
        ACCEPT,  // 处理成功
        RETRY,   // 可以重试的错误
        REJECT,  // 无需重试的错误
    }

    private static final Logger logger = LoggerFactory.getLogger(QueueConsumer.class);

    @Autowired
    private YunKaiUserService yunKaiUserService;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        String dataId = new String(message.getBody());
        Integer id = Integer.parseInt(dataId);

        DealResult dealResult = DealResult.ACCEPT;
        try{
            yunKaiUserService.delete(id);
         } catch(Exception e){
            dealResult = DealResult.REJECT;
         } finally {
            if(dealResult == DealResult.ACCEPT){
                //通知队列消息已消费
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }else if(dealResult == DealResult.REJECT){
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
            }else{
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,true);
            }
         }
    }
}
