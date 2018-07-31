package app.shears.mvp.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "services", schema = "mvp")
@SequenceGenerator(name = "services_id_seq", sequenceName = "services_id_seq", allocationSize = 1)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Service {

    //    @JsonBackReference
    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            schema = "mvp",
            name = "masters_services",
            joinColumns = {@JoinColumn(name = "service_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "master_id", referencedColumnName = "id")}
    )
    Set<Master> masters = new HashSet<>();
    //    @JsonBackReference
    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            schema = "mvp",
            name = "orders_services",
            joinColumns = {@JoinColumn(name = "service_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")}
    )
    Set<Order> orders = new HashSet<>();
    @Id
    @Column(unique = true, nullable = false)
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "services_id_seq")
    private Long id;


    public Service(String title, String description, BigDecimal price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }


    @Column
    private String title;

    @Column
    private String description;

    @Column
    private BigDecimal price;
}
