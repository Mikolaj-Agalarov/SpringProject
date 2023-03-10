package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.model.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository {

    private final Connection connection;

    @Override
    public List<User> findUsers() {
        try (
                Statement statement = connection.createStatement();
        ) {

            String sql = "select id, name, role, password, created_at from users";
            ResultSet rs = statement.executeQuery(sql);
            final List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(buildUser(rs));
            }

            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> getUser(String name) {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "select id, name, role, password, created_at from users where name = ?")
        ) {

            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return Optional.of(buildUser(rs));
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean findByUsernameAndPassword(String name, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from users where name = ? and password = ?");
            statement.executeQuery();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

        @Override
    public void createUser(String name, String password, String role) {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "insert into users (name, role, password) values (?, ?, ?)")
        ) {
            statement.setString(1, name);
            statement.setString(2, role);
            statement.setString(3, password);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private User buildUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("role"),
                rs.getString("password"),
                rs.getTimestamp("created_at")
        );
    }


}
