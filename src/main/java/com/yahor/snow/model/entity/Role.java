package com.yahor.snow.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.List;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "users")
@EqualsAndHashCode(exclude = { "users" })
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String shownName;

    @Transient
    @Fetch(FetchMode.SELECT)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "role")
    private List<User> users;

    public static Role admin() {
        return Role.builder()
                .id(1)
                .name("ADMIN")
                .shownName("Администратор")
                .build();
    }

    public static Role member() {
        return Role.builder()
                .id(2)
                .name("MEMBER")
                .shownName("Пользователь")
                .build();
    }
}