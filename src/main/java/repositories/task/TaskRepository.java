package repositories.task;

import models.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class TaskRepository implements ITaskRepository {

    private JdbcTemplate jdbcTemplate;

    public TaskRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Task> taskRowMapper = (resultSet, i) ->
            //TODO: Add project to RowMapper
            Task.builder()
                    .id(resultSet.getInt("id"))
                    .task(resultSet.getString("task"))
                    .isMarked(resultSet.getBoolean("is_marked"))
                    .build();

    @Override
    public List<Task> findAll() {
        //language=sql
        final String SELECT_ALL_TASKS = "select * from task";
        return jdbcTemplate.query(SELECT_ALL_TASKS, taskRowMapper);
    }

    @Override
    public void save(Task task) {
        //language=sql
        final String INSERT_TASK = "insert into task(task, project_id) ";
        jdbcTemplate.update(INSERT_TASK, task.getTask(), task.getProject().getId());
    }

    @Override
    public Task find(int id) {
        //language=sql
        final String SELECT_TASK_BY_ID = "select * from task where id = ?";
        return jdbcTemplate.queryForObject(SELECT_TASK_BY_ID, taskRowMapper, id);
    }
}
