package com.zqkh.archive.context.configuration;

import com.jovezhao.nest.amq.AMQChannelProvider;
import com.jovezhao.nest.amq.AMQProviderConfig;
import com.jovezhao.nest.ddd.event.ChannelProvider;
import com.jovezhao.nest.ddd.event.EventConfigItem;
import com.zqkh.gene.event.dto.GeneOrderStatusEventDto;
import com.zqkh.user.event.dto.UserAuthEventDto;
import com.zqkh.user.event.dto.UserFirstLoginEventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author
 * @date 2017/12/4 0004 15:30
 */
@Configuration
public class MQConfiguration {

    @Autowired
    private CloudConfigProperties cloudConfigProperties;

    @Bean
    public AMQProviderConfig getAMQProvider() {
        AMQProviderConfig providerConfig = new AMQProviderConfig();
        providerConfig.setSecretId(cloudConfigProperties.getAmq().getSecretId());
        providerConfig.setSecretKey(cloudConfigProperties.getAmq().getSecretKey());
        providerConfig.setEndpoint(cloudConfigProperties.getAmq().getEndpoint());
        return providerConfig;
    }

    @Bean
    public AMQChannelProvider getAMQChannelProvider(AMQProviderConfig providerConfig) {
        AMQChannelProvider channelProvider = new AMQChannelProvider(providerConfig);
        return channelProvider;
    }

    @Bean
    public EventConfigItem getUserAuthEvent(ChannelProvider provider) {
        EventConfigItem eventConfigItem = new EventConfigItem();
        eventConfigItem.setChannelProvider(provider);
        eventConfigItem.setEventName(UserAuthEventDto.EVENT_NAME);
        return eventConfigItem;
    }


    @Bean
    public EventConfigItem editGeneOrderStatus(ChannelProvider provider){
        EventConfigItem eventConfigItem = new EventConfigItem();
        eventConfigItem.setChannelProvider(provider);
        eventConfigItem.setEventName(GeneOrderStatusEventDto.EVENT_NAME);
        return eventConfigItem;
    }


}
