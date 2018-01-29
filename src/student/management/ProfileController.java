/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.management;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javax.rmi.CORBA.Stub;

/**
 * FXML Controller class
 *
 * @author shariar076
 */
public class ProfileController implements Initializable {

    /**
     * Initializes the controller class.
     */
    StudentManagement main;
    Students st;
    @FXML
    private Label nameField;
    @FXML
    private Label deptField;
    @FXML
    private Label lvlField;
    @FXML
    private Label termField;
    @FXML
    private Label advisorField;
    @FXML
    private ListView<String> courselist;
    @FXML
    private Button logoutButton;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        setUp();
        
    }    
    public void setUp() {
        Students st = null;
        try {
            st = new Students();
            Courses cs=new Courses();
            String [] list = cs.get(st.getLevel(main.currentUsr), st.getTerm(main.currentUsr));
            courselist.getItems().addAll(list);
            courselist.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        } catch (IOException ex) {
            System.out.println("student.management.ProfileController.setUp()");
        }
        nameField.setText(st.getName(main.currentUsr));
        deptField.setText(st.getDept(main.currentUsr));
        lvlField.setText(st.getLevel(main.currentUsr));
        termField.setText(st.getTerm(main.currentUsr));
        advisorField.setText(st.getAdvisor(main.currentUsr));
    }
    void setMain(StudentManagement scene) {
        main=scene;
    }

    @FXML
    private void viewCourseList(ActionEvent event) {
        
        try {
            String crsID= courselist.getSelectionModel().getSelectedItem();
            Courses cs=new Courses();
            if(cs.getName(crsID)!=null){
                main.currentCourse=crsID;
                System.err.println(main.currentCourse);
                main.setCourseDetailScene();
                
                
            }
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

    @FXML
    private void logOutAction(ActionEvent event) {
        main.setLogInScene2();
    }
}
    

