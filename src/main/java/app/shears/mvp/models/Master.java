package app.shears.mvp.models;

import app.shears.mvp.cores.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "masters", schema = "mvp")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Master {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //    @JsonBackReference
    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Order> orders;
    //    @JsonManagedReference
    @ManyToMany(mappedBy = "masters")
    private Set<Service> services = new HashSet<>();

    public Master(Set<Order> orders, Set<Service> services, String login,
                  String password, String name, String phone,
                  String email, Gender gender, Integer age, byte[] photo,
                  boolean isActive, boolean isBusy, BigDecimal rank) {
        this.orders = orders;
        this.services = services;
        this.login = login;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.photo = photo;
        this.isActive = isActive;
        this.isBusy = isBusy;
        this.rank = rank;
    }

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

    @Column(name = "active")
    private boolean isActive;

    @Column(name = "busy")
    private boolean isBusy;

    @Column(name = "rank")
    private BigDecimal rank;
}
