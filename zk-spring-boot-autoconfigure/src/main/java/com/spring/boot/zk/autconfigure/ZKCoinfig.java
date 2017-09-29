package com.spring.boot.zk.autconfigure;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.curator.framework.api.ACLProvider;
import org.apache.zookeeper.ZooDefs.Perms;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;
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
    private String userName;
    private String password;

    public String buildDigestString(){
        return String.format( "%s:%s",getUserName(),getPassword() );
    }

    public ACLProvider getACLProvider(){

        return new ACLProvider() {

            private List<ACL> acl = Lists.newArrayList();

            @Override
            public List<ACL> getDefaultAcl() {
                try {
                    acl.add(new ACL( Perms.ALL, new Id("digest", DigestAuthenticationProvider.generateDigest
                        (buildDigestString()))));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                return acl;
            }

            @Override
            public List<ACL> getAclForPath(String s) {
                try {
                    acl.add(new ACL(Perms.ALL, new Id("digest", DigestAuthenticationProvider.generateDigest
                        (buildDigestString()))));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                return acl;
            }
        };
    }
}
