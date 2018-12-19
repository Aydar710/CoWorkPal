package servlets.project;


import models.User;
import repositories.DataSourceSingleton;
import repositories.project.ProjectReposiory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/members")
public class MembersServlet extends HttpServlet {

    private ProjectReposiory projectReposiory;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO достать id проекта из куков
        int projectId = 1;
        ArrayList<User> projectMembers = (ArrayList<User>) projectReposiory.getAllProjectMembers(projectId);
        request.setAttribute("members", projectMembers);
        request.getRequestDispatcher("jsp/members.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void init() throws ServletException {
        projectReposiory = new ProjectReposiory(DataSourceSingleton.getDataSource());
    }
}
