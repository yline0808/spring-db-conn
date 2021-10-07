package com.tstory.yline.hellouser;

import com.tstory.yline.hellouser.repository.*;
import com.tstory.yline.hellouser.service.UserInfoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final UserInfoRepository userInfoRepository;

    public SpringConfig(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Bean
    public UserInfoService userInfoService(){
        return new UserInfoService(userInfoRepository);
    }

    //    private final DataSource dataSource;
//    private final EntityManager em;
//
//    public SpringConfig(DataSource dataSource, EntityManager em) {
//        this.dataSource = dataSource;
//        this.em = em;
//    }
//
//    @Bean
//    public UserInfoService userInfoService(){
//        return new UserInfoService(userInfoRepository());
//    }
//
//    @Bean
//    public UserInfoRepository userInfoRepository(){
////        return new MemoryUserInfoRepository();
////        return new JdbcUserInfoRepository(dataSource);
////        return new JdbcTemplateUserInfoRepository(dataSource);
//        return new JpaUserInfoRepository(em);
//    }
}
