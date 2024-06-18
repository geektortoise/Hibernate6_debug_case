package domain;

import jakarta.persistence.*;
import org.hibernate.annotations.FilterDef;

@Entity
@Table(name = "address")
@FilterDef(name = "onlyAvenue", defaultCondition=" STREET_ID = 'avenue'")
@FilterDef(name = "onlyBoulevard", defaultCondition=" STREET_ID = 'boulevard'")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "STREET_ID", precision = 1)
    private String streetID;

    @Column(name = "ZIP_CODE", precision = 1)
    private String zipCode;

    /*@Column(name = "COUNTRY_CODE", precision = 1)
    private String countryCode;

    private String floor;*/
}
