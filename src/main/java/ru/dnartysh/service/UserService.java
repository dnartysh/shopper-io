package ru.dnartysh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dnartysh.model.Role;
import ru.dnartysh.model.User;
import ru.dnartysh.repository.PositionRepository;
import ru.dnartysh.repository.RoleRepository;
import ru.dnartysh.repository.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    PositionRepository positionRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService (UserRepository userRepository,
                        RoleRepository roleRepository,
                        PositionRepository positionRepository,
                        BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.positionRepository = positionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveUser(String username, String firstname,
                         String lastname, String password) throws Exception {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return createUser(username, firstname, lastname, password);
        } else {
            throw new Exception("User with login - " + username + " already exists");
        }
    }

    private User createUser(String username, String firstname,
                            String lastname, String password) {
        User user = new User();

        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("ROLE_USER"));

        user.setUsername(username);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRoles(roles);
        user.setActive(true);
        user.setRegistrationDate(new Date());
        user.setPosition(positionRepository.findByName("newcomer"));
        userRepository.save(user);

        return user;
    }

    public User getCurrentUser() {
        return userRepository.findByUsername
                (SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public void setCookie(HttpServletResponse response) {
        Cookie positionCookie = new Cookie("position", getCurrentUser().getPosition().getName());
        positionCookie.setHttpOnly(false);
        positionCookie.setSecure(false);

        Cookie idCookie = new Cookie("userId", String.valueOf(getCurrentUser().getId()));
        idCookie.setHttpOnly(false);
        idCookie.setSecure(false);

        response.addCookie(positionCookie);
        response.addCookie(idCookie);
    }

    public Map<String, String> getSimpleFieldsForCurrentUser() {
        Map<String, String> fields = new HashMap<>();

        User currentUser = getCurrentUser();

        fields.put("id", String.valueOf(currentUser.getId()));
        fields.put("firstname", currentUser.getFirstname());
        fields.put("lastname", currentUser.getLastname());
        fields.put("position", currentUser.getPosition().getName());

        return fields;
    }
}
