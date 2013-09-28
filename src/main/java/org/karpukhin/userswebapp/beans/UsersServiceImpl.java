package org.karpukhin.userswebapp.beans;

import org.karpukhin.userswebapp.entities.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Karpukhin
 * @since 28.09.13
 */
@Stateless
public class UsersServiceImpl implements UsersService {

    @Inject
    private UserFacade userFacade;
    @Inject
    private Search search;

    @Override
    public void create(User user) {
        userFacade.create(user);
        search.addUser(user);
    }

    @Override
    public void update(User user) {
        userFacade.edit(user);
        search.updateUser(user);
    }

    @Override
    public int count() {
        return userFacade.count();
    }

    @Override
    public User find(long id) {
        return userFacade.find(id);
    }

    @Override
    public List<User> findAll() {
        return userFacade.findAll();
    }

    @Override
    public List<User> findRange(int[] range) {
        return userFacade.findRange(range);
    }

    @Override
    public void remove(User user) {
        userFacade.remove(user);
    }

    @Override
    public List<User> search(String text) {
        List<User> ids = search.searchUsers(text);
        List<User> result = new ArrayList<User>(ids.size());
        for (User id : ids) {
            result.add(userFacade.find(id.getId()));
        }
        return result;
    }
}
