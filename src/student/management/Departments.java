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
public class Departments {
        Configuration conf = HBaseConfiguration.create();
        HTable table;

    public Departments() throws IOException {
        this.table = new HTable(conf, "Departments");
    }
    public void cteateTable() throws MasterNotRunningException, IOException {
        
        HBaseAdmin admin = new HBaseAdmin(conf);
        HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf("Departments"));
        tableDescriptor.addFamily(new HColumnDescriptor("Dept_ID"));
        tableDescriptor.addFamily(new HColumnDescriptor("Dept_Name"));
        tableDescriptor.addFamily(new HColumnDescriptor("Dept_Head"));
        admin.createTable(tableDescriptor);
        System.out.println(" Table created ");

    }

    public void insert(String id, String name, String head) throws IOException {
        String rowkey=id;
        Put p = new Put(Bytes.toBytes(rowkey));
        System.out.println(rowkey);
        p.add(Bytes.toBytes("Dept_ID"), Bytes.toBytes("col1"), Bytes.toBytes(id));
        p.add(Bytes.toBytes("Dept_Name"), Bytes.toBytes("col2"), Bytes.toBytes(name));
        p.add(Bytes.toBytes("Dept_Head"), Bytes.toBytes("col3"), Bytes.toBytes(head));
    
        table.put(p);	
    }

    public void showRow(String rowkey) throws IOException {
        Configuration config = HBaseConfiguration.create();
        HTable table = new HTable(config, "Departments");
        Get g = new Get(Bytes.toBytes(rowkey));
        Result r = table.get(g);

        byte[] value1 = r.getValue(Bytes.toBytes("Dept_ID"), Bytes.toBytes("col1"));
        byte[] value2 = r.getValue(Bytes.toBytes("Dept_Name"), Bytes.toBytes("col2"));
        byte[] value3 = r.getValue(Bytes.toBytes("Dept_Head"), Bytes.toBytes("col3"));
        String ID = Bytes.toString(value1);
        String name = Bytes.toString(value2);
        String head = Bytes.toString(value3);
        System.out.println("GET: " + "Dept_ID: " + ID + "name: " + name + "head: " + head);

    }

    public void dropTable() throws ZooKeeperConnectionException, IOException {
        Configuration conf = HBaseConfiguration.create();

        HBaseAdmin admin = new HBaseAdmin(conf);
        if (admin.tableExists("Departments")) {
            admin.disableTable("Departments");
            admin.deleteTable("Departments");
            System.out.println("Table deleted");
        }
    }

}
