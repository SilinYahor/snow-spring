package com.yahor.snow.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min=6, max=50, message="От 6 до 50 символов")
    private String login;

    @Size(min=6, max=50, message="От 6 до 50 символов")
    private String password;

    @Size(min=2, max=50, message="От 2 до 50 символов")
    private String name;

    @Email
    private String email;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    private Role role;
}
