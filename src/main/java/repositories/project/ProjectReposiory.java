package repositories.project;

import models.Project;
import models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import repositories.DataSourceSingleton;
import repositories.user.Role;
import repositories.user.UsersRepository;

import javax.sql.DataSource;
import java.util.List;

public class ProjectReposiory implements IProjectRepository {

    private JdbcTemplate jdbcTemplate;
    private UsersRepository usersRepository;

    public ProjectReposiory(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        usersRepository = new UsersRepository(DataSourceSingleton.getDataSource());
    }

    RowMapper<Project> projectRowMapper = (resultSet, i) -> {
        User mainAdmin = usersRepository
                .find(resultSet.getInt("main_admin"));

        return Project.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .mainAdmin(mainAdmin)
                .build();
    };
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
    public List<Project> findAll() {
        //language=sql
        final String SELECT_ALL_PROJECTS = "select * from project";
        return jdbcTemplate.query(SELECT_ALL_PROJECTS, projectRowMapper);
    }

    @Override
    public void save(Project project) {
        //language=sql
        final String INSERT_PROJECT = "insert into project(name, main_admin) values (?, ?)";
        jdbcTemplate.update(INSERT_PROJECT, project.getName(), project.getMainAdmin().getId());
    }

    @Override
    public Project find(int id) {
        //language=sql
        final String SELECT_PROJECT_BY_ID = "select * from project where id = ?";
        return jdbcTemplate.queryForObject(SELECT_PROJECT_BY_ID, projectRowMapper, id);
    }


    //TODO реализовать
    public List<User> getAllProjectMembers(int id) {
        //language=sql
        final String SELECT_ALL_PROJECT_MEMBERS =
                "select users.* from users, user_project where project_id = ? and users.id = user_id";
        return jdbcTemplate.query(SELECT_ALL_PROJECT_MEMBERS, userRowMapper, id);
    }

    public void addMemberToProject(User user, Project project) {
        if (user != null && project != null) {
            //language=sql
            final String INSERT_MEMBER_INTO_USER_PROJECT = "insert into user_project(project_id, user_id) values (?, ?)";
            jdbcTemplate.update(INSERT_MEMBER_INTO_USER_PROJECT, project.getId(), user.getId());
        }
    }

    public List<Project> getAllUsersProjects(int userId) {
        //language=sql
        final String SELECT_ALL_USERS_PROJECTS =
                "select * from project where main_admin = ?";

        return jdbcTemplate.query(SELECT_ALL_USERS_PROJECTS, projectRowMapper, userId);
    }

    public List<User> getAllAdmins(int projectId) {
        //language=sql
        final String SELECT_ALL_PROJECT_ADMINS =
                "select users.* from admin_project, users where project_id = ? and admin_id = users.id";
        return jdbcTemplate.query(SELECT_ALL_PROJECT_ADMINS, userRowMapper, projectId);

    }

    public void addAdminToProject(int adminId, int projectId) {
        //language=sql
        final String ADD_ADMIN_TO_PROJECT =
                "insert into admin_project(project_id, admin_id) values (?, ?)";
        jdbcTemplate.update(ADD_ADMIN_TO_PROJECT, projectId, adminId);

        //setting user as admin
        //language=sql
        final String SET_USER_AS_ADMIN =
                "update users set is_admin = true where id = ?";
        jdbcTemplate.update(SET_USER_AS_ADMIN, adminId);
    }
}
