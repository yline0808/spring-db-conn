package com.tstory.yline.hellouser;

import com.tstory.yline.hellouser.repository.*;
import com.tstory.yline.hellouser.service.UserInfoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    // Memory
    @Bean
    public UserInfoService userInfoService(){
        return new UserInfoService(userInfoRepository());
    }

    @Bean
    public UserInfoRepository userInfoRepository(){
        return new MemoryUserInfoRepository();
    }


    // jdbc
//    private final DataSource dataSource;
//
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    @Bean
//    public UserInfoService userInfoService(DataSource dataSource){
//        return new UserInfoService(userInfoRepository());
//    }
//
//    @Bean
//    public UserInfoRepository userInfoRepository(){
//        return new JdbcUserInfoRepository(dataSource);
//    }


    // jdbc templete
//    private final DataSource dataSource;
//
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    @Bean
//    public UserInfoService userInfoService(DataSource dataSource){
//        return new UserInfoService(userInfoRepository());
//    }
//
//    @Bean
//    public UserInfoRepository userInfoRepository(){
//        return new JdbcTemplateUserInfoRepository(dataSource);
//    }


    // jpa
//    private final EntityManager em;
//
//    public SpringConfig(EntityManager em) {
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
//        return new JpaUserInfoRepository(em);
//    }


    // spring data jpa
//    private final UserInfoRepository userInfoRepository;
//
//    public SpringConfig(UserInfoRepository userInfoRepository) {
//        this.userInfoRepository = userInfoRepository;
//    }
//
//    @Bean
//    public UserInfoService userInfoService(){
//        return new UserInfoService(userInfoRepository);
//    }
}
