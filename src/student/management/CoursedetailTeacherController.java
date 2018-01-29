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
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author shariar076
 */
public class CoursedetailTeacherController implements Initializable {

    StudentManagement main;
    @FXML
    private Label courseNameField;
    @FXML
    private Label advisorNameField;

    @FXML
    private Button submit;
    @FXML
    private TextArea noticeBoard1;
    @FXML
    private TextArea noticeBoard2;
    @FXML
    private TextArea noticeBoard3;
    @FXML
    private TextArea noticeBoard4;
    @FXML
    private TextArea noticeBoard5;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setUp();
        } catch (IOException ex) {
            Logger.getLogger(CoursedetailTeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    void setMain(StudentManagement aThis) {
        main=aThis;
    }
    void setUp() throws IOException{
        Courses cs= new Courses();
        courseNameField.setText(main.currentCourse+":"+cs.getName(main.currentCourse));
        advisorNameField.setText(cs.getInstructor(main.currentCourse));
        
    }

    @FXML
    private void submitAction(ActionEvent event) throws IOException {
        String note1=noticeBoard1.getText();
        String note2=noticeBoard2.getText();
        String note3=noticeBoard3.getText();
        String note4=noticeBoard4.getText();
        String note5=noticeBoard5.getText();
        Courses cs= new Courses();
        cs.setNotice1(main.currentCourse, note1);
        cs.setNotice2(main.currentCourse, note2);
        cs.setNotice3(main.currentCourse, note3);
        cs.setNotice4(main.currentCourse, note4);
        cs.setNotice5(main.currentCourse, note5);
//        System.err.println(cs.getNotice1(main.currentCourse));
    }

    @FXML
    private void viewTeachProfile(ActionEvent event) {
        main.setTeachProfileScene();
    }
}
