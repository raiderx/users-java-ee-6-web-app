package org.karpukhin.userswebapp.beans;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.karpukhin.userswebapp.entities.User;

/**
 * @author Pavel Karpukhin
 * @since 24.09.13
 */
@Named
@Singleton
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "maven-web-app")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
}
