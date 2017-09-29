package com.zk.example;

import javax.annotation.PostConstruct;

import com.spring.boot.zk.autconfigure.IZKClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Liu Hailin
 * @create 2017-09-29 上午10:32
 **/
@Component
public class Example {

    @Autowired
    private IZKClient client;


    @PostConstruct
    public void init(){


        boolean isEx = client.checkNode("123");


    }
}
