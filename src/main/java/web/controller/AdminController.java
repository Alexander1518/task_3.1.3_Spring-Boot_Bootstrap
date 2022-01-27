package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping()
    public String printUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("roles", roleService.listRoles());
        return "/admin";
    }

    @GetMapping("/create")
    public String newUser(Model model) {
        model.addAttribute("roles", roleService.listRoles());
        model.addAttribute("user", new User());
        return "create";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "nameRoles") String [] nameRoles) {
        user.setRoles(roleService.getSetOfRoles(nameRoles));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/update/{id}")
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("roles",roleService.listRoles());
        model.addAttribute("user", userService.getUserById(id));
        return "update";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "nameRoles") String [] nameRoles) {
        user.setRoles(roleService.getSetOfRoles(nameRoles));
        userService.updateUser(user);
        return "redirect:/admin";
    }
}