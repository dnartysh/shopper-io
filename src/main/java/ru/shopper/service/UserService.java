package ru.shopper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.shopper.config.AppConstants;
import ru.shopper.model.Role;
import ru.shopper.model.User;
import ru.shopper.repository.PositionRepository;
import ru.shopper.repository.RoleRepository;
import ru.shopper.repository.UserRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    PositionRepository positionRepository;

    @Value("${shopper.user-img-path}")
    private String userImgPath;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PositionRepository positionRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.positionRepository = positionRepository;
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
        user.setRegistrationDate(LocalDate.now());
        user.setPosition(positionRepository.findByName("newcomer"));
        userRepository.save(user);

        return user;
    }

    public User getCurrentUser() {
        return userRepository.findByUsername
                (SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public Map<String, String> getSimpleFieldsForCurrentUser() {
        Map<String, String> fields = new HashMap<>();

        User currentUser = getCurrentUser();

        fields.put("id", String.valueOf(currentUser.getId()));
        fields.put("firstname", currentUser.getFirstname());
        fields.put("imgPath", currentUser.getImagePath());
        fields.put("lastname", currentUser.getLastname());
        fields.put("position", currentUser.getPosition().getName());
        fields.put("birthdate", currentUser.getBirthdate() == null ? "" : currentUser.getBirthdate().toString());

        return fields;
    }

    public void uploadUserPhoto(MultipartFile file) throws IOException {
        User currentUser = getCurrentUser();
        String imgName = currentUser.getId() + ".png";

        file.transferTo(Path.of(AppConstants.PROJECT_PATH + userImgPath + imgName));

        currentUser.setImagePath(userImgPath + imgName);
        userRepository.save(currentUser);
    }

    public void removeUserPhoto() {
        User currentUser = getCurrentUser();

        try {
            Files.deleteIfExists(Path.of(currentUser.getImagePath()));

            currentUser.setImagePath("");
            userRepository.save(currentUser);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public User updateUser(String firstname, String lastname, LocalDate birthdate) {
        User user = getCurrentUser();

        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setBirthdate(birthdate);

        userRepository.save(user);

        return user;
    }
}