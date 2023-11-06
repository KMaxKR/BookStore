package ks.msx.BookStore.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ks.msx.BookStore.dto.UserDTO;
import ks.msx.BookStore.service.UserService;
import ks.msx.BookStore.utility.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @RequestMapping(MainController.END_POINT+"/login")
    public String returnLoginPage(){
        return "login";
    }

    @RequestMapping(value = MainController.END_POINT+"/login/log", method = RequestMethod.POST)
    public void loginUser(@RequestBody UserDTO dto,
                          HttpServletResponse response,
                          HttpServletRequest request) throws ServletException, IOException {
        authService.signin(dto);
        System.out.println(authService.signin(dto));
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
    public void registerUser(@RequestBody UserDTO dto,
                             HttpServletResponse response,
                             HttpServletRequest request) throws IOException, ServletException {
        userService.registerUser(dto);
        request.login(dto.getUsername(), dto.getPassword());
        response.setStatus(200);
        response.sendRedirect("/");
    }

}
