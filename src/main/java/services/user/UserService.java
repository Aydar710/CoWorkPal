package services.user;

import forms.SignInForm;
import forms.SignUpForm;
import models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import repositories.user.Role;
import repositories.user.UsersRepository;

public class UserService implements IUSerService {

    private PasswordEncoder encoder;
    private UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public void signUp(SignUpForm form) {
        User user = User.builder()
                .email(form.getEmail())
                .passwordHash(encoder.encode(form.getPassword()))
                .name(form.getName())
                .build();

        usersRepository.save(user);
    }

    @Override
    public String signIn(SignInForm form) {
        return null;
    }

    public boolean isExistsByEmailAndPassword(String email, String password) {
        User user = usersRepository.findByEmail(email);
        if (encoder.matches(password, user.getPasswordHash()))
            return true;
        return false;

    }

    public void changeRole(int id) {
        User user = usersRepository.find(id);
        if (user.getRole() == Role.User)
            user.setRole(Role.Admin);

        usersRepository.save(user);
    }
}
