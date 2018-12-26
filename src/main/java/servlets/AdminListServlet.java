package servlets;

import models.User;
import repositories.DataSourceSingleton;
import repositories.project.ProjectReposiory;
import repositories.user.UsersRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/admins")
public class AdminListServlet extends HttpServlet {

    private ProjectReposiory projectReposiory;
    private UsersRepository usersRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int projectId = Helper.getProjectIdFromCookie(request);
        ArrayList<User> projectAdmins = (ArrayList<User>) projectReposiory.getAllAdmins(projectId);
        request.setAttribute("admins", projectAdmins);
        int userId = Helper.getUserIdFromCookie(request);
        User user = usersRepository.find(userId);
        request.setAttribute("role", user.getRole().name());
        request.getRequestDispatcher("jsp/admins.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Добавление админа в проект
        //TODO достать id проекта из cookie
        int projectId = Helper.getProjectIdFromCookie(request);
        User admin = usersRepository.findByEmail(request.getParameter("adminsEmail"));
        if (admin != null)
            projectReposiory.addAdminToProject(admin.getId(), projectId);

    }

    @Override
    public void init() throws ServletException {
        projectReposiory = new ProjectReposiory(DataSourceSingleton.getDataSource());
        usersRepository = new UsersRepository(DataSourceSingleton.getDataSource());
    }


}
