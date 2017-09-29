# zk boot starter


#### 依赖
```xml
 <dependency>
    <groupId>com.spring.boot</groupId>
    <artifactId>zk-spring-boot-starter</artifactId>
    <version>1.0.0-SNAPSHOT</version>
 </dependency>
```

#### 配置 properties
```properties
spring.boot.zk.enable=true
spring.boot.zk.cluster=127.0.0.1:2181
spring.boot.zk.nameSpace=example
spring.boot.zk.sessionTimeOut=60000
spring.boot.zk.connectionTimeOut=30000
spring.boot.zk.userName=liuhailin
spring.boot.zk.password=liuhailin
```
或者 yml

```yaml
spring:
  boot:
    zk:
      enable: true
      cluster: 127.0.0.1:2181
      nameSpace: example
      sessionTimeOut: 60000
      connectionTimeOut: 30000
      userName: liuhailin
      password: liuhailin
```

#### Example [例子](/example "example")

```java
@Component
public class Example {

    @Autowired
    private IZKClient client;

    @PostConstruct
    public void init() {

        boolean isEx = client.checkNode( "/12345" );
        if (!isEx) {
            client.createNode( "/12345", "liuhailin" );
        } else {
            client.deleteNode( "/12345" );
        }

    }
}
```