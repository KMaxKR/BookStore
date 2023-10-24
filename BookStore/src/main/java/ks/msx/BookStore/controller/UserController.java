package ks.msx.BookStore.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ks.msx.BookStore.dto.UserDTO;
import ks.msx.BookStore.entity.User;
import ks.msx.BookStore.service.UserService;
import ks.msx.BookStore.utility.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @RequestMapping(MainController.END_POINT+"/login")
    public String returnLoginPage(){
        return "login";
    }

    @RequestMapping(MainController.END_POINT+"/login/log")
    public void loginUser(@RequestBody UserDTO dto, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        request.login(dto.getUsername(), dto.getPassword());
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
        System.out.println(jwtUtil.generateToken((User) userService.loadUserByUsername(username)));
        request.login(username, password);
        response.setStatus(200);
        response.sendRedirect(MainController.END_POINT);
    }
}
