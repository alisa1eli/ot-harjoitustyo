package com.mycompany.javafx;


import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alisaelizarova
 */
public class JavaFx extends Application{
    private Scene signInOrSignUpScene;
    private Scene signInScene;
    private Scene signUpScene;

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
            } else {
                userCreationMessage.setText("");                
                signInOrSignUpSceneMessage.setText("New user successfully created!");
                signInOrSignUpSceneMessage.setTextFill(Color.GREEN);
                stage.setScene(signInOrSignUpScene);      
            } 
//            else {
//                userCreationMessage.setText("username has to be unique");
//                userCreationMessage.setTextFill(Color.RED);        
//            }
 
        });  
        
        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, newNamePane, signUpSceneButton); 
       
        signUpScene = new Scene(newUserPane, 300, 250);
        
        stage.setTitle("Tetris!");
        stage.show();

    }
    
}
