/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
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

    @Test 
    public void anOldGameCanBeAdded() {
        this.user.addAnOldGame(120);
        assertTrue(1 == this.user.getOldGames().size());
    }
}
