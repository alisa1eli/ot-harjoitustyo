package test.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.dao.Database;
import tetris.dao.UserDao;
import tetris.domain.User;

/**
 *
 * @author alisaelizarova
 */
public class UserDaoTest {
    Database database;
    User user;
    UserDao dao;
    
    public UserDaoTest() {
    }
    
    
    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        this.database = new Database("jdbc:sqlite:test.db");
        database.init();
        this.user = new User("alisa", "AlisaName");
        this.dao = new UserDao(database);
        this.dao.save(user);
        this.dao.save(new User("user2", "user2Name"));
        this.dao.save(new User("user3", "user3Name"));
    }
    
    @Test 
    public void aNewUserCanBeAdded () throws SQLException {
        User a = (User) this.dao.findOne(user.getId());
        assertEquals( a.getId(), "alisa" );
        assertEquals( a.getName(), "AlisaName" );
    }
    @Test 
    public void whenLookingForAnUnknownUserGetNull() throws SQLException {
        assertTrue(null == this.dao.findOne("bla"));
    }
    @Test 
    public void userCanBeFoundByTheirId() throws SQLException {
        assertEquals(  "AlisaName" , ((User)this.dao.findOne("alisa")).getName());
    }
    @Test
    public void userCanBeDeleted() throws SQLException {
        this.dao.delete("alisa");
        assertTrue( this.dao.findOne("alisa") == null);
    }
    @Test 
    public void userNameCanBeChanged() throws SQLException {
        this.dao.update(new User("alisa", "AlisaNewName"));
        assertEquals("AlisaNewName", this.dao.findOne("alisa").getName());
    }
}
