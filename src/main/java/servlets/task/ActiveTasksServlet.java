package servlets.task;

import models.Task;
import repositories.DataSourceSingleton;
import repositories.task.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/tasks")
public class ActiveTasksServlet extends HttpServlet {

    private TaskRepository taskRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO получить id проекта
        int projectId = 1;
        ArrayList<Task> doneTasks = (ArrayList<Task>) taskRepository.getAllNotActiveTasks(projectId);
        request.setAttribute("tasks", doneTasks);
        request.getRequestDispatcher("jsp/projectInfoTasks.jsp").forward(request, response);
    }


    @Override
    public void init() throws ServletException {
        taskRepository = new TaskRepository(DataSourceSingleton.getDataSource());
    }
}
