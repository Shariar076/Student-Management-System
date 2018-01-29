/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.management;

import java.awt.Component;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;

/**
 * FXML Controller class
 *
 * @author shariar076
 */
public class RegistrationFormController implements Initializable {

    StudentManagement main;
    @FXML
    private TextField nameField;
    @FXML
    private TextField deptField;
    @FXML
    private TextField levelField;
    @FXML
    private TextField termField;
    @FXML
    private TextField advisorField;
    @FXML
    private TextField passField;
    @FXML
    private TextField confPass;
    @FXML
    private Button submit;
    @FXML
    private TextField idfield;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void popError() {
        Component frame = null;
        JOptionPane.showMessageDialog(frame, "Please fill up all the fields.");
    }
    public void popPassError() {
        Component frame = null;
        JOptionPane.showMessageDialog(frame, "Passwords don't match.");
    }
    public void popUsrError() {
        Component frame = null;
        JOptionPane.showMessageDialog(frame, "Username already exists.");
    }
    public void insertData(String id,String Name,String dept,String lvl,String trm,String adv, String pass){

        try {
            Students st = new Students();
            
            Usernames us = new Usernames();
            if(us.getPassword(id)==null){
            st.insert(id, Name, dept , lvl,trm,adv);
            us.insert(id, pass,"0");
            }
            else popUsrError();
        } catch (IOException ex) {
            System.out.println("student.management.RegistrationFormController.insertData()");
        }
   
    }
    

    @FXML
    private void submitAction(ActionEvent event) throws IOException {
        /*   String name;
        String dept;
        String level;
        String term;
        String advisor;
        String pass;
        String conf;*/
        if (nameField.getText().length() != 0 && idfield.getText().length()!=0 && deptField.getText().length() != 0
                && levelField.getText().length() != 0 && termField.getText().length() != 0
                && advisorField.getText().length() != 0 && passField.getText().length() != 0 && confPass.getText().length() != 0) {
            String name = nameField.getText();
            String id=idfield.getText();
            String dept = deptField.getText();
            String level = levelField.getText();
            String term = termField.getText();
            String advisor = advisorField.getText();
            String pass = passField.getText();
            String conf = confPass.getText();
            
            
            if(!pass.toString().equals(conf.toString())){
                popPassError();
                System.out.println(pass + "  " + conf);
            }else{
                insertData(id, name, dept, level, term, advisor,pass);
                System.out.println("Inserting data");
            }
            
            

        } else {
            popError();
        }
    }

    void setMain(StudentManagement aThis) {
        main = aThis;
    }

    @FXML
    private void logInAction(ActionEvent event) {
        main.setLogInScene2();
    }

}
