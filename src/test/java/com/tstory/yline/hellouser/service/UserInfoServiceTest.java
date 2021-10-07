package com.tstory.yline.hellouser.service;

import com.tstory.yline.hellouser.domain.UserInfo;
import com.tstory.yline.hellouser.repository.MemoryUserInfoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserInfoServiceTest {

    UserInfoService userInfoService;
    MemoryUserInfoRepository userInfoRepository;

    @BeforeEach
    public void beforeEach(){
        userInfoRepository = new MemoryUserInfoRepository();
        userInfoService = new UserInfoService(userInfoRepository);
    }

    @AfterEach
    public void afterEach(){
        userInfoRepository.clearStore();
    }

    @Test
    public void join(){
        // given
        UserInfo userInfo = new UserInfo("name", "email");

        // when
        Long savedId = userInfoService.join(userInfo);

        // then
        UserInfo findUserInfo = userInfoService.findOne(savedId).get();
        assertThat(userInfo.getEmail()).isEqualTo(findUserInfo.getEmail());
    }

    @Test
    public void 중복_회원_예외(){
        // given
        UserInfo userInfo1 = new UserInfo("name", "email");
        UserInfo userInfo2 = new UserInfo("name", "email1");
        UserInfo userInfo3 = new UserInfo("name", "email");

        // when
        userInfoService.join(userInfo1);
        userInfoService.join(userInfo2);

        //then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> userInfoService.join(userInfo3));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    public void findUserInfos(){
        // given
        UserInfo userInfo1 = new UserInfo("name1", "email1");
        userInfoService.join(userInfo1);
        UserInfo userInfo2 = new UserInfo("name2", "email2");
        userInfoService.join(userInfo2);

        // when
        List<UserInfo> result = userInfoService.findUserInfos();

        // then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void findOne(){
        // given
        UserInfo userInfo = new UserInfo("name", "email");
        userInfoService.join(userInfo);

        // when
        UserInfo result = userInfoService.findOne(userInfo.getId()).get();

        // then
        assertThat(result).isEqualTo(userInfo);
    }
}
