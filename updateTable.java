import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

public class updateTable {
	public static void main(String[] args) throws IOException {
		Configuration configuration= HBaseConfiguration.create();
		Connection connection = ConnectionFactory.createConnection(configuration);
		Table table=connection.getTable(TableName.valueOf("blog"));
		Put put =new Put(Bytes.toBytes("row1"));
		put.addColumn(Bytes.toBytes("article"),Bytes.toBytes("math"),Bytes.toBytes("gaodeng"));
		table.put(put);
		table.close();
		System.out.println("Update table success!");
	}
}
