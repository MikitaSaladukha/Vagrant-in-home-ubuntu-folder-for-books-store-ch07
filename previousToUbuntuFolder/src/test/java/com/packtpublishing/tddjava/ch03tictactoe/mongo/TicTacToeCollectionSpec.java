package com.packtpublishing.tddjava.ch03tictactoe.mongo;

import com.mongodb.MongoException;
import org.jongo.MongoCollection;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class TicTacToeCollectionSpec {

    TicTacToeCollection collection;
    TicTacToeBean bean;
    MongoCollection mongoCollection;

    @Before
    public void before() throws UnknownHostException {
        collection = spy(new TicTacToeCollection());
        bean = new TicTacToeBean(3, 2, 1, 'Y');
        mongoCollection = mock(MongoCollection.class);
    }

    @Test
    public void whenInstantiatedThenMongoCollectionHasDbNameTicTacToe() {
//            throws UnknownHostException {
//        TicTacToeCollection collection = new TicTacToeCollection();
        assertEquals(
                "tic-tac-toe",
                collection.getMongoCollection().getDBCollection().getDB().getName());
    }

    @Test
    public void whenInstantiatedThenMongoCollectionHasNameGame() {
//            throws UnknownHostException {
//        TicTacToeCollection collection = new TicTacToeCollection();
        assertEquals(
                "game",
                collection.getMongoCollection().getName());
    }

    @Test
    public void whenSaveMoveThenInvokeMongoCollectionSave() {
//        TicTacToeBean bean = new TicTacToeBean(3, 2, 1, 'Y');
//        MongoCollection mongoCollection = mock(MongoCollection.class);
        doReturn(mongoCollection).when(collection).getMongoCollection();
        collection.saveMove(bean);
        verify(mongoCollection).save(bean);
    }

    @Test
    public void whenSaveMoveThenReturnTrue() {
//        TicTacToeBean bean = new TicTacToeBean(3, 2, 1, 'Y');
//        MongoCollection mongoCollection = mock(MongoCollection.class);
        doReturn(mongoCollection).when(collection).getMongoCollection();
        assertTrue(collection.saveMove(bean));
    }

    @Test
    public void givenExceptionWhenSaveMoveThenReturnFalse() {
        doThrow(new MongoException("Bla"))
                .when(mongoCollection)
                .save(any(TicTacToeBean.class));
        doReturn(mongoCollection).when(collection).getMongoCollection();
        assertFalse(collection.saveMove(bean));
    }

    @Test
    public void whenDropThenInvokeMongoCollectionDrop() {
        doReturn(mongoCollection).when(collection).getMongoCollection();
        collection.drop();
        verify(mongoCollection).drop();
    }

    @Test
    public void whenDropThenReturnTrue() {
        doReturn(mongoCollection).when(collection).getMongoCollection();
        assertTrue(collection.drop());
    }

    @Test
    public void givenExceptionWhenDropThenReturnFalse() {
        doThrow(new MongoException("Bla")).when(mongoCollection).drop();
        doReturn(mongoCollection).when(collection).getMongoCollection();
        assertFalse(collection.drop());
    }

}
