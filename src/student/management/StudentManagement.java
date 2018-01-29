/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.management;

/**
 *
 * @author shariar076
 */
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;

public class StudentManagement extends Application{
        public static int serial=0;
    public static String currentUsr=null;
    public static String currentCourse=null;
    Stage mainStage;
    public Scene scene0,scene1, scene2, scene3,scene4,scene5,scene6,scene7,scene8;

    public static String loginfile = "login.fxml";
    public static String profilefile = "profile.fxml";
    public static String registrationfile = "registrationForm.fxml";
    public static String courselistfile = "coursedetailStudent.fxml";
    public static String profile_teachfile = "profile_teach.fxml";
    public static String courseTeacher = "coursedetailTeacher.fxml";
    public static String techerCrsfile = "teacherCourses.fxml";
    public static String courseDetHeadfile = "courseDetailHead.fxml";



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        launch(args);
    }

   @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(loginfile));
            Parent root = loader.load();
            LoginController loginScreen = loader.getController();
            loginScreen.setMain(this);
            scene1 = new Scene(root);
            mainStage = primaryStage;
            mainStage.setScene(scene1);
            mainStage.show();
        } catch (IOException ex) {
            System.out.println("StudentManagement.Login.start()");
        }
    }


    public void setScene2() {
        try {
            System.out.println(currentUsr);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(profilefile));
            Parent root = loader.load();
            ProfileController prfScreen = loader.getController();
            prfScreen.setMain(this);
            scene2 = new Scene(root);
            mainStage.setScene(scene2);
            mainStage.show();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    
    public void setCourseControlScene() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(techerCrsfile));
            Parent root = loader.load();
            TeacherCoursesController tchCrsScreen = loader.getController();
            tchCrsScreen.setMain(this);
            scene7 = new Scene(root);
            mainStage.setScene(scene7);
            mainStage.show();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    
    public void setCourseDetailSceneHead() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(courseDetHeadfile));
            Parent root = loader.load();
            CourseDetailHeadController hdScreen = loader.getController();
            hdScreen.setMain(this);
            scene8 = new Scene(root);
            mainStage.setScene(scene8);
            mainStage.show();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    public void setLogInScene2() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(loginfile));
            Parent root = loader.load();
            LoginController logScreen = loader.getController();
            logScreen.setMain(this);
            scene0 = new Scene(root);
            mainStage.setScene(scene0);
            mainStage.show();
        } catch (IOException ex) {
            System.out.println("student.management.StudentManagement.setLogInScene2()");
        }
    }
    
    public void setScene3() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(registrationfile));
            Parent root = loader.load();
            RegistrationFormController regScreen = loader.getController();
            regScreen.setMain(this);
            scene3 = new Scene(root);
            mainStage.setScene(scene3);
            mainStage.show();
        } catch (IOException ex) {
            System.out.println("student.management.StudentManagement.setScene3()");
        }
    }
    public void setCourseDetailScene() {
        try {
            System.out.println(currentCourse);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(courselistfile));
            Parent root = loader.load();
            CoursedetailStudentController crsScreen = loader.getController();
            crsScreen.setMain(this);
            scene4 = new Scene(root);
            mainStage.setScene(scene4);
            mainStage.show();
        } catch (IOException ex) {
            System.out.println("student.management.StudentManagement.setCourseDetailScene()");
        }
    }
    public void setTeachProfileScene() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(profile_teachfile));
            Parent root = loader.load();
            Profile_teachController tchScreen = loader.getController();
            tchScreen.setMain(this);
            scene5 = new Scene(root);
            mainStage.setScene(scene5);
            mainStage.show();
        } catch (IOException ex) {
            System.out.println("student.management.StudentManagement.setTeachProfileScene()");
        }
    }
    public void setTeachCourseScene() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(courseTeacher));
            Parent root = loader.load();
            CoursedetailTeacherController tchScreen = loader.getController();
            tchScreen.setMain(this);
            scene6 = new Scene(root);
            mainStage.setScene(scene6);
            mainStage.show();
        } catch (IOException ex) {
            System.out.println("student.management.StudentManagement.setTeachProfileScene()");
        }
    }
        
}