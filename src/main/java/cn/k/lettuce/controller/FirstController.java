package cn.k.lettuce.controller;

import cn.k.lettuce.domain.User;
import org.springframework.data.redis.connection.lettuce.LettuceConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class FirstController {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private LettuceConnection lc;

    @RequestMapping("/k1")
    public String K1(){
        User user=new User(1, "java的架构师技术栈", "man");
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        operations.set("fdd2", user);
        boolean exists=redisTemplate.hasKey("fdd2");

        System.out.println("redis是否存在相应的key"+exists);

        User getUser = (User)redisTemplate.opsForValue().get("fdd2");
        System.out.println("从redis数据库获取的user:"+getUser.toString());
        return "当前时间>>>"+new Date();
    }
}
