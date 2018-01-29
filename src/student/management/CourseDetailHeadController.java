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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author shariar076
 */
public class CourseDetailHeadController implements Initializable {
    
    StudentManagement main;
    @FXML
    private TextField crsNameField;
    @FXML
    private TextField idField;
    @FXML
    private TextField instFied;
    @FXML
    private Button submit;
    @FXML
    private TextField lvlField;
    @FXML
    private TextField termField;
    @FXML
    private Button goBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setUP();
        } catch (IOException ex) {
            Logger.getLogger(CourseDetailHeadController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void setUP() throws IOException {
        System.out.println(main.currentCourse);
        if (main.currentCourse != null) {
            Courses cs = new Courses();
            String rowkey = main.currentCourse;
            crsNameField.setText(cs.getName(main.currentCourse));
            idField.setText(main.currentCourse);
            lvlField.setText(cs.getLevel(rowkey));
            termField.setText(cs.getTerm(rowkey));
            instFied.setText(cs.getInstructor(rowkey));
        }
    }
    
    void setMain(StudentManagement aThis) {
        main = aThis;
    }
    
    @FXML
    private void submitAction(ActionEvent event) throws IOException {
        Courses cs = new Courses();
        
        String rowkey = idField.getText();
        String name = crsNameField.getText();
        String lvl = lvlField.getText();
        String trm = termField.getText();
        String adv = instFied.getText();
        if (rowkey != null) {
            cs.insert(rowkey, name, lvl, trm, adv);
        }
        
    }
    
    /*private void deleteCourseAction(ActionEvent event) throws IOException {
        Courses cs = new Courses();
        String rowkey = idField.getText();
        if (cs.getName(rowkey) != null) {
            cs.deleteName(rowkey);
            cs.deleteInstructor(rowkey);
            cs.deleteLevel(rowkey);
            cs.deleteTerm(rowkey);
        }
    }*/

    @FXML
    private void goBackAction(ActionEvent event) {
        main.setCourseControlScene();
    }
}
