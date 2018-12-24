package servlets.project;

import models.Project;
import repositories.DataSourceSingleton;
import repositories.project.ProjectReposiory;
import servlets.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/membersProjects")
public class MembersProjectsServlet extends HttpServlet {

    private ProjectReposiory projectReposiory;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int memberId = Helper.getUserIdByCookie(request);
        ArrayList<Project> membersProjects = (ArrayList<Project>) projectReposiory.getAllMembersProjects(memberId);
        request.setAttribute("membersProjects", membersProjects);
        request.getRequestDispatcher("jsp/membersProjects.jsp").forward(request, response);
    }

    @Override
    public void init() throws ServletException {
        projectReposiory = new ProjectReposiory(DataSourceSingleton.getDataSource());
    }
}
