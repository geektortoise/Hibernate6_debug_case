package domain;

import jakarta.persistence.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Where;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "CUST_STREET_ID", precision = 1)
    private String streetID;

    @Column(name = "CUST_ZIP_CODE", precision = 1)
    private String zipCode;

    @Column(name = "CUST_COUNTRY_CODE", precision = 1)
    private String countryCode;

    @OneToMany
    @JoinColumns({
            @JoinColumn(name = "STREET_ID", referencedColumnName = "CUST_STREET_ID", insertable = false, updatable = false, nullable = false),
            //@JoinColumn(name = "ZIP_CODE", referencedColumnName = "CUST_ZIP_CODE", insertable = false, updatable = false, nullable = false),
            //@JoinColumn(name = "COUNTRY_CODE", referencedColumnName = "CUST_COUNTRY_CODE", insertable = false, updatable = false, nullable = false)
    })
    @Where(clause = "ZIP_CODE in (4000,4020,4030)")
    @Filter(name = "onlyAvenue")
    @Filter(name = "onlyBoulevard")
    private Set<Address> addresses = new LinkedHashSet<>();

// Constructors, Getters, Setters, hashCode and so on...
}

