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
    @Column(name = "user_id")
    private Long id;

    @Column(length = 100)
    private String password;

    @Column(length = 10)
    private String name;

    @Enumerated(value = EnumType.STRING)
    private UserIdentity identity;

    @Column(length = 20)
    private String nickname;

    @Column(length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 5)
    private String studentId;

    @ColumnDefault("200")
    private int point;

    @ColumnDefault("0")
    private int buyCount;

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
