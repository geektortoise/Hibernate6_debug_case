package org.example;

import domain.Customer;
import jakarta.persistence.*;
import org.hibernate.engine.spi.SessionImplementor;

import static org.hibernate.graph.GraphParser.parse;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = emf.createEntityManager();

        TypedQuery<Customer> query = entityManager.createQuery(
                        "select distinct c from Customer c" +
                                " left join c.addresses ad on ad.streetID = 'avenue' "
                        , Customer.class)
                .setHint("jakarta.persistence.fetchgraph", parse(Customer.class,
                        "addresses"
                        ,
                        entityManager.unwrap(SessionImplementor.class)))
        ;

        System.out.println("\nDEBUG QUERY");
        query.getResultList();

/*

Hibernate 6.4.4:
    select
        distinct c1_0.id,
        c1_0.CUST_STREET_ID,
        a2_0.STREET_ID,
        a2_0.id,
        a2_0.ZIP_CODE,
        c1_0.CUST_COUNTRY_CODE,
        c1_0.CUST_ZIP_CODE
    from
        customer c1_0
    left join
        address a1_0
            on c1_0.CUST_STREET_ID=a1_0.STREET_ID
            and (a1_0.ZIP_CODE in (4000, 4020, 4030))
            and a1_0.STREET_ID='avenue'
    left join
        address a2_0
            on c1_0.CUST_STREET_ID=a2_0.STREET_ID
            and (a2_0.ZIP_CODE in (4000, 4020, 4030))
            and (a2_0.ZIP_CODE in (4000, 4020, 4030))


However on Hibernate 5.6.15:
    select
        distinct customer0_.id as id1_1_0_,
        addresses1_.id as id1_0_1_,
        customer0_.CUST_COUNTRY_CODE as cust_cou2_1_0_,
        customer0_.CUST_STREET_ID as cust_str3_1_0_,
        customer0_.CUST_ZIP_CODE as cust_zip4_1_0_,
        addresses1_.STREET_ID as street_i2_0_1_,
        addresses1_.ZIP_CODE as zip_code3_0_1_,
        addresses1_.STREET_ID as street_i2_0_0__,
        addresses1_.id as id1_0_0__
    from
        customer customer0_
    left outer join
        address addresses1_
            on customer0_.CUST_STREET_ID=addresses1_.STREET_ID
            and (
                addresses1_.ZIP_CODE in (
                    4000,
                4020,
                4030))
                and (addresses1_.STREET_ID='avenue')
*/

    }
}
