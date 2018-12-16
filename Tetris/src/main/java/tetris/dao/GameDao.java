package tetris.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tetris.domain.OldGame;

/**
 *
 * @author alisaelizarova
 */
public class GameDao implements Dao<OldGame, Integer> {
    private final Database db;
    
    public GameDao(Database db) {
        this.db = db;
    } 
    
    public ArrayList<OldGame> findAllGamesOfOneUser(String key) throws SQLException {
        Connection connection = db.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Game WHERE userId = ?");
        stmt.setObject(1, key);
        
        ArrayList<OldGame> games = new ArrayList<OldGame>();
        
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            String date = rs.getString("date");
            
            games.add(new OldGame(rs.getInt("id"), rs.getInt("score"), date));
        }

        rs.close();
        stmt.close();
        connection.close();

        return games;
    }
    
    public OldGame save(OldGame object, String userId) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Game"
                + " (userId, date, score)"
                + " VALUES (?,?,?)");
        stmt.setString(1, userId);
        stmt.setString(2, object.getDate());
        stmt.setInt(3, object.getScore());
         
        stmt.executeUpdate();
        
        stmt = conn.prepareStatement("SELECT * FROM Game WHERE userId = ? AND date = ? AND score = ?");
        stmt.setString(1, userId);
        stmt.setString(2, object.getDate());
        stmt.setInt(3, object.getScore());
        
        ResultSet rs = stmt.executeQuery();
        
        object.setId(rs.getInt(1));
        
        stmt.close();
 
        return object;
    }

    @Override
    public OldGame findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<OldGame> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OldGame update(OldGame object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OldGame save(OldGame object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
