# The architure of the programm

In the programm there are 4 main packages:
1. tetris.main
2. tetris.ui
3. tetris.domain
4. tetris.dao

The class main.java is in tetris.main. Its only task is to launch a java file named TetrisUi.java.

TetrisUi is in the package tetris.ui. This file creates UI with the help of css file that is in the root directory. Also through UI an user can interact with the TetrisService and play a game. So class TetrisUi works closely with classes TetrisService and Game.

TetrisService is in charge of the logics of handling information of users and their games. This class saves new users to the database, shows users' games, add a new game to the database and make sure that only signed in user can start a new game, see only their games and change their name. TetrisService can handle only one person at a time.

TetrisService use mainly two classes: User and OldGame. The class User is located in the same package and it defines an user (a player). OldGame is a class that decsribes the games that have been played and saved to the database. TetrisService usually handle 0-n old games at a time. 

Class Game is in charge of the logics and process of game itse, tetris. With the help of other classes, Rotate, Move, Matrix, Block and Level, the class creates a real-time tetris. 

The package tetris.dao is a part of the programm that deals with saving and extracting information from the database. Class Database contains sqlite commands to create and database. Also this class creates a connection to the database. Class GameDao can save and find all saved games of one user from the db. Class UserDao allows us to save a new user, find them by their login, update their name and delete them. 

