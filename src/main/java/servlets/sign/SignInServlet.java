package servlets.sign;

import forms.SignInForm;
import models.User;
import repositories.DataSourceSingleton;
import repositories.user.Role;
import repositories.user.UsersRepository;
import services.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    private UserService userService;
    private UsersRepository usersRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/sign/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignInForm form = SignInForm.builder()
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .build();

        if (userService.isExistsByEmailAndPassword(form.getEmail(), form.getPassword())) {
            User currentUser = usersRepository.findByEmail(form.getEmail());
            Cookie cookieUserId = new Cookie("userId", String.valueOf(currentUser.getId()));
            resp.addCookie(cookieUserId);
            req.getRequestDispatcher("jsp/usersPage.jsp").forward(req, resp);
        } else
            resp.sendRedirect("/signIn");
    }


    @Override
    public void init() throws ServletException {
        usersRepository = new UsersRepository(DataSourceSingleton.getDataSource());
        userService = new UserService(usersRepository);
    }
}
