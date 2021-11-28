package ru.cherry.springhomework.security;

import lombok.Data;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cherry.springhomework.dao.UserRepository;
import ru.cherry.springhomework.domain.administration.User;


@Service
@Data
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUser(username);
        if (null == user) {
            throw new InternalAuthenticationServiceException("User not found");
        }
        return user;
    }

    public User getUser(String username) {
        return userRepository.findById(username).orElse(null);
    }
}
