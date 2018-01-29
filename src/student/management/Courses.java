/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.management;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

/**
 *
 * @author shariar076
 */
public class Courses {
    Configuration conf = HBaseConfiguration.create();
    HTable table;
    public Courses() throws IOException {
        this.table = new HTable(conf, "Courses");
    }
   /* public void createTable()throws MasterNotRunningException, IOException{
        HBaseAdmin admin = new HBaseAdmin(conf);
        HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf("Courses"));
        tableDescriptor.addFamily(new HColumnDescriptor("Course_ID"));
        tableDescriptor.addFamily(new HColumnDescriptor("Course_Name"));
        tableDescriptor.addFamily(new HColumnDescriptor("Level"));
        tableDescriptor.addFamily(new HColumnDescriptor("Term"));
        tableDescriptor.addFamily(new HColumnDescriptor("Instructor"));
        
        admin.createTable(tableDescriptor);
        System.out.println(" Table created ");
   }*/
    public void insert(String id, String name,String lvl,String term,String adv) throws IOException {
        String rowkey=id;
        Put p = new Put(Bytes.toBytes(rowkey));
        p.add(Bytes.toBytes("Course_ID"), Bytes.toBytes("col1"), Bytes.toBytes(id));
        p.add(Bytes.toBytes("Course_Name"), Bytes.toBytes("col2"), Bytes.toBytes(name));
        p.add(Bytes.toBytes("Level"), Bytes.toBytes("col3"), Bytes.toBytes(lvl));
        p.add(Bytes.toBytes("Term"), Bytes.toBytes("col4"), Bytes.toBytes(term));
        p.add(Bytes.toBytes("Instructor"), Bytes.toBytes("col5"), Bytes.toBytes(adv));
        table.put(p);	
    }
    public String[] get(String level,String term) throws IOException{
        Scan scan = new Scan();
      scan.addColumn(Bytes.toBytes("Course_ID"), Bytes.toBytes("col1"));
      scan.addColumn(Bytes.toBytes("Level"), Bytes.toBytes("col3"));
      scan.addColumn(Bytes.toBytes("Term"), Bytes.toBytes("col4"));

      ResultScanner scanner = table.getScanner(scan);

      String[] retVal={"","","","","",""};
       
      int i=0;
      for (Result result = scanner.next(); result != null; result = scanner.next()){
         
            byte[] value1=result.getValue(Bytes.toBytes("Course_ID"), Bytes.toBytes("col1"));
            byte[] value3=result.getValue(Bytes.toBytes("Level"), Bytes.toBytes("col3"));
            byte[] value4=result.getValue(Bytes.toBytes("Term"), Bytes.toBytes("col4"));
            String id = Bytes.toString(value1);
            
            String lvl = Bytes.toString(value3);
            String trm = Bytes.toString(value4);
            if(level.equals(lvl) && trm.equals(term))retVal[i++]=id;
      }
       scanner.close();
       return retVal;
    }
    public String getName(String rowkey) throws IOException{
        Configuration config = HBaseConfiguration.create();
        HTable table = new HTable(config, "Courses");
        Get g = new Get(Bytes.toBytes(rowkey));
        Result r = table.get(g);

        byte[] value1 = r.getValue(Bytes.toBytes("Course_Name"), Bytes.toBytes("col2"));
        
        String name = Bytes.toString(value1);
        
        return name;
    }
    
    public String getLevel(String rowkey) throws IOException{
        //Configuration config = HBaseConfiguration.create();
       // HTable table = new HTable(config, "Courses");
        Get g = new Get(Bytes.toBytes(rowkey));
        Result r = table.get(g);

        byte[] value1 = r.getValue(Bytes.toBytes("Level"), Bytes.toBytes("col3"));
        
        String name = Bytes.toString(value1);
        
        return name;
    }
    
    public String getTerm(String rowkey) throws IOException{
        //Configuration config = HBaseConfiguration.create();
       // HTable table = new HTable(config, "Courses");
        Get g = new Get(Bytes.toBytes(rowkey));
        Result r = table.get(g);

        byte[] value1 = r.getValue(Bytes.toBytes("Term"), Bytes.toBytes("col4"));
        
        String name = Bytes.toString(value1);
        
        return name;
    }
    
    public String getInstructor(String rowkey) throws IOException{
        Configuration config = HBaseConfiguration.create();
        HTable table = new HTable(config, "Courses");
        Get g = new Get(Bytes.toBytes(rowkey));
        Result r = table.get(g);

        byte[] value1 = r.getValue(Bytes.toBytes("Instructor"), Bytes.toBytes("col5"));
        
        String name = Bytes.toString(value1);
        
        return name;
    }
    
    public void setNotice1(String rowkey,String note) throws IOException{
        Put p = new Put(Bytes.toBytes(rowkey));
        p.add(Bytes.toBytes("Notice1"), Bytes.toBytes("col6"), Bytes.toBytes(note));
        table.put(p);
    }
    
    public void setNotice2(String rowkey,String note) throws IOException{
        Put p = new Put(Bytes.toBytes(rowkey));
        p.add(Bytes.toBytes("Notice2"), Bytes.toBytes("col7"), Bytes.toBytes(note));
        table.put(p);
    }
    public void setNotice3(String rowkey,String note) throws IOException{
        Put p = new Put(Bytes.toBytes(rowkey));
        p.add(Bytes.toBytes("Notice3"), Bytes.toBytes("col8"), Bytes.toBytes(note));
        table.put(p);
    }
    public void setNotice4(String rowkey,String note) throws IOException{
        Put p = new Put(Bytes.toBytes(rowkey));
        p.add(Bytes.toBytes("Notice4"), Bytes.toBytes("col9"), Bytes.toBytes(note));
        table.put(p);
    }
    public void setNotice5(String rowkey,String note) throws IOException{
        Put p = new Put(Bytes.toBytes(rowkey));
        p.add(Bytes.toBytes("Notice5"), Bytes.toBytes("col10"), Bytes.toBytes(note));
        table.put(p);
    }
    
    public String getNotice1(String rowkey) throws IOException{
        Configuration config = HBaseConfiguration.create();
        HTable table = new HTable(config, "Courses");
        Get g = new Get(Bytes.toBytes(rowkey));
        Result r = table.get(g);

        byte[] value1 = r.getValue(Bytes.toBytes("Notice1"), Bytes.toBytes("col6"));
        
        String note = Bytes.toString(value1);
        
        return note;
    }
    public String getNotice2(String rowkey) throws IOException{
        Configuration config = HBaseConfiguration.create();
        HTable table = new HTable(config, "Courses");
        Get g = new Get(Bytes.toBytes(rowkey));
        Result r = table.get(g);

        byte[] value1 = r.getValue(Bytes.toBytes("Notice2"), Bytes.toBytes("col7"));
        
        String note = Bytes.toString(value1);
        
        return note;
    }
    public String getNotice3(String rowkey) throws IOException{
        Configuration config = HBaseConfiguration.create();
        HTable table = new HTable(config, "Courses");
        Get g = new Get(Bytes.toBytes(rowkey));
        Result r = table.get(g);

        byte[] value1 = r.getValue(Bytes.toBytes("Notice3"), Bytes.toBytes("col8"));
        
        String note = Bytes.toString(value1);
        
        return note;
    }
    public String getNotice4(String rowkey) throws IOException{
        Configuration config = HBaseConfiguration.create();
        HTable table = new HTable(config, "Courses");
        Get g = new Get(Bytes.toBytes(rowkey));
        Result r = table.get(g);

        byte[] value1 = r.getValue(Bytes.toBytes("Notice4"), Bytes.toBytes("col9"));
        
        String note = Bytes.toString(value1);
        
        return note;
    }
    public String getNotice5(String rowkey) throws IOException{
        Configuration config = HBaseConfiguration.create();
        HTable table = new HTable(config, "Courses");
        Get g = new Get(Bytes.toBytes(rowkey));
        Result r = table.get(g);

        byte[] value1 = r.getValue(Bytes.toBytes("Notice5"), Bytes.toBytes("col10"));
        
        String note = Bytes.toString(value1);
        
        return note;
    }
    
    public String[] getCourseListTeacher(String tchr) throws IOException{
        Scan scan = new Scan();
      scan.addColumn(Bytes.toBytes("Course_ID"), Bytes.toBytes("col1"));
      scan.addColumn(Bytes.toBytes("Instructor"), Bytes.toBytes("col5"));
      ResultScanner scanner = table.getScanner(scan);
      String[] retVal={"","","","","",""};
      int i=0;
      for (Result result = scanner.next(); result != null; result = scanner.next()){
            byte[] value1=result.getValue(Bytes.toBytes("Course_ID"), Bytes.toBytes("col1"));
            byte[] value3=result.getValue(Bytes.toBytes("Instructor"), Bytes.toBytes("col5"));
            String id = Bytes.toString(value1);
            String tch = Bytes.toString(value3);
            if(tch.equals(tchr) )retVal[i++]=id;
      }
       scanner.close();
       return retVal;
    }
        
    public void deleteName(String rowkey) throws IOException{
       Delete delete = new Delete(Bytes.toBytes(rowkey));
      delete.deleteColumn(Bytes.toBytes("Course_Name"), Bytes.toBytes("col2"));

      // deleting the data
      table.delete(delete);
    }
    
    public void deleteLevel(String rowkey) throws IOException{
       Delete delete = new Delete(Bytes.toBytes(rowkey));
      delete.deleteColumn(Bytes.toBytes("Level"), Bytes.toBytes("col3"));

      // deleting the data
      table.delete(delete);
    }
    
    public void deleteTerm(String rowkey) throws IOException{
      Delete delete = new Delete(Bytes.toBytes(rowkey));
      delete.deleteColumn(Bytes.toBytes("Term"), Bytes.toBytes("col4"));

      // deleting the data
      table.delete(delete);
    }
    
    public void deleteInstructor(String rowkey) throws IOException{
      Delete delete = new Delete(Bytes.toBytes(rowkey));
      delete.deleteColumn(Bytes.toBytes("Instructor"), Bytes.toBytes("col5"));

      // deleting the data
      table.delete(delete);
    }
   

    public void showRow(String rowkey) throws IOException {
        Configuration config = HBaseConfiguration.create();
        HTable table = new HTable(config, "Courses");
        Get g = new Get(Bytes.toBytes(rowkey));
        Result r = table.get(g);

        byte[] value1 = r.getValue(Bytes.toBytes("Course_ID"), Bytes.toBytes("col1"));
        byte[] value2 = r.getValue(Bytes.toBytes("Course_Name"), Bytes.toBytes("col2"));
        String id = Bytes.toString(value1);
        String name = Bytes.toString(value2);
        System.out.println("GET: " + "Course_ID: " + id + " Course_NAme: " + name);

    }
    public void dropTable() throws ZooKeeperConnectionException, IOException{
        Configuration conf = HBaseConfiguration.create();

        HBaseAdmin admin = new HBaseAdmin(conf);
        if(admin.tableExists("Courses")){
            admin.disableTable("Courses");
            admin.deleteTable("Courses");
            System.out.println("Table deleted");
        }
   }
}
