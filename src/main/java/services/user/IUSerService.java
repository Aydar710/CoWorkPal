package services.user;

import forms.SignInForm;
import forms.SignUpForm;

public interface IUSerService {
    void signUp(SignUpForm form);
    String signIn(SignInForm form);
}
