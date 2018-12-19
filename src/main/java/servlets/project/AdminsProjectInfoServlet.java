package servlets.project;

import models.Project;
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

@WebServlet("/admin_projectInfoTasks")
public class AdminsProjectInfoServlet extends HttpServlet {

    TaskRepository taskRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO получить id проекта и сохранить в куках
        int projectId = 1;
        ArrayList<Task> allTasks = (ArrayList<Task>) taskRepository.getAllByProjectId(projectId);
        request.setAttribute("tasks", allTasks);
        request.getRequestDispatcher("jsp/admin_projectInfoTasks.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO получить id проекта из куков
        int projectid = 1;
        String taskText = request.getParameter("task");

        Project project = Project.builder()
                .id(projectid)
                .build();

        Task task = Task.builder()
                .task(taskText)
                .project(project)
                .build();

        taskRepository.save(task);
        request.getRequestDispatcher("jsp/admin_projectInfoTasks.jsp").forward(request, response);
    }

    @Override
    public void init() throws ServletException {
        taskRepository = new TaskRepository(DataSourceSingleton.getDataSource());
    }
}
