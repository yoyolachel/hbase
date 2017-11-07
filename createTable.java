import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.TableName;

public class createTable {
	public static void main(String[] args) throws IOException {
		Configuration configuraion=HBaseConfiguration.create();
		configuraion.set("hbase.zookeeper.quorum", "master");
		configuraion.set("hbase.zookeeper.property.clientPort", "2181");
		Connection connection = ConnectionFactory.createConnection(configuraion);
		Admin admin = connection.getAdmin();
		if (admin.tableExists(TableName.valueOf("blog"))) {
			System.out.println("table is exist");
		} else {
			HTableDescriptor descriptor=new HTableDescriptor(TableName.valueOf("blog"));
			descriptor.addFamily(new HColumnDescriptor("article"));
			descriptor.addFamily(new HColumnDescriptor("author"));
			admin.createTable(descriptor);
			admin.close();
			System.out.println("Whoops!you did it!");
		}

	}
    	
}
