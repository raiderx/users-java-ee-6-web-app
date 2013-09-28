package org.karpukhin.userswebapp.beans;

import org.karpukhin.userswebapp.entities.User;

import java.util.List;

/**
 * @author Pavel Karpukhin
 * @since 28.09.13
 */
public interface UsersService {

    void create(User user);

    void update(User user);

    int count();

    User find(long id);

    List<User> findAll();

    List<User> findRange(int[] range);

    void remove(User user);

    List<User> search(String text);
}
