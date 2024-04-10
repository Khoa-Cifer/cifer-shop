package com.cifer.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.aspectj.weaver.ast.Or;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    //The user of website, who can be buyer, seller or manager and they have different roles
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String firstName;
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private String address;
    private String phoneNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private ImageData data;
    @OneToMany(mappedBy = "user")
    private Set<Wishlist> wishlistSet;
    @OneToMany(mappedBy = "user")
    private Set<Cart> cartSet;
    @OneToMany(mappedBy = "user")
    private Set<Payment> paymentSet;
    @OneToMany(mappedBy = "user")
    private Set<Order> orderSet;
    @OneToMany(mappedBy = "user")
    private Set<Shipment> shipmentSet;

}
