package kodlamaio.hrms.demo.core.api.security;

import kodlamaio.hrms.demo.core.dataAccess.UserDao;
import kodlamaio.hrms.demo.core.entities.User;
import kodlamaio.hrms.demo.entities.concretes.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {

    private User user;

    public MyUserDetails(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Collection<Role> roles = this.user.getRoles();

        for (Role role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            role.getPrivileges().stream()
                    .map(p -> new SimpleGrantedAuthority(p.getPrivilegeName()))
                    .forEach(authorities::add);
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
