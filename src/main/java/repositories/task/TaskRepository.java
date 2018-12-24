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
        final String INSERT_TASK = "insert into task(task, project_id) values (?, ?)";
        jdbcTemplate.update(INSERT_TASK, task.getTask(), task.getProject().getId());
    }

    @Override
    public Task find(int id) {
        //language=sql
        final String SELECT_TASK_BY_ID = "select * from task where id = ?";
        return jdbcTemplate.queryForObject(SELECT_TASK_BY_ID, taskRowMapper, id);
    }

    public List<Task> getAllByProjectId(int projectId) {
        //language=sql
        final String SELECT_ALL_PROJECT_TASKS_BY_ID =
                "select * from task where project_id = ?";
        return jdbcTemplate.query(SELECT_ALL_PROJECT_TASKS_BY_ID, taskRowMapper, projectId);
    }

    public void markTaskAsDone(int taskId){
        //language=sql
        final String MARK_TASK_AS_DONE = "update task set is_done = true where id = ?";
        jdbcTemplate.update(MARK_TASK_AS_DONE, taskId);
    }

    public List<Task> getAllNotActiveTasks(int projectId){
        //language=sql
        final String SELECT_ALL_DONE_TASKS =
                "select * from task where project_id = ? and is_done = false";

        return jdbcTemplate.query(SELECT_ALL_DONE_TASKS, taskRowMapper, projectId);
    }
}
