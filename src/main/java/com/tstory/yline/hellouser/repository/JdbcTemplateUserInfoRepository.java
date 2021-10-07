package com.tstory.yline.hellouser.repository;

import com.tstory.yline.hellouser.domain.UserInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateUserInfoRepository implements UserInfoRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserInfoRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public UserInfo save(UserInfo userInfo) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("user_info").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", userInfo.getName());
        parameters.put("email", userInfo.getEmail());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        userInfo.setId(key.longValue());
        return userInfo;
    }

    @Override
    public Optional<UserInfo> findById(Long id) {
        List<UserInfo> result = jdbcTemplate.query("select * from user_info where id = ?", userInfoRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<UserInfo> findByName(String name) {
        List<UserInfo> result = jdbcTemplate.query("select * from user_info where name = ?", userInfoRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public Optional<UserInfo> findByEmail(String email) {
        List<UserInfo> result = jdbcTemplate.query("select * from user_info where email = ?", userInfoRowMapper(), email);
        return result.stream().findAny();
    }

    @Override
    public List<UserInfo> findAll() {
        return jdbcTemplate.query("select * from user_info", userInfoRowMapper());
    }

    private RowMapper<UserInfo> userInfoRowMapper(){
        return (rs, rowNum) -> {
            UserInfo userInfo = new UserInfo(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("email"));
            return userInfo;
        };
    }
}
