package ks.msx.BookStore.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ks.msx.BookStore.dto.UserDTO;
import ks.msx.BookStore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @RequestMapping(MainController.END_POINT+"/login")
    public String returnLoginPage(){
        return "login";
    }

    @RequestMapping(value = MainController.END_POINT+"/login/log", method = RequestMethod.POST)
    public void loginUser(@RequestParam(name = "username")String username,
                          @RequestParam(name = "password")String password, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        request.login(username, password);
        UserDetails userDetails = userService.loadUserByUsername(username);
        response.setStatus(200);
        response.sendRedirect("/");
    }

    @RequestMapping(MainController.END_POINT+"/logout")
    public void logoutUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.logout();
        response.setStatus(200);
        response.sendRedirect(MainController.END_POINT);
    }

    @RequestMapping(MainController.END_POINT+"/register")
    public String registerPage(){
        return "register";
    }

    @RequestMapping(MainController.END_POINT+"/register/reg")
    public void registerUser(@RequestParam(name = "username")String username,
                             @RequestParam(name = "password")String password,
                             HttpServletResponse response,
                             HttpServletRequest request) throws IOException, ServletException {
        userService.registerUser(UserDTO.builder()
                        .username(username)
                        .password(password)
                .build());
        request.login(username, password);
        response.setStatus(200);
        response.sendRedirect(MainController.END_POINT);
    }

    @RequestMapping(MainController.END_POINT+"/test")
    public String test(){
        return "testPage";
    }
}
