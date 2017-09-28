package com.spring.boot.zk.autconfigure;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Liu Hailin
 * @create 2017-09-28 上午11:51
 **/

@Configuration
@AutoConfigureAfter(ZKServer.class)
public class ZKAutoConfigure {

    @Bean
    @ConditionalOnBean(CuratorFramework.class)
    public IZKClient createZKClient(){
        return new ZKClientImpl();
    }
}
