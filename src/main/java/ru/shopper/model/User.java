package ru.shopper.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @Column(name = "image_path")
    private String imagePath;

    @Column(columnDefinition = "DATE")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    @Column(name = "registration_date", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDate registrationDate;

    @Column(name = "first_login")
    private boolean firstLogin;

    private boolean active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id")
    private Position position;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Location> locations;
}