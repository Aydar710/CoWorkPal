package servlets.project;

import models.Project;
import repositories.DataSourceSingleton;
import repositories.project.ProjectReposiory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/usersProjects")
public class UsersProjectsServlet extends HttpServlet {
    private ProjectReposiory projectReposiory;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        int userId = 0;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userId")) {
                userId = Integer.parseInt(cookie.getValue());
            }
        }
        ArrayList<Project> projects = (ArrayList<Project>) projectReposiory.getAllUsersProjects(userId);
        request.setAttribute("projects", projects);
        request.getRequestDispatcher("jsp/usersPage.jsp").forward(request, response);
    }

    @Override
    public void init() throws ServletException {
        projectReposiory = new ProjectReposiory(DataSourceSingleton.getDataSource());
    }
}
