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
import javafx.scene.control.Label;
import javafx.scene.text.TextFlow;

/**
 * FXML Controller class
 *
 * @author shariar076
 */
public class CoursedetailStudentController implements Initializable {
    
    StudentManagement main;
    @FXML
    private Label courseNameField;
    @FXML
    private Label advisorNameField;
    
    @FXML
    private Label noticeBoard1;
    @FXML
    private Label noticeBoard2;
    @FXML
    private Label noticeBoard3;
    @FXML
    private Label noticeBoard4;
    @FXML
    private Label noticeBoard5;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setUp();
        } catch (IOException ex) {
            Logger.getLogger(CoursedetailStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    void setUp() throws IOException{
        Courses cs= new Courses();
        courseNameField.setText(main.currentCourse+":"+cs.getName(main.currentCourse));
        advisorNameField.setText(cs.getInstructor(main.currentCourse));
        noticeBoard1.setText(cs.getNotice1(main.currentCourse));
        noticeBoard2.setText(cs.getNotice2(main.currentCourse));
        noticeBoard3.setText(cs.getNotice3(main.currentCourse));
        noticeBoard4.setText(cs.getNotice4(main.currentCourse));
        noticeBoard5.setText(cs.getNotice5(main.currentCourse));
        
    }
    void setMain(StudentManagement aThis) {
        main=aThis;
    }

    @FXML
    private void setProfile(ActionEvent event) {
        main.setScene2();
    }
    
}
