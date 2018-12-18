package repositories.project;

import models.Project;
import models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import repositories.DataSourceSingleton;
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
}
