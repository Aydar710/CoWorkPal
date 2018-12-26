package servlets.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import repositories.DataSourceSingleton;
import repositories.task.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/done")
public class TaskDoneServlet extends HttpServlet {

    private TaskRepository taskRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        taskRepository.markTaskAsDone(taskId);
    }

    @Override
    public void init() throws ServletException {
        taskRepository = new TaskRepository(DataSourceSingleton.getDataSource());
    }
}
