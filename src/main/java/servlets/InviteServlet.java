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
import java.util.ArrayList;


@WebServlet("/invite")
public class InviteServlet extends HttpServlet {

    private UsersRepository usersRepository;
    private InviteRepository inviteRepository;
    private ProjectReposiory projectReposiory;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Вернуть список всех инвайтов пользователя
        int userId = Helper.getUserIdFromCookie(request);
        ArrayList<Invite> usersInvites = (ArrayList<Invite>) inviteRepository.getAllUserInvites(userId);
        request.setAttribute("invites", usersInvites);
        request.getRequestDispatcher("jsp/invite.jsp").forward(request, response);
    }


    @Override
    public void init() throws ServletException {
        usersRepository = new UsersRepository(DataSourceSingleton.getDataSource());
        inviteRepository = new InviteRepository(DataSourceSingleton.getDataSource());
        projectReposiory = new ProjectReposiory(DataSourceSingleton.getDataSource());
    }
}
