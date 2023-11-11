package ks.msx.BookStore.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class MainController{
    public static final String END_POINT = "/app/v1/main";

    @RequestMapping("/")
    public void redirectToMain(HttpServletResponse response) throws IOException {
        response.setStatus(200);
        response.sendRedirect("/app/v1/main");
    }

    @RequestMapping(END_POINT)
    public String returnMainPage(HttpServletResponse response){
        response.setStatus(200);
        return "main";
    }

    @RequestMapping(MainController.END_POINT+"/login")
    public String returnLoginPage(){
        return "login";
    }

    @RequestMapping(MainController.END_POINT+"/register")
    public String returnRegisterPage(){
        return "register";
    }

    @RequestMapping(MainController.END_POINT+"/logout")
    public void logoutUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.logout();
        response.setStatus(200);
        response.sendRedirect(MainController.END_POINT);
    }
}
