package ghtk.spring.hw2.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //    unique=true
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password", length = 255)
    private String password;
}
