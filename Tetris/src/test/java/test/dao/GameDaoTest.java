package test.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.dao.Database;
import tetris.dao.GameDao;
import tetris.domain.OldGame;
/**
 *
 * @author alisaelizarova
 */
public class GameDaoTest {
    Database database;
    OldGame game;
    GameDao dao;
    String userId;
    
    public GameDaoTest() { 
    }
    

    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        this.database = new Database("jdbc:sqlite:test.db");
        database.init();
        this.game = new OldGame(10);
        this.dao = new GameDao(database);
        this.userId = "userId";
    }
    
    @Test 
    public void aNewGameCanBeAdded() throws SQLException {
        int size = this.dao.findAllGamesOfOneUser(userId).size();
        this.dao.save(this.game, this.userId);
        assertTrue(this.dao.findAllGamesOfOneUser(userId).size() == size + 1);
    }
}
