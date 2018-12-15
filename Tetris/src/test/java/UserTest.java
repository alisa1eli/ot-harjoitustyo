/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Collections;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.domain.OldGame;
import tetris.domain.User;

/**
 *
 * @author alisaelizarova
 */
public class UserTest {
    User user;
    
    @Before
    public void setUp() {
       this.user = new User("User", "Password");
    }

//    @Test 
//    public void anOldGameCanBeAdded() {
//        this.user.addAnOldGame(120);
//        assertTrue(1 == this.user.getOldGames().size());
//    }
//    @Test
//    public void whenAnOldGameAddedTheScoreSavedCorrectly() {
//        this.user.addAnOldGame(120);
//        assertTrue(120 == this.user.getOldGames().get(0).getScore());
//    }
//    @Test 
//    public void userCanDeleteAnOldGameWithItsId() {
//        this.user.addAnOldGame(120);
//        String id = this.user.getOldGames().get(0).getId();
//        this.user.deleteAnOldGame(id);
//        assertTrue(0 == this.user.getOldGames().size());
//    }
    @Test
    public void nameCanBeChanged() {
        this.user.setName("Name");
        assertEquals("Name",this.user.getName());
    }
    @Test
    public void gamesCanBeSortedByScore() {
        OldGame a = new OldGame(10);
        OldGame b = new OldGame(12);
        OldGame c = new OldGame(16);
        OldGame d = new OldGame(8);
        OldGame e = new OldGame(12);
        ArrayList<OldGame> games = new ArrayList<>();
        games.add(a);
        games.add(b);
        games.add(c);
        games.add(d);
        games.add(e);
        Collections.sort(games);
        assertTrue(16 == games.get(0).getScore());
    }
    @Test
    public void oldGamesCanBeSetted() {
        OldGame a = new OldGame(10);
        OldGame b = new OldGame(12);
        OldGame c = new OldGame(16);
        OldGame d = new OldGame(8);
        OldGame e = new OldGame(12);
        ArrayList<OldGame> games = new ArrayList<>();
        games.add(a);
        games.add(b);
        games.add(c);
        games.add(d);
        games.add(e);
        this.user.setOldGames(games);
        assertTrue(5 == this.user.getOldGames().size());
    }
    @Test
    public void aGameCanBeDeletedByItsId() {
        OldGame a = new OldGame(1, 10, "d");
        OldGame b = new OldGame(2, 12, "d");
        ArrayList<OldGame> games = new ArrayList<>();
        games.add(a);
        games.add(b);
        this.user.setOldGames(games);
        this.user.deleteAnOldGame(2);
        assertTrue(1 == this.user.getOldGames().get(0).getId());
    }
    @Test
    public void gamesCanBeSortedByScoreAndArrayCanBeCreated() {
        OldGame a = new OldGame(10);
        OldGame b = new OldGame(12);
        OldGame c = new OldGame(16);
        OldGame d = new OldGame(8);
        OldGame e = new OldGame(12);
        ArrayList<OldGame> games = new ArrayList<>();
        games.add(a);
        games.add(b);
        games.add(c);
        games.add(d);
        games.add(e);
        this.user.setOldGames(games);
        games = this.user.getOldGamesSortedByScore();
        assertTrue(12 == games.get(1).getScore());
    }
}
