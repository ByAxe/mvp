package app.shears.mvp.models;

import app.shears.mvp.cores.enums.Gender;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "masters", schema = "mvp")
@SequenceGenerator(name = "master_id_seq", sequenceName = "master_id_seq", allocationSize = 1)
public class Master {

    @Id
    @Column(unique = true, nullable = false)
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "master_id_seq")
    private Long id;

    @JsonBackReference
    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

    @Column(name = "active")
    private boolean isActive;

    @Column(name = "busy")
    private boolean isBusy;

    @Column(name = "rank")
    private BigDecimal rank;
}
