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
    }
}
