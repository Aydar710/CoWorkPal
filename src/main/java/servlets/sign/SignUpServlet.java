package servlets.sign;

import forms.SignUpForm;
import repositories.DataSourceSingleton;
import repositories.user.UsersRepository;
import services.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/sign/signUp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SignUpForm signUpForm = SignUpForm.builder()
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .name(request.getParameter("name"))
                .build();

        userService.signUp(signUpForm);
        response.sendRedirect("/signIn");
    }

    @Override
    public void init() throws ServletException {
        UsersRepository usersRepository = new UsersRepository(DataSourceSingleton.getDataSource());
        userService = new UserService(usersRepository);
    }
}
