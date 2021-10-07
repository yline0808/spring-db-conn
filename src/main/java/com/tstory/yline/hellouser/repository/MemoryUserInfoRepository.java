package com.tstory.yline.hellouser.repository;

import com.tstory.yline.hellouser.domain.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class MemoryUserInfoRepository implements UserInfoRepository{

    private HashMap<Long, UserInfo> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public UserInfo save(UserInfo userInfo) {
        userInfo.setId(++sequence);
        store.put(userInfo.getId(), userInfo);
        return userInfo;
    }

    @Override
    public Optional<UserInfo> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<UserInfo> findByName(String name) {
        return store.values().stream()
                .filter(userInfo -> userInfo.getName().equals(name))
                .findAny();
    }

    @Override
    public Optional<UserInfo> findByEmail(String email) {
        return store.values().stream()
                .filter(userInfo -> userInfo.getEmail().equals(email))
                .findAny();
    }

    @Override
    public List<UserInfo> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
