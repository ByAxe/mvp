package app.shears.mvp.models;

import app.shears.mvp.cores.enums.Gender;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customers", schema = "mvp")
@SequenceGenerator(name = "customer_id_seq", sequenceName = "customer_id_seq", allocationSize = 1)
public class Customer {

    @Id
    @Column(unique = true, nullable = false)
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_seq")
    private Long id;

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
