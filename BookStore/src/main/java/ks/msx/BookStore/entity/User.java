package ks.msx.BookStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "")
    private String username;

    @Column(name = "")
    private String password;

    @Column(name = "")
    private boolean enable;

    @Column(name = "")
    private boolean account_non_locked;

    @Column(name = "")
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enable;
    }

    @Override
    public boolean isAccountNonLocked() {
        return account_non_locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return account_non_locked;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
