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
 *
 * @author alisaelizarova
 */
public class UserDao implements Dao<User, String> {
    private final Database db;
    
    public UserDao(Database db) {
        this.db = db;
    } 
    
    @Override
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
    
    
    public User findOneByName(String name) throws SQLException {
        Connection connection = db.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM User WHERE name = ?");
        stmt.setObject(1, name);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        String id = rs.getString("id");
       
        User u = new User(id, name);

        rs.close();
        stmt.close();
        connection.close();

        return u;
    }
    
    @Override
    public List<User> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM User WHERE id = ? ");
        stmt.setString(1, id);
        
        stmt.executeUpdate();
        stmt.close();
    }
    
    public User saveOrUpdate(User object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public User save(User object) throws SQLException {
//         if id isn't unique, returns NULL
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
    
    // name can be only changed
    @Override
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