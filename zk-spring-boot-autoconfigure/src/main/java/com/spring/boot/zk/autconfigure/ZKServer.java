package com.spring.boot.zk.autconfigure;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * @author Liu Hailin
 * @create 2017-09-28 下午3:43
 **/
@Configuration
@EnableConfigurationProperties(ZKCoinfig.class)
@ConditionalOnProperty(name = "spring.boot.zk.enable", havingValue = "true")
public class ZKServer {

    @Autowired
    private ZKCoinfig config;

    private RetryPolicy retryPolicy = new ExponentialBackoffRetry( 1000, 3 );

    @Bean
    public CuratorFramework createCuratorClient() {
        /**
         * //默认创建的根节点是没有做权限控制的--需要自己手动加权限???---- ACLProvider aclProvider = new
         * ACLProvider() { private List<ACL> acl ;
         *
         * @Override public List<ACL> getDefaultAcl() { if(acl ==null){
         *           ArrayList<ACL> acl = ZooDefs.Ids.CREATOR_ALL_ACL;
         *           acl.clear(); acl.add(new ACL(Perms.ALL, new Id("auth",
         *           "admin:admin") )); this.acl = acl; } return acl; }
         * @Override public List<ACL> getAclForPath(String path) { return acl; }
         *           }; String scheme = "digest"; byte[] auth =
         *           "admin:admin".getBytes();
         */
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder().connectString( config.getCluster() )
            .retryPolicy( retryPolicy ).sessionTimeoutMs( config.getSessionTimeOut() )
            .connectionTimeoutMs( config.getConnectionTimeOut() );

        if (!StringUtils.isEmpty( config.getNameSpace() )) {
            builder.namespace( config.getNameSpace() );
        }
        if (!StringUtils.isEmpty( config.getUserName() ) && !StringUtils.isEmpty( config.getPassword() )) {
            builder.authorization( "digest", config.buildDigestString().getBytes() );
            builder.aclProvider( config.getACLProvider() );
        }

        CuratorFramework client = builder.build();
        client.start();
        try {
            client.blockUntilConnected();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return client;
    }

}
