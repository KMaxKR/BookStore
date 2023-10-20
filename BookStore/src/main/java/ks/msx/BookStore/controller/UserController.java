package ks.msx.BookStore.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ks.msx.BookStore.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class UserController {

    @RequestMapping(MainController.END_POINT+"/login")
    public String returnLoginPage(){
        return "login";
    }

    @RequestMapping(MainController.END_POINT+"/login/log")
    public void loginUser(@RequestBody UserDTO dto, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        request.login(dto.getUsername(), dto.getPassword());
        response.setStatus(200);
        response.sendRedirect(MainController.END_POINT);
    }

    @RequestMapping(MainController.END_POINT+"/logout")
    public void logoutUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.logout();
        response.setStatus(200);
        response.sendRedirect(MainController.END_POINT);
    }
}
