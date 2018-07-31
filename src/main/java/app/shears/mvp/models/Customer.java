package app.shears.mvp.models;

import app.shears.mvp.cores.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers", schema = "mvp")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Order> orders;
    @Column
    private String login;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String phone;
    @Column
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column
    private Integer age;
    @Column
    private byte[] photo;

    public Customer(String login, String password, String name, String phone,
                    String email, Gender gender, Integer age, byte[] photo) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.photo = photo;
    }


}
