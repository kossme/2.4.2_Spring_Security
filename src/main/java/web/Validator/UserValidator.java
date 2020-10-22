package web.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import web.Model.User;
import web.Service.UserService;

@Component
    public class UserValidator implements Validator {

        @Autowired
        private UserService userService;

        @Override
        public boolean supports(Class<?> aClass) {
            return User.class.equals(aClass);
        }

        @Override
        public void validate(Object o, Errors errors) {
            User user = (User) o;

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.appUserForm.username");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.appUserForm.firstName");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.appUserForm.lastName");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.appUserForm.email");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.appUserForm.password");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.appUserForm.confirmPassword");


            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
            if (user.getUsername().length() < 4 || user.getUsername().length() > 16) {
                errors.rejectValue("username", "Size.userForm.username");
            }

            if (userService.loadUserByUsername(user.getUsername()) != null) {
                errors.rejectValue("username", "Duplicate.userForm.username");
            }

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
            if (user.getPassword().length() < 8 || user.getPassword().length() > 16) {
                errors.rejectValue("password", "Size.userForm.password");
            }

            if (!user.getConfirmPassword().equals(user.getPassword())) {
                errors.rejectValue("confirmPassword", "Different.userForm.password");
            }
        }
    }