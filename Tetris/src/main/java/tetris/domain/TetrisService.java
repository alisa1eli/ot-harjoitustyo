/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import tetris.dao.GameDao;
import tetris.dao.UserDao;

/**
 * This class contains logics of Tetris UI.
 * 
 * @author alisaelizarova
 */
public class TetrisService {
    private GameDao gameDao;
    private UserDao userDao;
    private User signedIn;
    
    /**
     * This method defines GameDao and UserDao.
     * 
     * @param gameDao (GameDao)
     * @param userDao (UserDao)
     */
    public TetrisService(GameDao gameDao, UserDao userDao) {
        this.userDao = userDao;
        this.gameDao = gameDao;
        this.signedIn = null;        
    }
    
    /**
    * This method checks if the user with the login is in the db.
    * 
    * @param   login   (String) - unique
    * 
    * @return true if the user is found, otherwise false 
    * @throws SQLException if db not found
    */ 
    public boolean userWithThatLoginFound(String login) throws SQLException { 
        this.signedIn = userDao.findOne(login);
        if (this.signedIn != null) {
            this.signedIn.setOldGames(this.gameDao.findAllGamesOfOneUser(this.signedIn.getId()));
            return true;
        } 
        return false;
    }
    
    /**
    * This method creates a new user.
    * 
    * @param   login   (String) - unique
    * @param   name   (String)
    * 
    * @return true if a new user created, otherwise false 
    * @throws SQLException if db not found
    */ 
    public boolean createUser(String login, String name) throws SQLException  {   
        if (userDao.findOne(login) != null) {
            return false;
        }
        User user = new User(login, name);
        try {
            userDao.save(user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
    /**
    * This method return the signed in user. 
    * 
    * @return true if there is a signed in user, false otherwise 
    */
    public User getSignedInUser() {
        if (this.signedIn != null) {
            
            return this.signedIn;
        }
        return null;
    }
    
    /**
    * this method adds an new OldGame to the db and at the same time to the user's list.
    * 
    * @param   points   (int) 
    * @throws SQLException if db not found
    */
    public void addAnOldGame(int points) throws SQLException {
        OldGame game = this.gameDao.save(new OldGame(points), this.signedIn.getId());
        this.signedIn.addAnOldGame(game);
    }
    
    /**
    * this method returns user's old games sorted by score. 
    * The games with the highest scores are on the top.
    * 
    * @return   list of OldGames (ArrayList<OldGame>) 
    * @throws   SQLException if db not found
    */
    public ArrayList<OldGame> getUsersOldGames() throws SQLException {
        if (this.signedIn == null) {
            return null;
        }
        ArrayList<OldGame> r = this.signedIn.getOldGamesSortedByScore();
        return r;
    }
    
    /**
    * When an user sign out, this method sets "null" to the parameter "signedIn".
    */
    public void signOutTheCurrentUser() {
        this.signedIn = null;
    }
    
    /**
    * This method updates signed in user's name.
    * @param name new name (String)
    * 
    * @throws   SQLException if db not found
    */
    public void updateUsersName(String name) throws SQLException {
        this.signedIn.setName(name);
        this.userDao.update(signedIn);
    }

}
