/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

import java.sql.SQLException;
import tetris.dao.GameDao;
import tetris.dao.UserDao;

/**
 *
 * @author alisaelizarova
 */
public class TetrisService {
    private GameDao gameDao;
    private UserDao userDao;
    private User loggedIn;
    
    public TetrisService( GameDao gameDao, UserDao userDao) {
        this.userDao = userDao;
        this.gameDao = gameDao;
    }
    
    /**
    * to create a new user
    * 
    * @param   login   (String) - unique
    * @param   name   (String)
    * 
    * @return true if a new user created, otherwise false 
    */ 
    
    public boolean createUser(String login, String name) throws SQLException  {   
        if (userDao.findOne(login) != null) {
            return false;
        }
        User user = new User(login, name);
        try {
            userDao.save(user);
        } catch(Exception e) {
            return false;
        }

        return true;
    }
}
