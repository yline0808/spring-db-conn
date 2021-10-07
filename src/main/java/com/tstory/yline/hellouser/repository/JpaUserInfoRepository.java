package com.tstory.yline.hellouser.repository;

import com.tstory.yline.hellouser.domain.UserInfo;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaUserInfoRepository implements UserInfoRepository{

    private final EntityManager em;

    public JpaUserInfoRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public UserInfo save(UserInfo userInfo) {
        em.persist(userInfo);
        return userInfo;
    }

    @Override
    public Optional<UserInfo> findById(Long id) {
        UserInfo userInfo = em.find(UserInfo.class, id);
        return Optional.ofNullable(userInfo);
    }

    @Override
    public Optional<UserInfo> findByName(String name) {
        List<UserInfo> result = em.createQuery("select u from UserInfo u where u.name = :name", UserInfo.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public Optional<UserInfo> findByEmail(String email) {
        List<UserInfo> result = em.createQuery("select u from UserInfo u where u.email = :email", UserInfo.class)
                .setParameter("email", email)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<UserInfo> findAll() {
        return em.createQuery("select u from UserInfo u", UserInfo.class).getResultList();
    }
}
