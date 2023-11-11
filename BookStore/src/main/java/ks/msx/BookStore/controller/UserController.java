package ks.msx.BookStore.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ks.msx.BookStore.dto.AuthResponseDTO;
import ks.msx.BookStore.dto.UserDTO;
import ks.msx.BookStore.service.UserService;
import ks.msx.BookStore.utility.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    @RequestMapping(value = MainController.END_POINT+"/login/log", method = RequestMethod.POST)
    public void loginUser(@RequestBody UserDTO dto, HttpServletRequest request) throws ServletException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateToken(authentication);
        //request.login(dto.getUsername(), dto.getPassword());
    }


    @RequestMapping(MainController.END_POINT+"/register/reg")
    public void registerUser(@RequestBody UserDTO dto,
                             HttpServletResponse response) throws IOException{
        userService.registerUser(dto);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        response.setStatus(200);
        response.sendRedirect("/");
    }

}
