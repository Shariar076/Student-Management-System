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
public class Usernames {
     Configuration conf = HBaseConfiguration.create();
        HTable table;

   public Usernames() throws IOException {
        this.table = new HTable(conf, "Usernames");
    }
    public void createTable() throws MasterNotRunningException, IOException {
        
        HBaseAdmin admin = new HBaseAdmin(conf);
        HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf("Usernames"));
        tableDescriptor.addFamily(new HColumnDescriptor("Password"));
        tableDescriptor.addFamily(new HColumnDescriptor("isTeacher"));
        admin.createTable(tableDescriptor);
        System.out.println(" Table created ");

    }

    public void insert(String usrName, String passwrd,String bool) throws IOException {
        String rowkey=usrName;
        Put p = new Put(Bytes.toBytes(rowkey));

        p.add(Bytes.toBytes("Password"), Bytes.toBytes("col1"), Bytes.toBytes(passwrd));
        p.add(Bytes.toBytes("isTeacher"), Bytes.toBytes("col2"), Bytes.toBytes(bool));
    
        table.put(p);	
    }

    public String getPassword(String rowkey) throws IOException {
        Configuration config = HBaseConfiguration.create();
        HTable table = new HTable(config, "Usernames");
        Get g = new Get(Bytes.toBytes(rowkey));
        Result r = table.get(g);

        byte[] value1 = r.getValue(Bytes.toBytes("Password"), Bytes.toBytes("col1"));

        String password = Bytes.toString(value1);
        return password;
    }
    public String isTeacher(String rowkey) throws IOException {
        Configuration config = HBaseConfiguration.create();
        HTable table = new HTable(config, "Usernames");
        Get g = new Get(Bytes.toBytes(rowkey));
        Result r = table.get(g);

        byte[] value1 = r.getValue(Bytes.toBytes("isTeacher"), Bytes.toBytes("col2"));

        String bool = Bytes.toString(value1);
        return bool;
    }


    public void dropTable() throws ZooKeeperConnectionException, IOException {
        Configuration conf = HBaseConfiguration.create();

        HBaseAdmin admin = new HBaseAdmin(conf);
        if (admin.tableExists("Usernames")) {
            admin.disableTable("Usernames");
            admin.deleteTable("Usernames");
            System.out.println("Table deleted");
        }
    }
    
}
