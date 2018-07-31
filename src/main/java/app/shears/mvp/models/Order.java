package app.shears.mvp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders", schema = "mvp")
@SequenceGenerator(name = "order_id_seq", sequenceName = "order_id_seq", allocationSize = 1)
public class Order {

    @Id
    @Column(unique = true, nullable = false)
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
    private Long id;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "master_id", referencedColumnName = "id")
    private Master master;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @JsonManagedReference
    @ManyToMany(mappedBy = "orders")
    private Set<Service> services = new HashSet<>();

    @Column
    private String location;

    @Column(name = "photo_before")
    private byte[] photoBefore;

    @Column(name = "photo_after")
    private byte[] photoAfter;

    @Column(name = "date_time")
    private LocalDateTime dateTime;
}
