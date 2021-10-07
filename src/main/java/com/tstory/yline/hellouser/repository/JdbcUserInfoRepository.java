package com.tstory.yline.hellouser.repository;

import com.tstory.yline.hellouser.domain.UserInfo;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcUserInfoRepository implements UserInfoRepository{
    private DataSource dataSource;

    public JdbcUserInfoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public UserInfo save(UserInfo userInfo) {
        String sql = "insert into user_info(name, email) values(?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, userInfo.getName());
            pstmt.setString(2, userInfo.getEmail());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                userInfo.setId(rs.getLong(1));
            } else {
                throw new SQLException("id 조회 실패");
            }
            return userInfo;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<UserInfo> findById(Long id) {
        String sql = "select * from user_info where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                UserInfo userInfo = new UserInfo();
                userInfo.setId(rs.getLong("id"));
                userInfo.setName(rs.getString("name"));
                userInfo.setEmail(rs.getString("email"));
                return Optional.of(userInfo);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<UserInfo> findByName(String name) {
        String sql = "select * from user_info where name = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                UserInfo userInfo = new UserInfo();
                userInfo.setId(rs.getLong("id"));
                userInfo.setName(rs.getString("name"));
                userInfo.setEmail(rs.getString("email"));
                return Optional.of(userInfo);
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<UserInfo> findByEmail(String email) {
        String sql = "select * from user_info where email = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                UserInfo userInfo = new UserInfo();
                userInfo.setId(rs.getLong("id"));
                userInfo.setName(rs.getString("name"));
                userInfo.setEmail(rs.getString("email"));
                return Optional.of(userInfo);
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<UserInfo> findAll() {
        String sql = "select * from user_info";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<UserInfo> users = new ArrayList<>();
            while(rs.next()) {
                UserInfo userInfo = new UserInfo();
                userInfo.setId(rs.getLong("id"));
                userInfo.setName(rs.getString("name"));
                userInfo.setEmail(rs.getString("email"));
                users.add(userInfo);
            }
            return users;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs)
    {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } try {
        if (pstmt != null) {
            pstmt.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } }
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
