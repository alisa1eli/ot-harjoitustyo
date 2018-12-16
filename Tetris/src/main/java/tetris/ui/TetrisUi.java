/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tetris.dao.Database;
import tetris.dao.GameDao;
import tetris.dao.UserDao;
import tetris.domain.TetrisService;
import tetris.domain.User;

import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import tetris.domain.Game;
import tetris.domain.OldGame;

/**
 *
 * @author alisaelizarova
 */
public class TetrisUi extends Application {
    
    private TetrisService tetrisService;
    private Scene signInOrSignUpScene;
    private Scene signInScene;
    private Scene signUpScene;
    private Scene personalScene;
    private Scene gameOverScene;
    private Scene gameScene;
    
    @Override
    public void init() throws ClassNotFoundException {
        Database db = new Database("jdbc:sqlite:tetris.db");
        db.init();
        
        GameDao gameDao = new GameDao(db);
        UserDao userDao = new UserDao(db);
  
        this.tetrisService = new TetrisService(gameDao, userDao);

    }

    @Override
    public void start(Stage stage) throws Exception {
        
        // signInOrSignUpScene
        
        VBox firstPagePane = new VBox(10);
//        loginPane.setPadding(new Insets(20));
//        loginPane.setSpacing(30);
        firstPagePane.setAlignment(Pos.CENTER);
        Label firstPageLabel = new Label();
        firstPageLabel.setId("label");

        
        Button signInButton = new Button("Sign in");
        signInButton.setId("button");
        Button signUpButton = new Button("Sign up");
        signUpButton.setId("button");
               
        signUpButton.setOnAction(e-> {
            stage.setScene(signUpScene);   
        }); 
        
        signInButton.setOnAction(e-> {
            stage.setScene(signInScene);   
        }); 
        
        firstPagePane.getChildren().addAll(signInButton, signUpButton, firstPageLabel);      
        firstPagePane.setId("basic");
        signInOrSignUpScene = new Scene(firstPagePane, 385, 770);    
        signInOrSignUpScene.getStylesheets().add("file:style.css");
        
        stage.setScene(signInOrSignUpScene);
        
        // signUpScene
        
        VBox newUserPane = new VBox(10);
        
        HBox newUsernamePane = new HBox(10);
        newUsernamePane.setPadding(new Insets(10));
        TextField newLoginInput = new TextField();
        newLoginInput.setId("textfield");
        Label newUsernameLabel = new Label("Login");
        newUsernameLabel.setId("label");
        newUsernameLabel.setPrefWidth(100);
        newUsernamePane.getChildren().addAll(newUsernameLabel, newLoginInput);
        newUsernamePane.setAlignment(Pos.CENTER);
        newUsernamePane.setPadding(new Insets(50));
     
        HBox newNamePane = new HBox(10);
        newNamePane.setPadding(new Insets(10));
        TextField newNameInput = new TextField();
        newNameInput.setId("textfield");
        Label newNameLabel = new Label("Name");
        newNameLabel.setId("label");
        newNameLabel.setPrefWidth(100);
        newNamePane.getChildren().addAll(newNameLabel, newNameInput); 
        newNamePane.setAlignment(Pos.CENTER);
        newNamePane.setPadding(new Insets(0, 50, 50, 50));
        
        Button backSignUpButton = new Button("Back");
        backSignUpButton.setPadding(new Insets(10));
        backSignUpButton.setOnAction(e-> {
            stage.setScene(signInOrSignUpScene);   
        });
        backSignUpButton.setId("button");
        
        Label userCreationMessage = new Label();
        
        Button signUpSceneButton = new Button("Sign up!");
        signUpSceneButton.setPadding(new Insets(10));
        signUpSceneButton.setId("button");

        signUpSceneButton.setOnAction(e-> {
            String login = newLoginInput.getText();
            String name = newNameInput.getText();
   
            if (login.length() <= 3 || name.length() <= 3) {
                userCreationMessage.setText("Your login or name is too short!");
                userCreationMessage.setTextFill(Color.RED);              
            } else 
                try {
                    if (this.tetrisService.createUser(login, name)) {
                        userCreationMessage.setText("");
                        newLoginInput.setText("");
                        firstPageLabel.setText("New user successfully created!");
                        firstPageLabel.setTextFill(Color.GREEN);
                        newNameInput.setText("");
                        
                        stage.setScene(signInOrSignUpScene);
                    } else {
                        userCreationMessage.setText("This login is already taken!");        
                        userCreationMessage.setTextFill(Color.RED);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(TetrisUi.class.getName()).log(Level.SEVERE, null, ex);
                }
 
        });  
        
        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, 
                newNamePane, signUpSceneButton, backSignUpButton); 
        newUserPane.setAlignment(Pos.CENTER);
        newUserPane.setId("basic");
        signUpScene = new Scene(newUserPane, 385, 770);
        signUpScene.getStylesheets().add("file:style.css");
        
        // signInScene
        
        // for personalScene
        Label oldGamesLabel = new Label();
        oldGamesLabel.setPadding(new Insets(10));
        oldGamesLabel.setId("label");
        Label bestGame1 = new Label();
        bestGame1.setId("label");
        Label bestGame2 = new Label();
        bestGame2.setId("label");
        Label bestGame3 = new Label();
        bestGame3.setId("label");
        VBox listOfGames = new VBox(10);
        listOfGames.setAlignment(Pos.CENTER);
        listOfGames.setPadding(new Insets(0, 0, 50, 0));
        // end
        
        VBox signInPane = new VBox(10);
        signInPane.setPadding(new Insets(10));
        TextField loginSignIn = new TextField();
        loginSignIn.setId("textfield");
        Label labelSignIn = new Label("Your login: ");
        labelSignIn.setId("label");
        Button signInSceneButton = new Button("Sign In!");
        signInSceneButton.setId("button");
//        signInSceneButton.setPadding(new Insets(10));
        Button backSignInButton = new Button("Back");
        backSignInButton.setId("button");
//        backSignInButton.setPadding(new Insets(10));
        Label signInSceneMessage = new Label();
        signInSceneMessage.setId("label");
        
        HBox loginSignInPane = new HBox(10);
        loginSignInPane.getChildren().addAll(labelSignIn, loginSignIn); 
        loginSignInPane.setAlignment(Pos.CENTER);
        loginSignInPane.setPadding(new Insets(0, 50, 50, 50));
        
        labelSignIn.setPrefWidth(100);
        
        
        backSignInButton.setOnAction(e-> {
            stage.setScene(signInOrSignUpScene);   
        });
        
        Label welcomePersonalSceneLabel = new Label("Oops, you are not signed in!");
        welcomePersonalSceneLabel.setId("label");
        
        signInSceneButton.setOnAction(e-> {
            String login = loginSignIn.getText();
            try {
                if (this.tetrisService.userWithThatLoginFound(login)) {
                    welcomePersonalSceneLabel.setText("Welcome " + this.tetrisService.getSignedInUser().getName() + "!");
                    loginSignIn.setText("");
                            // the next lines checks if there any saved old games. If so, 3 best are showed.
                            // Start:
                            
                            oldGamesLabel.setText("Your best games:");
                            List<OldGame> oldGamesList = this.tetrisService.getUsersOldGames();
                            if(oldGamesList.isEmpty()) {
                                oldGamesLabel.setText("");
                            } else {
                                bestGame1.setText(oldGamesList.get(0).toString());
                                if (oldGamesList.size() > 2) {
                                    bestGame2.setText(oldGamesList.get(1).toString());
                                    bestGame3.setText(oldGamesList.get(2).toString());
                                } else {
                                    bestGame2.setText(oldGamesList.get(1).toString());
                                }
                            }

                            // End.
                    stage.setScene(personalScene);
                } else {
                    signInSceneMessage.setText("Wrong login!");
                    signInSceneMessage.setTextFill(Color.RED);
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(TetrisUi.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        });  
        
        signInPane.getChildren().addAll(signInSceneMessage, loginSignInPane, 
                signInSceneButton, backSignInButton); 
        signInPane.setId("basic");
        signInScene = new Scene(signInPane, 385, 770);
        signInScene.getStylesheets().add("file:style.css");
        
        // personalScene
        
        VBox personalScenePane = new VBox(10);
        personalScenePane.setPadding(new Insets(10));

        Button newGameButton = new Button("New game!");
        newGameButton.setId("button");
        newGameButton.setPadding(new Insets(10));

        listOfGames.getChildren().addAll(bestGame1, bestGame2, bestGame3);
        
        boolean gameStarted = false;
        
        Label gameOverMessage = new Label("Game Over!");
        gameOverMessage.setId("label");
        
        Label scoreOfTheGameLabel = new Label("");
        scoreOfTheGameLabel.setAlignment(Pos.CENTER);
        scoreOfTheGameLabel.setPrefWidth(385);
        scoreOfTheGameLabel.setId("scoreLabel");
        
        
        // New game's starting: 
        // Start: 
        newGameButton.setOnAction(e-> {
            Canvas gameField = new Canvas(420, 770);
            
            Game game = new Game();
            int height = game.getHeigth() - 4;
            int lenght = game.getLength();
            int[][] a = game.visible();

            GraphicsContext gc = gameField.getGraphicsContext2D();
            gc.setFill(Color.BLACK);

            BorderPane asettelu = new BorderPane();
            asettelu.setCenter(gameField);
            asettelu.setTop(scoreOfTheGameLabel);
            
            new AnimationTimer() {
                long edellinen = 0;
                               
                @Override
                public void handle(long now) {
                    if (now - edellinen <  ( (long) 500000000 )) {
                        return;
                    }
                    scoreOfTheGameLabel.setText("Your score: " + game.getPoints());
                    for (int y = 0; y < height; y++) {
                        for (int x = 0; x < lenght; x++) {
//                            if (game.visible()[x][y] != 0) {
//                                            gc.setFill(Color.web("#c57e00"));
//                                            gc.fillRect(x * 35, y * 35, 35, 35);
//                                        } else {
//                                            gc.setFill(Color.WHITE);
//                                            gc.fillRect(x * 35, y * 35, 35, 35);
//                                        }
                                Color c = Color.web(game.getColor(x, y));
                                gc.setFill(c);
                                gc.fillRect(x * 35, y * 34, 35, 34);

                        }
                    }
                    if ( game.update() ) {
                        gameScene.setOnKeyPressed(e -> {
                            if (e.getCode() == KeyCode.SPACE) {
                                game.makeMove(3);
                                this.edellinen = 500000000 + 1;
                                return;
                            } else {
                                if (e.getCode() == KeyCode.RIGHT) {
                                    game.makeMove(1);
                                } else if (e.getCode() == KeyCode.LEFT) {
                                    game.makeMove(2);
                                } else if (e.getCode() == KeyCode.UP) {
                                    game.rotate();
                                } else if (e.getCode() == KeyCode.DOWN) {
                                    game.update();
                                }  
                            }
                            for (int y = 0; y < height; y++) {
                                    for (int x = 0; x < lenght; x++) {
//                                        if (game.visible()[x][y] != 0) {
//                                            gc.setFill(Color.web("#585858"));
//                                            gc.setFill(Color.web("#c57e00"));
//                                            gc.fillRect(x * 35, y * 35, 35, 35);
//                                        } else {
//                                            gc.setFill(Color.WHITE);
//                                            gc.fillRect(x * 35, y * 35, 35, 35);
//                                        }
                                        Color c = Color.web(game.getColor(x, y));
                                        gc.setFill(c);
                                        gc.fillRect(x * 35, y * 34, 35, 34);
                                    }
                                }
                        });
                    } else {
                        gameOverMessage.setText("Game over! Your score is " + game.getPoints() + " .");
                        stage.setScene(gameOverScene);
                        this.stop();
                    }
                    this.edellinen = now;
                }
                
            }.start();
            
            gameScene = new Scene(asettelu, 385, 770);
            gameScene.getStylesheets().add("file:style.css");
            stage.setScene(gameScene);
            stage.show();

        });
        Button signOutButton = new Button("Sign out");

        signOutButton.setId("button");
        signOutButton.setOnAction(e-> {
            this.tetrisService.signOutTheCurrentUser();
            stage.setScene(this.signInOrSignUpScene);   
        }); 

        personalScenePane.getChildren().addAll(welcomePersonalSceneLabel,  
                newGameButton, oldGamesLabel, listOfGames, signOutButton); 
        personalScenePane.setAlignment(Pos.CENTER);
        personalScenePane.setId("basic");
        personalScenePane.getStylesheets().add("file:style.css");
        personalScene = new Scene(personalScenePane, 385, 770);
        
        
        // gameoverScene
        
        VBox gameOverPane = new VBox(10);
        gameOverPane.setPadding(new Insets(20));
        gameOverPane.setSpacing(30);
        gameOverPane.setAlignment(Pos.CENTER);
        Label gameSavedLabel = new Label();
        gameSavedLabel.setId("label");

        
        Button backToPersonalPageButton = new Button("Back");
        Button saveThisScoreButton = new Button("Save this score");
        backToPersonalPageButton.setId("button");
        saveThisScoreButton.setId("button");
               
        backToPersonalPageButton.setOnAction(e-> {
            gameSavedLabel.setText("");
            stage.setScene(personalScene);   
        });

        saveThisScoreButton.setOnAction(e-> {
            gameSavedLabel.setText("Saved!");
            int points = 0;
            String message = gameOverMessage.getText();
            points = Integer.parseInt(message.split(" ")[5]);
            try {
                this.tetrisService.addAnOldGame(points);

            } catch (SQLException ex) {
                Logger.getLogger(TetrisUi.class.getName()).log(Level.SEVERE, null, ex);
            }
            // the next lines checks if there any saved old games. If so, 3 best are showed.
            // Start:
            List<OldGame> oldGamesList;
            try {
                oldGamesLabel.setText("Your best games:");
                oldGamesList = this.tetrisService.getUsersOldGames();
                bestGame1.setText(oldGamesList.get(0).toString());
                if (oldGamesList.size() > 2) {
                    bestGame2.setText(oldGamesList.get(1).toString());
                    bestGame3.setText(oldGamesList.get(2).toString());
                } else if (oldGamesList.size() == 2){
                    bestGame2.setText(oldGamesList.get(1).toString());
                }
            } catch (SQLException ex) {
                Logger.getLogger(TetrisUi.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // End.
        });
        
        
        gameOverPane.getChildren().addAll(gameOverMessage, backToPersonalPageButton, saveThisScoreButton, gameSavedLabel);       
        gameOverPane.setId("basic");
        gameOverScene = new Scene(gameOverPane, 385, 770);    
        gameOverScene.getStylesheets().add("file:style.css");

        
        stage.setScene(signInOrSignUpScene);
        
        
        stage.setTitle("Tetris!");
        stage.show();

    }
}
 

