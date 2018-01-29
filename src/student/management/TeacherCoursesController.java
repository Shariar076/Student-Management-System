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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.jruby.RubyThread$s$0$0$main;

/**
 * FXML Controller class
 *
 * @author shariar076
 */
public class TeacherCoursesController implements Initializable {

    
    StudentManagement main;
    @FXML
    private TableView<Course> techersTable;
    @FXML
    private Button addCourse;
    @FXML
    private Button logOut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setUp();
    }

    void setUp(){
        Course cs= new Course();
        TableColumn <Course,String> crsId= new TableColumn("ID");
        crsId.setMinWidth(100);
        crsId.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        
        TableColumn<Course,String> crsName= new TableColumn<>("Name");
        crsName.setMinWidth(200);
        crsName.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        
        TableColumn<Course,String> crsTeacher= new TableColumn<>("Teacher");
        crsTeacher.setMinWidth(100);
        crsTeacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        
        
        try {
            techersTable.setItems(cs.getCourses());
        } catch (IOException ex) {
            System.out.println("student.management.TeacherCoursesController.setUp()");
        }
        techersTable.getColumns().addAll(crsId,crsName,crsTeacher);
        
        
        
    }

    void setMain(StudentManagement aThis) {
        main=aThis;
    }

    @FXML
    private void viewCourse(MouseEvent event) {
        
        main.currentCourse=techersTable.getSelectionModel().getSelectedItem().courseID;
        System.out.println(main.currentCourse);
        main.setCourseDetailSceneHead();
        
    }

    @FXML
    private void addCourseAction(ActionEvent event) {
        main.currentCourse=null;
        main.setCourseDetailSceneHead();
    }

   
    @FXML
    private void deleteCourseAction(MouseEvent event) {
    }

    @FXML
    private void logOutAction(ActionEvent event) {
        main.setLogInScene2();
    }

    
    
}
