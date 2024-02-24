package com.mycompany.user;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true, length = 45)
    private String email;
    @Column(nullable = false, length = 15)
    private String password;

    private boolean enabled;



    @Column(length = 45, nullable = false, name= "first_name")
    private String firstName;
    @Column(length = 45, nullable = false, name= "last_name")
    private String lastName;

}
