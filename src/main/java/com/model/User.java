package com.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;


@Entity
@Table(name = "users1")
@JsonIgnoreProperties
@Getter
@Setter
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinTable(name = "user_roles1", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Role role;

    @Column(name = "firstname")
    @Size(min = 1, max = 16, message = "Name should be from 1 to 16 symbols")
    private String firstname;

    @Column(name = "lastname")
    @Size(min = 1, max = 16, message = "Surname should be from 1 to 16 symbols")
    private String lastname;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "email")
    @Email(message = "Invalid Email Format")
    private String email;

    @Column(name = "password")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;


    transient private String password2;

    public User() {
    }

   
    public User(int id, Role role, @Size(min = 1, max = 16, message = "Name should be from 1 to 16 symbols") String firstname, @Size(min = 1, max = 16, message = "Surname should be from 1 to 16 symbols")
            String lastname, @Min(value = 9, message = "need 9") String phone_number,
                @Email(message = "Invalid Email Format")
                @Size(min = 8, message = "Password must be at least 8 characters long") String password, String password2) {
        this.id = id;
        this.role = role;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone_number = phone_number;
        this.email = email;

        this.password = password;
        this.password2 = password2;
    }



    @Override
    public String toString() {
        return email;
    }
}
