/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tetris.dao.GameDao;
import tetris.dao.UserDao;

/**
 *
 * @author alisaelizarova
 */
public class TetrisService {
    private GameDao gameDao;
    private UserDao userDao;
    private User signedIn;
    private Game game;
    private Boolean gameStarted;
    
    public TetrisService(GameDao gameDao, UserDao userDao) {
        this.userDao = userDao;
        this.gameDao = gameDao;
        this.signedIn = null;
        this.game = new Game();
        this.gameStarted = false;
        
    }
    
    public boolean userWithThatLoginFound(String login) throws SQLException { 
        this.signedIn = userDao.findOne(login);
        if (this.signedIn != null) {
            this.signedIn.setOldGames(this.gameDao.findAllGamesOfOneUser(this.signedIn.getId()));
            return true;
        } 
        return false;
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
        } catch (Exception e) {
            return false;
        }

        return true;
    }
    
    public User getSignedInUser() {
        if (this.signedIn != null) {
            
            return this.signedIn;
        }
        return null;
    }
    public boolean gameStarted() {
        return this.gameStarted;
    }
    public void setGameStart() {
        this.gameStarted = true;
    }
    public Game getGame() {
        return this.game;
    }
    public int[][] getVisible() {
        return this.game.visible();
    }
    /**
    * this method adds an new OldGame to the db and at the same time to the user's list.
    * 
    * @param   points   (int) 
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
    */
    public ArrayList<OldGame> getUsersOldGames() throws SQLException {
        if(this.signedIn == null) {
            return null;
        }
        ArrayList<OldGame> r = this.signedIn.getOldGamesSortedByScore();
        return r;
    }
}
