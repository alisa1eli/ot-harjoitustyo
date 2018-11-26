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

/**
 *
 * @author alisaelizarova
 */
public class TetrisUi extends Application{
    
    private TetrisService tetrisService;
    private Scene signInOrSignUpScene;
    private Scene signInScene;
    private Scene signUpScene;
    
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
        
        VBox loginPane = new VBox(10);
        loginPane.setPadding(new Insets(20));
        loginPane.setSpacing(30);
        loginPane.setAlignment(Pos.CENTER);
        Label signInOrSignUpSceneMessage = new Label();

        
        Button signInButton = new Button("Sign in");
        Button signUpButton = new Button("Sign up");
        
        
        
//        loginButton.setOnAction(e->{
//            String username = usernameInput.getText();
//            menuLabel.setText(username + " logged in...");
//            if ( todoService.login(username) ){
//                loginMessage.setText("");
//                redrawTodolist();
//                primaryStage.setScene(todoScene);  
//                usernameInput.setText("");
//            } else {
//                loginMessage.setText("use does not exist");
//                loginMessage.setTextFill(Color.RED);
//            }      
//        });  
//        
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
        
        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, newNamePane, signUpSceneButton); 
       
        signUpScene = new Scene(newUserPane, 300, 250);
        
        stage.setTitle("Tetris!");
        stage.show();

    }
    
}
