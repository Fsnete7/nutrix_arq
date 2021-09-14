package com.appnutrix.patient.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient implements Serializable {

    public Patient(Integer id, String username, String password, String firstName, String lastName, String email, Date createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.createdAt = createdAt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "username", nullable = false, length = 16)
    private String username;
    @Column(name = "password", nullable = false, length = 60)
    private String password;
    @Column(name="firstname", nullable = false, length = 50)
    private String firstName;
    @Column(name="lastname", nullable = false, length = 50)
    private String lastName;
    @Column(name="email", nullable = false, length = 50)
    private String email;
    @Column(name="created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToMany(mappedBy = "patient")
    private List<PatientFavoriteRecipes> patientAssoc;
}