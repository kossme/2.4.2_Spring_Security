package web.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import web.Dao.RoleDao;
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
/*
    @GetMapping(value = "/")
    public String firstPage(ModelMap model) {
        List<User> usersList = new ArrayList<>();
        //userService.add(new User("User5", "Lastname5", "user5@mail.ru"));
        usersList = userService.listUsers();
        model.addAttribute("usersList", usersList);
        System.out.println(usersList);
        return "index";
    }


    @GetMapping("/deleteConfirm/{id}")
    public String deleteConfirm(@PathVariable("id") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", userService.findUserById(id));
        return "deleteConfirm";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserById(@PathVariable(value = "id", required = true) long id, Model model) {
        userService.removeUser(id);
        return "redirect:/";
    }
//"redirect:/index"

    @GetMapping("/addNewUserForm")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String create(@RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lasttName,
                         @RequestParam("email") String email, Model model) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lasttName);
        user.setEmail(email);
        userService.add(user);
        return "redirect:/";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "updateForm";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id", required = true) long id,
                         @RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lasttName,
                         @RequestParam("email") String email, Model model) {
        if (firstName != null && lasttName != null && email != null) {
            User user = userService.findUserById(id);
            user.setFirstName(firstName);
            user.setLastName(lasttName);
            user.setEmail(email);
            userService.updateUser(user);
        }
        return "redirect:/";
    }

    @GetMapping("/user/{id}")
    public String showUserPage(@PathVariable(value = "id", required = true) long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "userPage";
    }
*/
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
        userService.add(userForm);
        roleService.checkIfRoleExistOraddNewRole("ROLE_USER");
        //securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());
        return "redirect:/";
    }

    @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is home page!");
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

/*
    // Login form with error
    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login.html";
    }

   @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }
        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        return "login";

   @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }*/

}



