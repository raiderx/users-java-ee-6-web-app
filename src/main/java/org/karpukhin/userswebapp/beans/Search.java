package org.karpukhin.userswebapp.beans;

import org.karpukhin.userswebapp.entities.User;

import javax.inject.Singleton;
import javax.inject.Named;
import java.util.List;

/**
 * @author Pavel Karpukhin
 * @since 24.09.13
 */
@Named
@Singleton
public class Search {

    public void indexUser(User user) {
    }

    public List<User> searchUsers(String text) {
        return null;
    }
}
