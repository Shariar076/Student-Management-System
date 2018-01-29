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
public class Teachers {
    Configuration conf = HBaseConfiguration.create();
    HTable table;
    public Teachers() throws IOException {
        this.table = new HTable(conf, "Teachers");
    }
    public void cteateTable()throws MasterNotRunningException, IOException{
        HBaseAdmin admin = new HBaseAdmin(conf);
        HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf("Teachers"));
        tableDescriptor.addFamily(new HColumnDescriptor("Teacher_ID"));
        tableDescriptor.addFamily(new HColumnDescriptor("Name"));
        tableDescriptor.addFamily(new HColumnDescriptor("Dept_ID"));
        admin.createTable(tableDescriptor);
        System.out.println(" Table created ");
   
   }
        public void insert(String id, String name, String dp) throws IOException {
        String rowkey=id;
        Put p = new Put(Bytes.toBytes(rowkey));
        System.out.println(rowkey);
        p.add(Bytes.toBytes("Name"), Bytes.toBytes("col1"), Bytes.toBytes(name));
        p.add(Bytes.toBytes("Dept_ID"), Bytes.toBytes("col2"), Bytes.toBytes(dp));
        table.put(p);	
    }
        public String getName(String rowkey){
        try {
            Configuration config = HBaseConfiguration.create();
            HTable table = new HTable(config, "Teachers");
            Get g = new Get(Bytes.toBytes(rowkey));
            Result r = table.get(g);
            byte[] value2 = r.getValue(Bytes.toBytes("Name"), Bytes.toBytes("col1"));
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
            HTable table = new HTable(config, "Teachers");
            Get g = new Get(Bytes.toBytes(rowkey));
            Result r = table.get(g);
            byte[] value2 = r.getValue(Bytes.toBytes("Dept_ID"), Bytes.toBytes("col2"));
            String name = Bytes.toString(value2);
            System.out.println(name);
            return name;
        } catch (IOException ex) {
            System.out.println("student.management.Teachers.getDept()");
        }
        return null;
       
    }
    
    public void showRow(String rowkey) throws IOException {
        Configuration config = HBaseConfiguration.create();
        HTable table = new HTable(config, "Teachers");
        Get g = new Get(Bytes.toBytes(rowkey));
        Result r = table.get(g);

        byte[] value1 = r.getValue(Bytes.toBytes("Name"), Bytes.toBytes("col1"));
        byte[] value2 = r.getValue(Bytes.toBytes("Dept_ID"), Bytes.toBytes("col2"));
        String name = Bytes.toString(value1);
        String dept = Bytes.toString(value2);
        System.out.println("GET: " + "Name: " + name + " Dept_ID: " + dept);

    }
    
    public void dropTable() throws ZooKeeperConnectionException, IOException{
        Configuration conf = HBaseConfiguration.create();

        HBaseAdmin admin = new HBaseAdmin(conf);
        if(admin.tableExists("Teachers")){
            admin.disableTable("Teachers");
            admin.deleteTable("Teachers");
            System.out.println("Table deleted");
        }
   }
    
}
