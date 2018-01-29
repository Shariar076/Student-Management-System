/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.management;

import com.sun.javafx.collections.ElementObservableListDecorator;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

/**
 *
 * @author shariar076
 */
public class Course {
    String courseID;
    String CourseName;
    String teacher;
    

    public Course() {
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return CourseName;
    }

    public String getTeacher() {
        return teacher;
    }

    public Course(String id, String name, String thc) {
           this.courseID=id;
           this.CourseName=name;
           this.teacher=thc;
    }
    
    ObservableList<Course> getCourses() throws IOException{
        ObservableList<Course> ol= FXCollections.observableArrayList();

        
        
      Scan scan = new Scan();
      scan.addColumn(Bytes.toBytes("Course_ID"), Bytes.toBytes("col1"));
      scan.addColumn(Bytes.toBytes("Course_Name"), Bytes.toBytes("col2"));
      scan.addColumn(Bytes.toBytes("Instructor"), Bytes.toBytes("col5"));
      Configuration conf = HBaseConfiguration.create();
      HTable table=new HTable(conf,"Courses");
      ResultScanner scanner = table.getScanner(scan);
      int i=0;
      for (Result result = scanner.next(); result != null; result = scanner.next()){
            byte[] value1=result.getValue(Bytes.toBytes("Course_ID"), Bytes.toBytes("col1"));
            byte[] value2=result.getValue(Bytes.toBytes("Course_Name"), Bytes.toBytes("col2"));
            byte[] value3=result.getValue(Bytes.toBytes("Instructor"), Bytes.toBytes("col5"));
            ol.add(new Course(Bytes.toString(value1),Bytes.toString(value2),Bytes.toString(value3)));

      }
       scanner.close();
       return ol;
        
        
        
    }
   
}
