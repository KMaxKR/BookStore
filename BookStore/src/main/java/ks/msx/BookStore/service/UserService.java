package ks.msx.BookStore.service;

import ks.msx.BookStore.dto.UserDTO;
import ks.msx.BookStore.entity.Role;
import ks.msx.BookStore.entity.User;
import ks.msx.BookStore.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDetailsService userDetails(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findUserByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Wrong username"));
            }
        };
    }

    public void registerUser(UserDTO dto){
        userRepository.save(User.builder()
                        .username(dto.getUsername())
                        .password(new BCryptPasswordEncoder(12).encode(dto.getPassword()))
                        .role(Role.USER)
                        .enable(true)
                        .account_non_locked(true)
                .build());
    }
}
