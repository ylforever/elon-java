package com.elon.springbootdemo;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.elon.springbootdemo.manager.InvalidatorMgr;
import com.elon.springbootdemo.model.User;
import com.elon.springbootdemo.ws.WSExample;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@MapperScan(basePackages= {"com.elon.springbootdemo"})
public class StartupSpringBootDemo 
{
    public static void main( String[] args )
    {
//        User user = new User();
//        user.setName("ahskahskhqlwjqlwqlwhqlhwlqjwlqhwlhqwhqlwjjqlwl");
//        List<String> messageList = InvalidatorMgr.instance().validate(user);
//        System.out.println(messageList);
        
        SpringApplication.run(StartupSpringBootDemo.class, args);
        
//        new WSExample().queryUser("");
    }
}
