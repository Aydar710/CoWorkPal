package servlets;

import models.Project;
import models.Task;
import models.User;
import repositories.DataSourceSingleton;
import repositories.project.ProjectReposiory;
import repositories.task.TaskRepository;
import repositories.user.UsersRepository;
import services.project.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/startProject")
public class StartProjectServlet extends HttpServlet {

    private UsersRepository usersRepository;
    private ProjectReposiory projectReposiory;
    private ProjectService projectService;
    private TaskRepository taskRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/startProject.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String projectName = request.getParameter("projectName");
        Cookie[] cookies = request.getCookies();
        int userId = 0;
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("userId")){
                userId = Integer.parseInt(cookie.getValue());
            }
        }
        User mainAdmin = User.builder()
                .id(userId)
                .build();

        Project project = Project.builder()
                .name(projectName)
                .mainAdmin(mainAdmin)
                .build();
        projectService.addProject(project);

        request.setAttribute("name", projectName);

        //Redirect на страничку проекта
        Project addedProject = projectReposiory.findProjectByNameAndMainAdminId(project.getName(), mainAdmin.getId());
        ArrayList<Task> allTasks = (ArrayList<Task>) taskRepository.getAllNotActiveTasks(addedProject.getId());
        request.setAttribute("tasks", allTasks);
        request.setAttribute("projectName", addedProject.getName());

        userId = Helper.getUserIdByCookie(request);
        User currentUser = usersRepository.find(userId);
        String role = currentUser.getRole().name();
        request.setAttribute("role", role);
        request.getRequestDispatcher("jsp/projectInfoTasks.jsp").forward(request, response);

    }


    @Override
    public void init() throws ServletException {
        usersRepository = new UsersRepository(DataSourceSingleton.getDataSource());
        projectReposiory = new ProjectReposiory(DataSourceSingleton.getDataSource());
        projectService = new ProjectService(projectReposiory);
        taskRepository = new TaskRepository(DataSourceSingleton.getDataSource());
    }
}
