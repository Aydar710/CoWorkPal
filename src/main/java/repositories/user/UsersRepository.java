package repositories.user;

import models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class UsersRepository implements IUsersRepository {

    public UsersRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> userRowMapper = (resultSet, i) -> {
        Role role;
        if (resultSet.getBoolean("is_admin"))
            role = Role.Admin;
        else role = Role.User;

        return User.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .email(resultSet.getString("email"))
                .passwordHash(resultSet.getString("password_hash"))
                .role(role)
                .build();
    };

    @Override
    public List<User> findAll() {
        //language=sql
        final String SELECT_ALL_USERS = "select * from users";
        return jdbcTemplate.query(SELECT_ALL_USERS, userRowMapper);
    }

    @Override
    public void save(User user) {
        //language=sql
        final String INSERT_USER = "insert into users(email, name, password_hash) values (?,?,?)";
        jdbcTemplate.update(INSERT_USER, user.getEmail(), user.getName(), user.getPasswordHash());
    }

    @Override
    public User find(int id) {
        //language=sql
        final String SELECT_USER_BY_ID = "select * from users where id = ?";
        return jdbcTemplate.queryForObject(SELECT_USER_BY_ID, userRowMapper, id);
    }

    public User findByEmail(String email) {
        //language=sql
        final String SELECT_BY_EMAIL = "select * from users where email = ?";

        User user = jdbcTemplate.queryForObject(SELECT_BY_EMAIL, userRowMapper, email);
        return user;
    }

    public boolean isAdmin(int id) {
        User user = find(id);
        return user.getRole() == Role.Admin;
    }

    public boolean isUser(int id) {
        User user = find(id);
        return user.getRole() == Role.User;
    }

    public void changeRoleToAdmin(int id) {
        //language=sql
        final String UPDATE_ROLE = "update users set is_admin = true where id = ?";
        jdbcTemplate.update(UPDATE_ROLE, id);
    }
}
