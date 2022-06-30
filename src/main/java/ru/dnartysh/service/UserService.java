package ru.dnartysh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.dnartysh.model.Role;
import ru.dnartysh.model.User;
import ru.dnartysh.repository.PositionRepository;
import ru.dnartysh.repository.RoleRepository;
import ru.dnartysh.repository.UserRepository;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

        if (!file.isEmpty()) {
            String rootImgPath = "src/main/resources/static";
            String imgName = "/img/user/" + currentUser.getId() + ".png";

            byte[] bytes = file.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(rootImgPath + imgName));
            stream.write(bytes);
            stream.close();

            currentUser.setImagePath(imgName);
            userRepository.save(currentUser);
        } else {
            throw new FileNotFoundException("File not found");
        }
    }

    public User updateUser(int id, String firstname, String lastname, LocalDate birthdate) {
        User user = userRepository.findById(id).get();

        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setBirthdate(birthdate);

        userRepository.save(user);

        return user;
    }
}