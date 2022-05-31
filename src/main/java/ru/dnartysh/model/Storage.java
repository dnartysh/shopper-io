package ru.dnartysh.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "storages")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String address;
    private String description;
    private Double capacity;

//    @ManyToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Product> products;
}
