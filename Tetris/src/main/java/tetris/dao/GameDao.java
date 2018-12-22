package tetris.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tetris.domain.OldGame;

/**
 * This class interacts with table Game.
 * 
 * @author alisaelizarova
 */
public class GameDao {
    private final Database db;
    
    /**
     * This class sets database of this class.
     * @param db  (Database)
     */
    public GameDao(Database db) {
        this.db = db;
    } 
    
    /**
     * This method returns all games of one user.
     * @param key - login of that user
     * @throws SQLException if db is not found
     * @return list of old games (ArrayList<OldGame>)
     */
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
    
    /**
     * This class saves a game into db.
     * @param object - game to be saved (OldGame)
     * @param userId - user the game belongs to
     * @throws SQLException if db is not found
     * @return saved old game (OldGame)
     */
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
}
