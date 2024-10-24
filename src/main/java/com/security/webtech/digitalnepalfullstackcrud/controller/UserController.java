package com.security.webtech.digitalnepalfullstackcrud.controller;

import com.security.webtech.digitalnepalfullstackcrud.entity.User;
import com.security.webtech.digitalnepalfullstackcrud.enumtype.RoleType;
import com.security.webtech.digitalnepalfullstackcrud.exception.CustomRoleNotFoundException;
import com.security.webtech.digitalnepalfullstackcrud.exception.EmailAlreadyExistsException;
import com.security.webtech.digitalnepalfullstackcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String home() {
        return "home-page";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", RoleType.values());
        return "register-page";
    }

    @PostMapping("/register")
    public String userRegistered(@ModelAttribute("user") User user,
                                 @RequestParam("roleTypes") String[] roles) throws CustomRoleNotFoundException {
       List<User> users=userService.findAll();
       List<String> emails=users.stream().map(User::getEmail).filter(email -> email !=null)
               .toList();
       boolean exist=emails.stream().anyMatch(email->email.equals(user.getEmail()));
       if (exist){
           throw new EmailAlreadyExistsException("email "+ user.getEmail() +" already exists");
       }else {
           userService.saveUser(user, roles);
       }
        return "redirect:/api/home";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new User());
        return "login-page";
    }

////    @PostMapping("/login")
////    public String loginSuccessFull(@ModelAttribute("user")User user){
////
////        List<User> users =userService.findAll();
////        return "redirect:/userPage";
////    }
//
//    @PostMapping("/userPage")
//    public String userPage(@ModelAttribute("user") User user, Model model) {
//        return "user-page";
//    }
//
//    @GetMapping("/403")
//    public ResponseEntity<String> accessDenied(){
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("access denied");
//    }
//
//    @GetMapping("/user/home")
//    public ResponseEntity<String> userHomePage(){
//        return ResponseEntity.ok("welcome to the userPage");
//    }
//
//    @GetMapping("/admin/dashboard")
//    public ResponseEntity<String> adminDashboard(){
//        return ResponseEntity.ok("welcome to the adminDashBoard");
//    }
//
//    public ResponseEntity<String> defaultPage(){
//        return ResponseEntity.ok("Redirected to defaultPage");
//    }

}
