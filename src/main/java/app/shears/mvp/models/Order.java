package app.shears.mvp.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "master_id", referencedColumnName = "id")
    private Master master;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
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

    public Order(Master master, Customer customer, Set<Service> services, String location,
                 byte[] photoBefore, byte[] photoAfter, LocalDateTime dateTime) {
        this.master = master;
        this.customer = customer;
        this.services = services;
        this.location = location;
        this.photoBefore = photoBefore;
        this.photoAfter = photoAfter;
        this.dateTime = dateTime;
    }

    public Order(Customer customer, Set<Service> services, String location, LocalDateTime dateTime) {
        this.customer = customer;
        this.services = services;
        this.location = location;
        this.dateTime = dateTime;
    }
}
