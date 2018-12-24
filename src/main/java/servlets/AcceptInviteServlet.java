package servlets;

import models.Invite;
import repositories.DataSourceSingleton;
import repositories.invite.InviteRepository;
import repositories.project.ProjectReposiory;
import repositories.user.UsersRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/accept")
public class AcceptInviteServlet extends HttpServlet {

    InviteRepository inviteRepository;
    ProjectReposiory projectReposiory;
    UsersRepository usersRepository;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int inviteId = Integer.parseInt(request.getParameter("inviteId"));

        Invite invite = inviteRepository.find(inviteId);
        projectReposiory.addMemberToProject(invite.getUser(), invite.getProject());
        inviteRepository.delete(invite);
    }

    @Override
    public void init() throws ServletException {
        inviteRepository = new InviteRepository(DataSourceSingleton.getDataSource());
        usersRepository = new UsersRepository(DataSourceSingleton.getDataSource());
        projectReposiory = new ProjectReposiory(DataSourceSingleton.getDataSource());
    }
}
