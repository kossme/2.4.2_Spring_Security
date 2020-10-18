package web.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.JsonPath;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.Model.User;
import web.Service.UserService;
import web.Service.UserServiceImpl;


@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    public UserController() {
    }

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
        model.addAttribute("user", new User(null, null, null));
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
            userService.updateUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getId());
        }
        return "redirect:/";
    }

    @GetMapping("/user/{id}")
    public String showUserPage(@PathVariable(value = "id", required = true) long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "userPage";
    }

}

