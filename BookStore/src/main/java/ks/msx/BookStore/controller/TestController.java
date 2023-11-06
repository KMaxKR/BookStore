package ks.msx.BookStore.controller;

import ks.msx.BookStore.dto.UserDTO;
import ks.msx.BookStore.service.UserService;
import ks.msx.BookStore.utility.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestController {
    private final UserService userService;
    private final AuthService authService;


    @RequestMapping(MainController.END_POINT+"/test")
    public String test(){
        UserDTO dto = UserDTO.builder()
                .username("k")
                .password("k")
                .build();
        return authService.signin(dto);
    }
}
