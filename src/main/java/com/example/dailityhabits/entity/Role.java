package com.example.dailityhabits.entity;

import com.example.dailityhabits.enumeration.RolesEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RolesEnum role;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}