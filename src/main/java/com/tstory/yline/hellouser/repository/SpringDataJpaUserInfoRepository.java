package com.tstory.yline.hellouser.repository;

import com.tstory.yline.hellouser.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaUserInfoRepository extends JpaRepository<UserInfo, Long>, UserInfoRepository {
    Optional<UserInfo> findByName(String name);
    Optional<UserInfo> findByEmail(String email);
}
