package ks.msx.BookStore.utility;

import ks.msx.BookStore.dto.UserDTO;
import ks.msx.BookStore.service.UserService;
import ks.msx.BookStore.utility.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

   public String signup(UserDTO dto){
        userService.registerUser(dto);
        return jwtService.generateToken(userService.userDetails().loadUserByUsername(dto.getUsername()));
   }

   public String signin(UserDTO dto){
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
       return jwtService.generateToken(userService.userDetails().loadUserByUsername(dto.getUsername()));
   }
}
