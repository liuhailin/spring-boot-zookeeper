package com.zk.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Liu Hailin
 * @create 2017-09-29 上午10:30
 **/
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        String[] args_new = new String[args.length + 1];
        System.arraycopy( args, 0, args_new, 0, args.length );
        args_new[args.length] = "-Djava.security.egd=file:/dev/./urandom";
        SpringApplication.run( Application.class, args_new );
    }
}
