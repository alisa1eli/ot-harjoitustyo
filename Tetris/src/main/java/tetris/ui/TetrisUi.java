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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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
    private Scene changeNameScene;
    private Scene gameSettingsScene;
    
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
        
        // signInOrSignUpScene -------------------------------------------------
        
        VBox firstPagePane = new VBox();
        Label firstPageLabel = new Label();
        firstPageLabel.setId("label");
        Label tetrisLabel = new Label("Tetris!");
        tetrisLabel.setId("title");
        
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
        
        firstPagePane.getChildren().addAll(tetrisLabel, signInButton, 
                signUpButton, firstPageLabel);      
        firstPagePane.setId("basic");
        signInOrSignUpScene = new Scene(firstPagePane, 385, 770);    
        signInOrSignUpScene.getStylesheets().add("file:style.css");
        
        stage.setScene(signInOrSignUpScene);
        
        
        
        
        // signUpScene  --------------------------------------------------------
        
        VBox newUserPane = new VBox(20);
        newUserPane.setPadding(new Insets(30));
        
        HBox newLoginPane = new HBox();
        newLoginPane.setId("insertInformation");
        TextField newLoginInput = new TextField();
        newLoginInput.setId("textfield");
        Label newUsernameLabel = new Label("Login");
        newUsernameLabel.setId("insertInformationLabel");
        newLoginPane.getChildren().addAll(newUsernameLabel, newLoginInput);

     
        HBox newNamePane = new HBox(25);
        newNamePane.setId("insertInformation");
        TextField newNameInput = new TextField();
        newNameInput.setId("textfield");
        Label newNameLabel = new Label("Name");
        newNameLabel.setId("insertInformationLabel");
        newNamePane.getChildren().addAll(newNameLabel, newNameInput);

        
        Button backSignUpButton = new Button("Back");
        backSignUpButton.setId("button");
        backSignUpButton.setOnAction(e-> {
            
            stage.setScene(signInOrSignUpScene);   
        });

        
        Label userCreationMessage = new Label();
        
        Button signUpSceneButton = new Button("Sign up!");
        signUpSceneButton.setPadding(new Insets(10));
        signUpSceneButton.setId("button");

        signUpSceneButton.setOnAction(e-> {
            String login = newLoginInput.getText();
            String name = newNameInput.getText();
   
            if (login.length() <= 3 || name.length() <= 3 || login.length() > 20 || name.length() > 20 ) {
                userCreationMessage.setText("Your login or name is too short or too long!");
                userCreationMessage.setTextFill(Color.PINK);              
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
                        userCreationMessage.setTextFill(Color.PINK);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(TetrisUi.class.getName()).log(Level.SEVERE, null, ex);
                }
 
        });  
        
        newUserPane.getChildren().addAll(newLoginPane, newNamePane);
        
        VBox newUserPaneAndButtons = new VBox();
        
        newUserPaneAndButtons.getChildren().addAll(userCreationMessage, newUserPane, 
                signUpSceneButton, backSignUpButton); 
        newUserPaneAndButtons.setId("basic");
        signUpScene = new Scene(newUserPaneAndButtons, 385, 770);
        signUpScene.getStylesheets().add("file:style.css");
        
        
        
        
        
        // signInScene ---------------------------------------------------------
        
        // for personalScene
        // Start:
        Label oldGamesLabel = new Label();
        oldGamesLabel.setPadding(new Insets(10));
        oldGamesLabel.setId("label");
        Label oldGamesInfoLabel = new Label();
        oldGamesInfoLabel.setPadding(new Insets(10));
        oldGamesInfoLabel.setId("label");
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
        
        VBox signInPane = new VBox();
        signInPane.setId("basic");
        
        TextField loginSignIn = new TextField();
        loginSignIn.setId("textfield");
        Label labelSignIn = new Label("Your login: ");
        labelSignIn.setId("insertInformationLabel");
        
        HBox loginSignInPane = new HBox();
        loginSignInPane.setPadding(new Insets(30));
        
        loginSignInPane.setId("insertInformation");
        loginSignInPane.getChildren().addAll(labelSignIn, loginSignIn);
        
        Button signInSceneButton = new Button("Sign In!");
        signInSceneButton.setId("button");
        Button backSignInButton = new Button("Back");
        backSignInButton.setId("button");
        Label signInSceneMessage = new Label();
        signInSceneMessage.setId("label");
        
         

        
        
        backSignInButton.setOnAction(e-> {
            loginSignIn.setText("");
            stage.setScene(signInOrSignUpScene);   
        });
        
        Label welcomePersonalSceneLabel = new Label("Oops, you are not signed in!");
        welcomePersonalSceneLabel.setId("title");
        
        signInSceneButton.setOnAction(e-> {
            String login = loginSignIn.getText();
            try {
                if (this.tetrisService.userWithThatLoginFound(login)) {
                    welcomePersonalSceneLabel.setText("Welcome\n" + this.tetrisService.getSignedInUser().getName() + "!");
                    loginSignIn.setText("");
                            // the next lines checks if there any saved old games. If so, 3 best are showed.
                            // Start:
                            
                            oldGamesLabel.setText("Your 3 best games:");
                            oldGamesInfoLabel.setText("Score | Date");
                            List<OldGame> oldGamesList = this.tetrisService.getUsersOldGames();
                            if(oldGamesList.isEmpty()) {
                                oldGamesLabel.setText("");
                                oldGamesInfoLabel.setText("");
                            } else {
                                bestGame1.setText(oldGamesList.get(0).toString());
                                if (oldGamesList.size() > 2) {
                                    bestGame2.setText(oldGamesList.get(1).toString());
                                    bestGame3.setText(oldGamesList.get(2).toString());
                                } else if (oldGamesList.size() == 2) {
                                    bestGame2.setText(oldGamesList.get(1).toString());
                                }
                            }

                            // End.
                    stage.setScene(personalScene);
                } else {
                    signInSceneMessage.setText("Wrong login!");
                    signInSceneMessage.setTextFill(Color.PINK);
                    
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
        
        
        
        
        // personalScene -------------------------------------------------------
        
        VBox personalScenePane = new VBox(10);
        personalScenePane.setPadding(new Insets(10));
        
        Label nextGameInfoLabel = new Label("arcade and fading colors");
        nextGameInfoLabel.setId("label");
        
        Button newGameButton = new Button("New game!");
        newGameButton.setId("button");
        newGameButton.setPadding(new Insets(10));

        listOfGames.getChildren().addAll(bestGame1, bestGame2, bestGame3);
              
        Label gameOverMessage = new Label("Game Over!");
        gameOverMessage.setId("label");
        
        Button toChangeYourName = new Button("Change the name");
        toChangeYourName.setId("button");
        toChangeYourName.setOnAction(e-> {
            stage.setScene(changeNameScene);   
        });
        
        Button toChangeGameSettings = new Button("Game settings");
        toChangeGameSettings.setId("button");
        toChangeGameSettings.setOnAction(e-> {
            stage.setScene(gameSettingsScene);   
        });
        
        // This label will be used during the game:
        Label scoreOfTheGameLabel = new Label("");
        scoreOfTheGameLabel.setAlignment(Pos.CENTER);
        scoreOfTheGameLabel.setPrefWidth(385);
        scoreOfTheGameLabel.setId("scoreLabel");
        
        
        // New game's starting: 
        // Start: 
        newGameButton.setOnAction(e-> {
            Canvas gameField = new Canvas(420, 770);
            
            Game game = new Game();
            game.setParametersOfTheNextGame(nextGameInfoLabel.getText());
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
                    if (now - edellinen <  (long) game.getSpeed() ) {
                        return;
                    }
                    scoreOfTheGameLabel.setText("Your score: " + game.getPoints());
                    for (int y = 0; y < height; y++) {
                        for (int x = 0; x < lenght; x++) {
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

        personalScenePane.getChildren().addAll(welcomePersonalSceneLabel, nextGameInfoLabel,  
                newGameButton, oldGamesLabel, oldGamesInfoLabel, listOfGames, 
                toChangeYourName, toChangeGameSettings, signOutButton); 
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
                oldGamesLabel.setText("Your 3 best games:");
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
        
        // ChangeTheNameScene --------------------------------------------------
        Label wrongUpdatedNamelabe = new Label();
        wrongUpdatedNamelabe.setId("label");
        HBox updateNameSmallPane = new HBox();
        updateNameSmallPane.setId("insertInformation");
        Label updatedNameLabel = new Label("New name");
        updatedNameLabel.setId("insertInformationLabel");
        TextField updatedNameTextField = new TextField();
        updateNameSmallPane.getChildren().addAll(updatedNameLabel, updatedNameTextField);
        
        Button updateNameButton = new Button("Update!");
        updateNameButton.setId("button");
        Button backUpdateNameButton = new Button("Back");
        backUpdateNameButton.setId("button");
        backUpdateNameButton.setOnAction(e-> {
            wrongUpdatedNamelabe.setText("");
            updatedNameTextField.setText("");
            stage.setScene(personalScene);   
        });
        updateNameButton.setOnAction(e-> {
            String updatedName = updatedNameTextField.getText();
            if (!updatedName.isEmpty() ) {
                if (updatedName.equals(this.tetrisService.getSignedInUser().getName())) {
                    wrongUpdatedNamelabe.setText("Your current name is the same.");
                } else {
                    if (updatedName.length() > 3 && updatedName.length() < 15) {
                        try {
                            this.tetrisService.updateUsersName(updatedName);
                            welcomePersonalSceneLabel.setText("Welcome\n" + this.tetrisService.getSignedInUser().getName() + "!");
                            wrongUpdatedNamelabe.setText("Successfully updated!");

                        } catch (SQLException ex) {
                            Logger.getLogger(TetrisUi.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        wrongUpdatedNamelabe.setText("Too long or too short!");
                    }
                }
            }
            updatedNameTextField.setText("");   
        });
        
        VBox updatedNamePane = new VBox();
        updatedNamePane.getChildren().addAll(wrongUpdatedNamelabe, updateNameSmallPane, updateNameButton, backUpdateNameButton );
        
        changeNameScene = new Scene(updatedNamePane, 385, 770); 
        updatedNamePane.setId("basic");   
        changeNameScene.getStylesheets().add("file:style.css");
        
        // gameSettingsScene ---------------------------------------------------
        
        Label settingInfoLabel = new Label(" Here, you can choose \n "
                + "how fast blocks will move and \n "
                + "if their colors fading or not. \n \n "
                + "Remember to save your changes! \n ");
        settingInfoLabel.setId("label");
        Label speedLabel = new Label("Speed:");
        speedLabel.setId("label");
        
        VBox speeds = new VBox();
        speeds.setAlignment(Pos.CENTER);
        RadioButton option1 = new RadioButton("Arcade");
        RadioButton option2 = new RadioButton("Slow");
        RadioButton option3 = new RadioButton("Medium");
        RadioButton option4 = new RadioButton("Fast");
        ToggleGroup radioGroup1 = new ToggleGroup();
        option1.setId("radiobutton");
        option2.setId("radiobutton");
        option3.setId("radiobutton");
        option4.setId("radiobutton");
        
        option1.setToggleGroup(radioGroup1);
        option2.setToggleGroup(radioGroup1);
        option3.setToggleGroup(radioGroup1);
        option4.setToggleGroup(radioGroup1);
        
        speeds.getChildren().addAll(option1, option2, option3, option4);
        
        Label fadingColorsLabel = new Label("Fading colors:");
        fadingColorsLabel.setId("label");
        VBox fatingColors = new VBox();
        fatingColors.setAlignment(Pos.CENTER);
        RadioButton yesFC = new RadioButton("Yes");
        RadioButton noFC = new RadioButton("No");
        yesFC.setId("radiobutton");
        noFC.setId("radiobutton");
        
        ToggleGroup radioGroup2 = new ToggleGroup();
        yesFC.setToggleGroup(radioGroup2);
        noFC.setToggleGroup(radioGroup2);
        fatingColors.getChildren().addAll(yesFC, noFC);
        
        Button saveGameSettingsButton = new Button("Save changes!");
        saveGameSettingsButton.setId("button");
        
        Button backGameSettingsButton = new Button("Back");
        backGameSettingsButton.setId("button");
        
        Label savesGameSettingLabel = new Label("");
        savesGameSettingLabel.setId("label");
        
        saveGameSettingsButton.setOnAction( e-> {
            String[] text = nextGameInfoLabel.getText().split(" ");
            if(option1.isSelected()) {
                text[0] = "arcade";
            } else if (option2.isSelected()) {
                text[0] = "slow";
            } else if (option3.isSelected()) {
                text[0] = "medium";
            } else if (option4.isSelected()) {
                text[0] = "fast";
            }
            if (yesFC.isSelected()) {
                text[2] = "fading";
            } else if (noFC.isSelected()) {
                text[2] = "same";
            }
            nextGameInfoLabel.setText(text[0] + " and " + text[2] + " colors");
            savesGameSettingLabel.setText("Saved!");
        }); 
        
        backGameSettingsButton.setOnAction(e-> {
            option1.setSelected(false);
            option2.setSelected(false);
            option3.setSelected(false);
            option4.setSelected(false);
            yesFC.setSelected(false);
            noFC.setSelected(false);
            savesGameSettingLabel.setText("");
            stage.setScene(personalScene);   
        });
        
        VBox gameSettingsPane = new VBox();
        gameSettingsPane.getChildren().addAll(settingInfoLabel, speedLabel, speeds, 
                fadingColorsLabel, fatingColors, saveGameSettingsButton, 
                backGameSettingsButton, savesGameSettingLabel);
        gameSettingsPane.setId("basic");
        
        gameSettingsScene = new Scene(gameSettingsPane, 385, 770); 
        gameSettingsScene.getStylesheets().add("file:style.css");
        
        
        stage.setScene(signInOrSignUpScene);
        
        
        stage.setTitle("Tetris!");
        stage.show();

    }
}
 

