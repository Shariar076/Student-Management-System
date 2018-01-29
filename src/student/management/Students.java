/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.management;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 *
 * @author shariar076
 */
public class Students {
    Configuration conf = HBaseConfiguration.create();
    HTable table;
    public Students() throws IOException {
        this.table = new HTable(conf, "Students");
    }
    public void cteateTable()throws MasterNotRunningException, IOException{
        
        HBaseAdmin admin = new HBaseAdmin(conf);
        HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf("Students"));
        tableDescriptor.addFamily(new HColumnDescriptor("Std_ID"));
        tableDescriptor.addFamily(new HColumnDescriptor("Name"));
        tableDescriptor.addFamily(new HColumnDescriptor("Dept_ID"));
        tableDescriptor.addFamily(new HColumnDescriptor("Level"));
        tableDescriptor.addFamily(new HColumnDescriptor("Term"));
        tableDescriptor.addFamily(new HColumnDescriptor("Advisor"));
        admin.createTable(tableDescriptor);
        System.out.println(" Table created ");
   
   }
    public void insert(String id, String name, String dpt,String lvl, String trm, String adv) throws IOException {
       // String rowkey="row" + StudentManagement.serial++;
        String rowkey= id;
        Put p = new Put(Bytes.toBytes(rowkey));
        System.out.println(rowkey);
        p.add(Bytes.toBytes("Std_ID"), Bytes.toBytes("col1"), Bytes.toBytes(id));
        p.add(Bytes.toBytes("Name"), Bytes.toBytes("col2"), Bytes.toBytes(name));
        p.add(Bytes.toBytes("Dept_ID"), Bytes.toBytes("col3"), Bytes.toBytes(dpt));
        p.add(Bytes.toBytes("Level"), Bytes.toBytes("col4"), Bytes.toBytes(lvl));
        p.add(Bytes.toBytes("Term"), Bytes.toBytes("col5"), Bytes.toBytes(trm));
        p.add(Bytes.toBytes("Advisor"), Bytes.toBytes("col6"), Bytes.toBytes(adv));
        table.put(p);	
        System.out.println("data inserted");
    }

    public void showRow(String rowkey) throws IOException {
        Configuration config = HBaseConfiguration.create();
        HTable table = new HTable(config, "Students");
        Get g = new Get(Bytes.toBytes(rowkey));
        Result r = table.get(g);

        byte[] value1 = r.getValue(Bytes.toBytes("Std_ID"), Bytes.toBytes("col1"));
        byte[] value2 = r.getValue(Bytes.toBytes("Name"), Bytes.toBytes("col2"));
        byte[] value3 = r.getValue(Bytes.toBytes("Dept_ID"), Bytes.toBytes("col3"));
        byte[] value4 = r.getValue(Bytes.toBytes("Level"), Bytes.toBytes("col4"));
        byte[] value5 = r.getValue(Bytes.toBytes("Term"), Bytes.toBytes("col5"));
        byte[] value6 = r.getValue(Bytes.toBytes("Advisor"), Bytes.toBytes("col6"));
        String ID = Bytes.toString(value1);
        String name = Bytes.toString(value2);
        String dept = Bytes.toString(value3);
        String lvl = Bytes.toString(value4);
        String term = Bytes.toString(value5);
        String advisor = Bytes.toString(value6);
        System.out.println("GET: " + "Std_ID: " + ID + "Name: " + name + "Dept_ID: " + dept + "Level: " + lvl + "Term: " + term + "Advisor: " + advisor);

    }
    public String getName(String rowkey){
        try {
            Configuration config = HBaseConfiguration.create();
            HTable table = new HTable(config, "Students");
            Get g = new Get(Bytes.toBytes(rowkey));
            Result r = table.get(g);
            byte[] value2 = r.getValue(Bytes.toBytes("Name"), Bytes.toBytes("col2"));
            String name = Bytes.toString(value2);
            System.out.println(name);
            return name;
        } catch (IOException ex) {
            System.out.println("student.management.Students.getName()");
        }
        return null;
       
    }
    public String getDept(String rowkey){
        try {
            Configuration config = HBaseConfiguration.create();
            HTable table = new HTable(config, "Students");
            Get g = new Get(Bytes.toBytes(rowkey));
            Result r = table.get(g);
            byte[] value2 = r.getValue(Bytes.toBytes("Dept_ID"), Bytes.toBytes("col3"));
            String name = Bytes.toString(value2);
            System.out.println(name);
            return name;
        } catch (IOException ex) {
            System.out.println("student.management.Students.getDept()");
        }
        return null;
       
    }
    public String getLevel(String rowkey){
        try {
            Configuration config = HBaseConfiguration.create();
            HTable table = new HTable(config, "Students");
            Get g = new Get(Bytes.toBytes(rowkey));
            Result r = table.get(g);
            byte[] value2 = r.getValue(Bytes.toBytes("Level"), Bytes.toBytes("col4"));
            String name = Bytes.toString(value2);
            System.out.println(name);
            return name;
        } catch (IOException ex) {
            System.out.println("student.management.Students.getLevel()");
        }
        return null;
       
    }
    public String getTerm(String rowkey){
        try {
            Configuration config = HBaseConfiguration.create();
            HTable table = new HTable(config, "Students");
            Get g = new Get(Bytes.toBytes(rowkey));
            Result r = table.get(g);
            byte[] value2 = r.getValue(Bytes.toBytes("Term"), Bytes.toBytes("col5"));
            String name = Bytes.toString(value2);
            System.out.println(name);
            return name;
        } catch (IOException ex) {
            System.out.println("student.management.Students.getTerm()");
        }
        return null;
       
    }
    public String getAdvisor(String rowkey){
        try {
            Configuration config = HBaseConfiguration.create();
            HTable table = new HTable(config, "Students");
            Get g = new Get(Bytes.toBytes(rowkey));
            Result r = table.get(g);
            byte[] value2 = r.getValue(Bytes.toBytes("Advisor"), Bytes.toBytes("col6"));
            String name = Bytes.toString(value2);
            System.out.println(name);
            return name;
        } catch (IOException ex) {
            System.out.println("student.management.Students.getAdvisor()");
        }
        return null;
       
    }
    
    public void dropTable() throws ZooKeeperConnectionException, IOException{
        Configuration conf = HBaseConfiguration.create();

        HBaseAdmin admin = new HBaseAdmin(conf);
        if(admin.tableExists("Students")){
            admin.disableTable("Students");
            admin.deleteTable("Students");
            System.out.println("Table deleted");
        }
   }
}
