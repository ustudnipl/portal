package pl.ustudni.portal.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ustudni.portal.model.Role;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        pl.ustudni.portal.model.User user = userService.findUserByEmail(email);

        if (user == null)
            throw new UsernameNotFoundException(email);

        List<GrantedAuthority> authorities = getAuthorities(user.getRoles());
        //authorities.stream().map(x -> x.getAuthority()).forEach(x -> System.out.println(x));


        return new User(user.getEmail(), user.getPassword(), user.isActive(), true, true, true, authorities);
    }

    private List<GrantedAuthority> getAuthorities(Set<Role> roles) {
        return roles.stream().map(x -> new SimpleGrantedAuthority(x.getRole())).collect(Collectors.toList());
    }
}