/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.karpukhin.userswebapp.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.karpukhin.userswebapp.entities.User;

/**
 *
 * @author karpukhin-pavel
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "maven-web-app")
    private EntityManager em;

    @Inject
    private Search search;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

}
