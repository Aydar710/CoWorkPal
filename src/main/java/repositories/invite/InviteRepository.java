package repositories.invite;

import models.Invite;
import models.Project;
import models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import repositories.DataSourceSingleton;
import repositories.project.ProjectReposiory;
import repositories.user.UsersRepository;

import javax.sql.DataSource;
import java.util.List;

public class InviteRepository implements IInviteRepository {
    private JdbcTemplate jdbcTemplate;
    private UsersRepository usersRepository;
    private ProjectReposiory projectReposiory;

    public InviteRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        usersRepository = new UsersRepository(DataSourceSingleton.getDataSource());
        projectReposiory = new ProjectReposiory(DataSourceSingleton.getDataSource());
    }

    RowMapper<Invite> inviteRowMapper = (resultSet, i) -> {
        Project project = projectReposiory.find(resultSet.getInt("project_id"));
        User user = usersRepository.find(resultSet.getInt("user_id"));
        return Invite.builder()
                .id(resultSet.getInt("id"))
                .project(project)
                .user(user)
                .build();

    };

    @Override
    public List<Invite> findAll() {
        return null;
    }

    @Override
    public void save(Invite invite) {
        //language=sql
        final String INSERT_INVITE = "insert into invite(user_id, project_id) values (?, ?)";
        jdbcTemplate.update(INSERT_INVITE, invite.getUser().getId(), invite.getProject().getId());
    }

    @Override
    public Invite find(int id) {
        //language=sql
        final String SELECT_INVITE_BY_ID = "select * from invite where id = ?";
        return jdbcTemplate.queryForObject(SELECT_INVITE_BY_ID, inviteRowMapper, id);
    }

    public void delete(Invite invite) {
        //language=sql
        final String DELETE_BY_PROJECT_ID = "delete from invite where project_id = ? and user_id = ?";
        jdbcTemplate.update(DELETE_BY_PROJECT_ID, invite.getProject().getId(), invite.getUser().getId());
    }

    public List<Invite> getAllUserInvites(int userId){
        //language=sql
        final String SELECT_ALL_USERS_INVITES = "select * from invite where user_id = ?";
        return jdbcTemplate.query(SELECT_ALL_USERS_INVITES, inviteRowMapper, userId);
    }

}
