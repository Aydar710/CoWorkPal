package servlets;

import models.Invite;
import models.Project;
import models.User;
import repositories.DataSourceSingleton;
import repositories.invite.InviteRepository;
import repositories.project.ProjectReposiory;
import repositories.user.UsersRepository;
import services.project.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/addMember")
public class AddMemberServlet extends HttpServlet {
    private UsersRepository usersRepository;
    private ProjectReposiory projectReposiory;
    private ProjectService projectService;
    private InviteRepository inviteRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/addMember.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = request.getParameter("usersEmail");
        User user = usersRepository.findByEmail(userEmail);
        int projectId =  Helper.getProjectIdFromCookie(request);
        Project project = projectReposiory.find(projectId);

        Invite invite = Invite.builder()
                .project(project)
                .user(user)
                .build();
        inviteRepository.save(invite);
    }

    @Override
    public void init() throws ServletException {
        usersRepository = new UsersRepository(DataSourceSingleton.getDataSource());
        projectReposiory = new ProjectReposiory(DataSourceSingleton.getDataSource());
        projectService = new ProjectService(projectReposiory);
        inviteRepository = new InviteRepository(DataSourceSingleton.getDataSource());
    }

}
