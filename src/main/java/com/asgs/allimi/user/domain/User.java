package com.asgs.allimi.user.domain;

import com.asgs.allimi.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 10, nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private UserIdentity identity;

    @Column(length = 20, nullable = false)
    private String nickname;

    @Column(length = 50, unique = true, nullable = false)
    private String email;

    @Column(length = 5)
    private String studentId;

    @ColumnDefault("200")
    private int point;

    @ColumnDefault("0")
    private int buyCount;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Builder
    public User(String name, String password, UserIdentity identity, String nickname, String email, String studentId){
        this.name = name;
        this.password = password;
        this.identity = identity;
        this.nickname = nickname;
        this.email = email;
        this.studentId = studentId;
    }
}
