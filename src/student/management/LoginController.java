/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.management;

import java.awt.Button;
import java.awt.Component;
import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author shariar076
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    StudentManagement main;
    
    @FXML
    private javafx.scene.control.Button login;
    @FXML
    private javafx.scene.control.TextField userNameField;
    @FXML
    private PasswordField passField;
    @FXML
    private javafx.scene.control.Button signUp;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void popUsernameError() {
        Component frame = null;
        JOptionPane.showMessageDialog(frame, "Username doesn't exist.");
    }
    public void popPasswordError() {
        Component frame = null;
        JOptionPane.showMessageDialog(frame, "Wrong Password! !!!");
    }
    public boolean checkPassword(String usrname, String pass) throws IOException{
        Usernames us= new Usernames();
        String dbPass=us.getPassword(usrname);
        if(dbPass==null){
            popUsernameError();
            return false;
        }
        else if(!dbPass.equals(pass)){
            popPasswordError();
            return false;
        }
        else return true;
    }
    
    public String checkTeacher(String usrname) throws IOException{
        Usernames us= new Usernames();
        String dbPass=us.isTeacher(usrname);
        return dbPass;
    }

    @FXML
    private void loginAction(ActionEvent event) throws IOException {
        String usrname=userNameField.getText();
        String pass=passField.getText();
        if(checkPassword(usrname, pass)){
        main.currentUsr=userNameField.getText();
        String ans=checkTeacher(usrname);

        if(ans.equals("1"))main.setTeachProfileScene();
        else if(ans.equals("0")) main.setScene2();
        else if(ans.equals("2")) main.setCourseControlScene();
        }
    }
    

    void setMain(StudentManagement scene) {
        main = scene;
    }

    @FXML
    private void signUpAction(ActionEvent event) {
        main.setScene3();
    }

 
    
    
}
