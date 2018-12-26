package servlets.project;

import models.Project;
import models.Task;
import models.User;
import repositories.DataSourceSingleton;
import repositories.project.ProjectReposiory;
import repositories.task.TaskRepository;
import repositories.user.UsersRepository;
import servlets.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/projectInfoTasks")
public class AdminsProjectInfoServlet extends HttpServlet {

    private TaskRepository taskRepository;
    private ProjectReposiory projectReposiory;
    private UsersRepository usersRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int projectId = Helper.getProjectIdFromCookie(request);
        ArrayList<Task> allTasks = (ArrayList<Task>) taskRepository.getAllNotActiveTasks(projectId);
        request.setAttribute("tasks", allTasks);
        Project project = projectReposiory.find(projectId);
        request.setAttribute("projectName", project.getName());

        int userId = Helper.getUserIdFromCookie(request);
        User currentUser = usersRepository.find(userId);
        String role = currentUser.getRole().name();
        request.setAttribute("role", role);
        request.getRequestDispatcher("jsp/projectInfoTasks.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int projectid = Helper.getProjectIdFromCookie(request);
        String taskText = request.getParameter("task");

        Project project = Project.builder()
                .id(projectid)
                .build();

        Task task = Task.builder()
                .task(taskText)
                .project(project)
                .build();

        taskRepository.save(task);
        request.getRequestDispatcher("jsp/projectInfoTasks.jsp").forward(request, response);
    }

    @Override
    public void init() throws ServletException {
        taskRepository = new TaskRepository(DataSourceSingleton.getDataSource());
        projectReposiory = new ProjectReposiory(DataSourceSingleton.getDataSource());
        usersRepository = new UsersRepository(DataSourceSingleton.getDataSource());
    }
}
