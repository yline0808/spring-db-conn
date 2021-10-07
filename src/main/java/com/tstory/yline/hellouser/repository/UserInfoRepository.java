package com.tstory.yline.hellouser.repository;

import com.tstory.yline.hellouser.domain.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository {
    UserInfo save(UserInfo userInfo);
    Optional<UserInfo> findById(Long id);
    Optional<UserInfo> findByName(String name);
    Optional<UserInfo> findByEmail(String email);
    List<UserInfo> findAll();
}
