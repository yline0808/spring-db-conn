package com.tstory.yline.hellouser.repository;

import com.tstory.yline.hellouser.domain.UserInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryUserInfoRepositoryTest {
    MemoryUserInfoRepository repository = new MemoryUserInfoRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        // given
        UserInfo userInfo = new UserInfo("name", "email@gmail.com");

        // when
        repository.save(userInfo);

        // then
        UserInfo result = repository.findById(userInfo.getId()).get();
        assertThat(result).isEqualTo(userInfo);     // test
    }

    @Test
    public void findById(){
        // given
        UserInfo userInfo1 = new UserInfo("name", "email@gmail.com");
        repository.save(userInfo1);

        UserInfo userInfo2 = new UserInfo("yline", "yline@gmail.com");
        repository.save(userInfo2);

        // when
        UserInfo result1 = repository.findById(userInfo1.getId()).get();
        UserInfo result2 = repository.findById(userInfo2.getId()).get();

        // then
        assertThat(result1).isEqualTo(userInfo1);
        assertThat(result2).isEqualTo(userInfo2);
    }

    @Test
    public void findByName(){
        // given
        UserInfo userInfo1 = new UserInfo("name1", "email1");
        repository.save(userInfo1);
        
        UserInfo userInfo2 = new UserInfo("name2", "email2"); 
        repository.save(userInfo2);
        
        // when
        UserInfo result1 = repository.findByName(userInfo1.getName()).get();
        UserInfo result2 = repository.findByName(userInfo2.getName()).get();

        // then
        assertThat(result1).isEqualTo(userInfo1);
        assertThat(result2).isEqualTo(userInfo2);
    }
    
    @Test
    public void findByEmail(){
        // given
        UserInfo userInfo1 = new UserInfo("name1", "email1");
        repository.save(userInfo1);
        
        UserInfo userInfo2 = new UserInfo("name2", "email2");
        repository.save(userInfo2);
        
        // when
        UserInfo result1 = repository.findByEmail(userInfo1.getEmail()).get();
        UserInfo result2 = repository.findByEmail(userInfo2.getEmail()).get();

        // then
        assertThat(result1).isEqualTo(userInfo1);
        assertThat(result2).isEqualTo(userInfo2);
    }
    
    @Test
    public void findAll(){
        // given
        UserInfo userInfo1 = new UserInfo("name1", "email1");
        repository.save(userInfo1);
        UserInfo userInfo2 = new UserInfo("name2", "email2");
        repository.save(userInfo2);
        UserInfo userInfo3 = new UserInfo("name3", "email3");
        repository.save(userInfo3);
        UserInfo userInfo4 = new UserInfo("name4", "email4");
        repository.save(userInfo4);
        
        // when
        List<UserInfo> result = repository.findAll();

        // then
        assertThat(result.size()).isEqualTo(4);
    }
}
