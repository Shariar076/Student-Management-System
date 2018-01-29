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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

/**
 * FXML Controller class
 *
 * @author shariar076
 */
public class Profile_teachController implements Initializable {

    StudentManagement main;
    @FXML
    private Label nameField;
    @FXML
    private Label deptField;
    @FXML
    private ListView<String> courselist;
    @FXML
    private Button logOut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setUp();
    }    

    @FXML
    private void viewCourseList(ActionEvent event) {
        
        try {
            String crsID= courselist.getSelectionModel().getSelectedItem();
            Courses cs=new Courses();
            if(cs.getName(crsID)!=null){
                main.currentCourse=crsID;
                
                main.setTeachCourseScene();
                
                
            }
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    public void setUp() {
        Teachers st = null;
        try {
            st = new Teachers();
            Courses cs=new Courses();
            String [] list = cs.getCourseListTeacher(main.currentUsr);
            courselist.getItems().addAll(list);
            courselist.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        } catch (IOException ex) {
            System.out.println("student.management.ProfileController.setUp()");
        }
        nameField.setText(st.getName(main.currentUsr));
        deptField.setText(st.getDept(main.currentUsr));

    }
    void setMain(StudentManagement aThis) {
        main=aThis;
    }

    @FXML
    private void logOutAction(ActionEvent event) {
        main.setLogInScene2();
    }
    
}
