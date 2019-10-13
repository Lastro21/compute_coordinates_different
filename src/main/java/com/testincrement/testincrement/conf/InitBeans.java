package com.testincrement.testincrement.conf;

import com.testincrement.testincrement.global.GlobalSetTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitBeans {

    @Bean
    public GlobalSetTime globalSetTime(){
        return new GlobalSetTime();
    }

}
