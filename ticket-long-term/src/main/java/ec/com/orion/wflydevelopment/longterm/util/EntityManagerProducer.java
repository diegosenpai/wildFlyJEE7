package ec.com.orion.wflydevelopment.longterm.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer {

    @Produces
    @PersistenceContext
    private EntityManager em;

}
