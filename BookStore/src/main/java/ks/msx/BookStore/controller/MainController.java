package ks.msx.BookStore.controller;


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

}
