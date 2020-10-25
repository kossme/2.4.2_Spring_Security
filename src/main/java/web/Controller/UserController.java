package web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.Model.User;
import web.Service.RoleService;
import web.Service.UserService;
import web.Validator.UserValidator;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private RoleService roleService;

    public UserController() {
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute("user") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/register";
        }
        roleService.checkIfRoleExistOraddNewRole("ROLE_USER");
        userService.save(userForm);
        return "redirect:/";
    }

    @GetMapping(value = { "/", "/home" })
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is home page!");
        String notloggedInMessage = "You are not logged in. Please log in or register to view information";
        model.addAttribute("youAraNotLoggedIn", notloggedInMessage);
        String loggedInMessage = "You are logged in";
        model.addAttribute("LoggedInMessage", loggedInMessage);
        return "home";
    }

    // Login form
    @GetMapping("/login.html")
    public String login() {
        return "login.html";
    }

    @GetMapping(value = "/logoutSuccessful")
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @GetMapping(value = "/userPage")
    public String showUserPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();//get logged in username
        User user = (User) userService.loadUserByUsername(name);
        model.addAttribute("user", user);
        return "userPage";
    }

    @GetMapping(value = "/403")
    public String accessDenied(Model model) {
        return "403";
    }
}



