package caballero.eduardo.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String country;
    private String state;
    private String city;
    private String street;
    private String zipCode;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Order order;
}
