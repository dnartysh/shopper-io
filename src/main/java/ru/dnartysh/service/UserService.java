package ru.dnartysh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dnartysh.model.Role;
import ru.dnartysh.model.User;
import ru.dnartysh.repository.PositionRepository;
import ru.dnartysh.repository.RoleRepository;
import ru.dnartysh.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
}
