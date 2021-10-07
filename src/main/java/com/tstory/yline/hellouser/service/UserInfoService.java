package com.tstory.yline.hellouser.service;

import com.tstory.yline.hellouser.domain.UserInfo;
import com.tstory.yline.hellouser.repository.UserInfoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/*
org.springframework.transaction.annotation.Transactional 를 사용하자.
스프링은 해당 클래스의 메서드를 실행할 때 트랜잭션을 시작하고, 메서드가 정상 종료되면 트랜잭션을 커밋한다. 만약 런타임 예외가 발생하면 롤백한다.
JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야 한다.
 */

@Transactional
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;

    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public Long join(UserInfo userInfo){
        validateDuplicateUserInfo(userInfo);
        userInfoRepository.save(userInfo);
        return userInfo.getId();
    }

    private void validateDuplicateUserInfo(UserInfo userInfo){
        userInfoRepository.findByEmail(userInfo.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<UserInfo> findUserInfos(){
        return userInfoRepository.findAll();
    }

    public Optional<UserInfo> findOne(Long userInfoId){
        return userInfoRepository.findById(userInfoId);
    }
}
