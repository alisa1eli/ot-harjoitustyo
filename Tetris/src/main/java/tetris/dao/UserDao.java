/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import tetris.domain.User;

/**
 * This class interacts with table User.
 * 
 * @author alisaelizarova
 */
public class UserDao {
    private final Database db;
    
    /**
     * This class sets database of this class.
     * @param db  (Database)
     */
    public UserDao(Database db) {
        this.db = db;
    } 
    /**
     * This method returns one user with id (key).
     * @param key - id ( = login )
     * @throws SQLException if db is not found
     * @return an user (User)
     */
    public User findOne(String key) throws SQLException {
        Connection connection = db.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM User WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        String id = rs.getString("id");
        String name = rs.getString("name");
        

        User u = new User(id, name);

        rs.close();
        stmt.close();
        connection.close();

        return u;
    }
    
    /**
     * This method deletes an user with id.
     * @param id - user's id (= login) 
     * @throws SQLException if db is not found
     */
    public void delete(String id) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM User WHERE id = ? ");
        stmt.setString(1, id);
        
        stmt.executeUpdate();
        stmt.close();
    }
    
    
    /**
     * This method saves an new user into db.
     * If user's id already in db, the method return NULL;
     * @param object - user 
     * @throws SQLException if db is not found
     * @return saved user 
     */
    public User save(User object) throws SQLException {
        User alreadyExists = this.findOne(object.getId());
        if (alreadyExists != null) {
            return null;
        }
        
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO User"
                + " (id, name)"
                + " VALUES (?,?)");
        stmt.setString(1, object.getId());
        stmt.setString(2, object.getName());
         
        stmt.executeUpdate();
        stmt.close();
 
        return object;
    }
    
    /**
     * This method updates user's name. Only name can be updated.
     * @param object - user 
     * @throws SQLException if db is not found
     * @return user with the new name
     */
    public User update(User object) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE User SET "
                + "name = ? where id = ?");
        stmt.setString(1, object.getName());
        stmt.setString(2, object.getId());
        
        stmt.executeUpdate();
        stmt.close();
        conn.close();
        
        return object;
    }
}