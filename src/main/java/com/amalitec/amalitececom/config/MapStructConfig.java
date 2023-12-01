package com.amalitec.amalitececom.config;

import com.amalitec.amalitececom.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan(basePackages = "com.mag.user.mapper") // Specify the package where your mappers are located
//@Import(MapStructConfig.class)

public class MapStructConfig {

    // You can define each mapper as a bean here if needed
    @Bean
    //@Primary
    public UserMapper userMapper() {
        return UserMapper.INSTANCE;
    }
}

