package com.tstory.yline.hellouser.service;

import com.tstory.yline.hellouser.domain.UserInfo;
import com.tstory.yline.hellouser.repository.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class UserInfoIntegrationTest {

    @Autowired UserInfoService userInfoService;
    @Autowired UserInfoRepository userInfoRepository;

    @Test
    public void 회원가입(){
        // given
        UserInfo userInfo = new UserInfo("name", "email");

        // when
        Long saveId = userInfoService.join(userInfo);

        // then
        UserInfo result = userInfoService.findOne(saveId).get();
        assertThat(result.getEmail()).isEqualTo(userInfo.getEmail());
    }

    @Test
    public void 중복_회원_예외(){
        // given
        UserInfo userInfo = new UserInfo("name", "email");

        // when
        userInfoService.join(userInfo);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> userInfoService.join(userInfo));

        // then
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
        assertThat(result.size()).isEqualTo(4);
    }

    @Test
    public void findOne(){
        // given
        UserInfo userInfo = new UserInfo("name", "email");
        userInfoService.join(userInfo);

        // when
        UserInfo result = userInfoService.findOne(userInfo.getId()).get();

        // then
        assertThat(result.getEmail()).isEqualTo(userInfo.getEmail());
    }
}
