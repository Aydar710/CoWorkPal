package servlets;

import models.Project;
import models.User;
import repositories.DataSourceSingleton;
import repositories.project.ProjectReposiory;
import repositories.user.UsersRepository;
import services.project.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/startProject")
public class StartProjectServlet extends HttpServlet {

    private UsersRepository usersRepository;
    private ProjectReposiory projectReposiory;
    private ProjectService projectService;

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

        request.getRequestDispatcher("jsp/project.jsp").forward(request, response);

    }


    @Override
    public void init() throws ServletException {
        usersRepository = new UsersRepository(DataSourceSingleton.getDataSource());
        projectReposiory = new ProjectReposiory(DataSourceSingleton.getDataSource());
        projectService = new ProjectService(projectReposiory);
    }
}
