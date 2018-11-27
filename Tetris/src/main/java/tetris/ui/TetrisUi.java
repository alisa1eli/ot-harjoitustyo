/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.ui;

import java.sql.SQLException;
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

/**
 *
 * @author alisaelizarova
 */
public class TetrisUi extends Application{
    
    private TetrisService tetrisService;
    private Scene signInOrSignUpScene;
    private Scene signInScene;
    private Scene signUpScene;
    private Scene personalScene;
    private User signedIn;
    
    @Override
    public void init() throws ClassNotFoundException {
        Database db = new Database("jdbc:sqlite:tetris.db");
        db.init();
        
        GameDao gameDao = new GameDao(db);
        UserDao userDao = new UserDao(db);
  
        this.tetrisService = new TetrisService(gameDao, userDao);
        this.signedIn = null;
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        // signInOrSignUpScene
        
        VBox loginPane = new VBox(10);
        loginPane.setPadding(new Insets(20));
        loginPane.setSpacing(30);
        loginPane.setAlignment(Pos.CENTER);
        Label signInOrSignUpSceneMessage = new Label();

        
        Button signInButton = new Button("Sign in");
        Button signUpButton = new Button("Sign up");
               
        signUpButton.setOnAction(e->{
            stage.setScene(signUpScene);   
        }); 
        
        signInButton.setOnAction(e->{
            stage.setScene(signInScene);   
        }); 
        
        loginPane.getChildren().addAll(signInButton, signUpButton, signInOrSignUpSceneMessage);       
        
        signInOrSignUpScene = new Scene(loginPane, 300, 250);    

        
        stage.setScene(signInOrSignUpScene);
        
        // signUpScene
        
        VBox newUserPane = new VBox(10);
        
        HBox newUsernamePane = new HBox(10);
        newUsernamePane.setPadding(new Insets(10));
        TextField newLoginInput = new TextField(); 
        Label newUsernameLabel = new Label("Login");
        newUsernameLabel.setPrefWidth(100);
        newUsernamePane.getChildren().addAll(newUsernameLabel, newLoginInput);
     
        HBox newNamePane = new HBox(10);
        newNamePane.setPadding(new Insets(10));
        TextField newNameInput = new TextField();
        Label newNameLabel = new Label("Name");
        newNameLabel.setPrefWidth(100);
        newNamePane.getChildren().addAll(newNameLabel, newNameInput); 
        
        Button backSignUpButton = new Button("Back");
        backSignUpButton.setPadding(new Insets(10));
        backSignUpButton.setOnAction(e->{
            stage.setScene(signInOrSignUpScene);   
        });
        
        Label userCreationMessage = new Label();
        
        Button signUpSceneButton = new Button("Sign up!");
        signUpSceneButton.setPadding(new Insets(10));

        signUpSceneButton.setOnAction(e->{
            String login = newLoginInput.getText();
            String name = newNameInput.getText();
   
            if ( login.length() <= 3 || name.length() <= 3 ) {
                userCreationMessage.setText("Your login or name is too short!");
                userCreationMessage.setTextFill(Color.RED);              
            } else try {
                if (this.tetrisService.createUser(login, name)){
                    userCreationMessage.setText("");
                    signInOrSignUpSceneMessage.setText("New user successfully created!");
                    signInOrSignUpSceneMessage.setTextFill(Color.GREEN);
                    stage.setScene(signInOrSignUpScene);
                }
                else {
                    userCreationMessage.setText("This login is already taken!");        
                    userCreationMessage.setTextFill(Color.RED);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TetrisUi.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        });  
        
        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, 
                newNamePane, signUpSceneButton,backSignUpButton); 
       
        signUpScene = new Scene(newUserPane, 300, 250);
        
        // signInScene
        
        VBox signInPane = new VBox(10);
        signInPane.setPadding(new Insets(10));
        TextField loginSignIn = new TextField();
        Label labelSignIn = new Label("Your login: ");
        labelSignIn.setPrefWidth(100);
        Button signInSceneButton = new Button("Sign In!");
        signInSceneButton.setPadding(new Insets(10));
        Button backSignInButton = new Button("Back");
        backSignInButton.setPadding(new Insets(10));
        Label signInSceneMessage = new Label();
        
        backSignInButton.setOnAction(e->{
            stage.setScene(signInOrSignUpScene);   
        });
        
        Label welcomePersonalSceneLabel = new Label("Oops, you are not signed in!");
        
        signInSceneButton.setOnAction(e->{
            String login = loginSignIn.getText();
   
            try {
                if ( this.tetrisService.userSignIn(login) ) {
                    this.signedIn = this.tetrisService.getSignedInUser();
                    welcomePersonalSceneLabel.setText("Welcome "+ this.signedIn.getName() +"!");
                    stage.setScene(personalScene);
                } else {
                    signInSceneMessage.setText("Wrong login!");
                    signInSceneMessage.setTextFill(Color.RED);
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(TetrisUi.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        });  
        
        signInPane.getChildren().addAll(signInSceneMessage, labelSignIn, loginSignIn, 
                signInSceneButton, backSignInButton); 
        
        signInScene = new Scene(signInPane, 300, 250);
        
        // personalScene
        
        VBox personalScenePane = new VBox(10);
        personalScenePane.setPadding(new Insets(10));

        Button newGameButton = new Button("New game!");
        newGameButton.setPadding(new Insets(10));
        Label oldGamesLabel = new Label("Your best games: ");
//        
//        backSignInButton.setOnAction(e->{
//            stage.setScene(signInOrSignUpScene);   
//        });
//        
//        signInSceneButton.setOnAction(e->{
//            String login = loginSignIn.getText();
//   
//            try {
//                if ( this.tetrisService.userSignIn(login) ) {
//                    stage.setScene(personalScene);
//                } else {
//                    signInSceneMessage.setText("Wrong login!");
//                    signInSceneMessage.setTextFill(Color.RED);
//                    
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(TetrisUi.class.getName()).log(Level.SEVERE, null, ex);
//            }
// 
//        });  
//        
        personalScenePane.getChildren().addAll(welcomePersonalSceneLabel,  
                newGameButton, oldGamesLabel); 
        
        personalScene = new Scene(personalScenePane, 300, 250);
        
        
        stage.setTitle("Tetris!");
        stage.show();

    }
    
}
