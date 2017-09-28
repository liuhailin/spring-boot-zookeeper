package com.spring.boot.zk.autconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Liu Hailin
 * @create 2017-09-28 上午11:55
 **/
@Configuration
@ConfigurationProperties(prefix = "spring.boot.zk")
@Data
public class ZKCoinfig {
    private String cluster;
    private String nameSpace;
    private int sessionTimeOut;
    private int connectionTimeOut;
    private String acl;
}
