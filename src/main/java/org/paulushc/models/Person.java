package org.paulushc.models;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="PERSONS")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @NotEmpty
    @NotNull
    @Column(name="first_name",length = 50)
    private String firstName;

    @NotEmpty
    @NotNull
    @Column(name="last_name",length = 50)
    private String lastName;

    @NotEmpty
    @NotNull
    @Column(length = 50)
    private String email;

    @NotEmpty
    @NotNull
    @Column(name="gender",length = 50)
    private String gender;

    public Person() {
    }

    public Person(Long id, String firstName, String lastName, String email, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
