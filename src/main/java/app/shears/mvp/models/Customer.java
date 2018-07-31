package app.shears.mvp.models;

import app.shears.mvp.cores.enums.Gender;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@SequenceGenerator(name = "customers_id_seq", sequenceName = "customers_id_seq", allocationSize = 1)
public class Customer {

    @Id
    @Column(unique = true, nullable = false)
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customers_id_seq")
    private Long id;

    @JsonBackReference
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


}
