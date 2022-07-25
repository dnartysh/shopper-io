package ru.shopper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import ru.shopper.config.AppConstants;
import ru.shopper.exception.PositionNotFoundException;
import ru.shopper.exception.UserNotFoundException;
import ru.shopper.model.Position;
import ru.shopper.model.Role;
import ru.shopper.model.User;
import ru.shopper.repository.PositionRepository;
import ru.shopper.repository.RoleRepository;
import ru.shopper.repository.UserRepository;

import java.io.IOException;
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

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }

    public void createUser(String username, String firstname,
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
    }

    public void createUser(String username, String firstname,
                           String lastname, String password, String position,
                           LocalDate birthdate, boolean active) throws PositionNotFoundException {
        User user = new User();
        Position userPosition = positionRepository.findByName(position);
        List<Role> roles = new ArrayList<>();

        if (userPosition == null) {
            throw new PositionNotFoundException("Позиция - " + position + " не найдена!");
        }

        if (isAdminPosition(userPosition)) {
            roles.add(roleRepository.findByName("ROLE_ADMIN"));
        } else {
            roles.add(roleRepository.findByName("ROLE_USER"));
        }

        user.setUsername(username);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRoles(roles);
        user.setActive(active);
        user.setRegistrationDate(LocalDate.now());
        user.setPosition(userPosition);
        userRepository.save(user);
    }

    private boolean isAdminPosition(Position position) {
        return "admin".equals(position.getName());
    }

    public User getCurrentUser() {
        return userRepository.findByUsername
                (SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public Map<String, String> getSimpleFieldsForCurrentUser() {
        Map<String, String> fields = new HashMap<>();

        User currentUser = getCurrentUser();

        fields.put("id", String.valueOf(currentUser.getId()));
        fields.put("username", currentUser.getUsername());
        fields.put("firstname", currentUser.getFirstname());
        fields.put("lastname", currentUser.getLastname());
        fields.put("imgPath", currentUser.getImagePath());
        fields.put("position", currentUser.getPosition().getDescription());
        fields.put("birthdate", currentUser.getBirthdate() == null ? "" : currentUser.getBirthdate().toString());
        fields.put("active", Boolean.toString(currentUser.isActive()));

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

    public void updateCurrentUser(String firstname, String lastname, LocalDate birthdate, String password) {
        User user = getCurrentUser();

        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setBirthdate(birthdate);

        if (!"".equals(password)) {
            user.setPassword(bCryptPasswordEncoder.encode(password));
        }

        userRepository.save(user);
    }

    public void updateUser(String username, String firstname, String lastname, LocalDate birthdate,
                           String password, boolean active) throws UserNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UserNotFoundException("Пользователь с логином - " + username + " не найден!");
        }

        user.setUsername(username);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setBirthdate(birthdate);
        user.setActive(active);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        userRepository.save(user);
    }

    public List<Position> getPositions() {
        List<Position> positions = new ArrayList<>();

        positionRepository.findAll().forEach(positions::add);

        return positions;
    }

    public void addBasicAttributes(Model model) {
        model.addAttribute("currentUser", getSimpleFieldsForCurrentUser());
    }
}