package com.tstory.yline.hellouser.repository;

import com.tstory.yline.hellouser.domain.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository {
    // 회원가입
    UserInfo save(UserInfo userInfo);
    // id를 사용한 사용자 조회
    Optional<UserInfo> findById(Long id);
    // name을 사용한 사용자 조회
    Optional<UserInfo> findByName(String name);
    // email을 사용한 사용자 조회
    Optional<UserInfo> findByEmail(String email);
    // 모든 사용자 조회
    List<UserInfo> findAll();
}
