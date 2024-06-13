package domain;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
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
