package app.shears.mvp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@SequenceGenerator(name = "service_id_seq", sequenceName = "service_id_seq", allocationSize = 1)
public class Service {

    @JsonBackReference
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            schema = "mvp",
            name = "masters_services",
            joinColumns = {@JoinColumn(name = "service_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "master_id", referencedColumnName = "id")}
    )
    Set<Master> masters = new HashSet<>();
    @JsonBackReference
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_id_seq")
    private Long id;
    @Column
    private String title;

    @Column
    private String description;

    @Column
    private BigDecimal price;
}
