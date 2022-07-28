package ru.shopper.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.shopper.model.User;
import ru.shopper.repository.UserRepository;
import ru.shopper.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        User currentUser = userService.getCurrentUser();

        Cookie positionCookie = new Cookie("position", (currentUser.getPosition().getName()));
        Cookie userIdCookie = new Cookie("userId", String.valueOf(currentUser.getId()));

        response.addCookie(positionCookie);
        response.addCookie(userIdCookie);

        super.onAuthenticationSuccess(request, response, authentication);

        updateUserVisitDate(currentUser);
        updateUserIpAddress(currentUser, request.getRemoteAddr());

        userRepository.save(currentUser);
    }

    private void updateUserVisitDate(User user) {
        if (!user.isFirstLogin()) {
            user.setLastVisit(LocalDateTime.now());
        }
    }

    private void updateUserIpAddress(User user, String ipAddress) {
        user.setIpAddress(ipAddress);
    }
}
