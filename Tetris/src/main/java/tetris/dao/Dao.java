/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.dao;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author alisaelizarova
 * @param <T>
 * @param <K>
 */
public interface Dao<T, K> {
    /** UserDao supports it, GameDao doesn't support it */
    T findOne(K key) throws SQLException;
    /** UserDao doesn't support it, GameDao doesn't support it (use findAllGamesOfOneUser instead) */
    List<T> findAll() throws SQLException;
    /** UserDao supports it, GameDao doesn't support it (please use save(OldGame object, String userId)) */
    T save(T object) throws SQLException;
    /** UserDao supports it, GameDao doesn't support it */
    T update(T object) throws SQLException;
    /** UserDao supports it, GameDao doesn't support it */
    void delete(K key) throws SQLException;
}
