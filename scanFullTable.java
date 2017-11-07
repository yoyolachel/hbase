import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

public class scanFullTable  {
     public static void main(String[] args) throws IOException  {
		Scan scan=new Scan();
		ResultScanner rScanner=null;
		Configuration configuration=HBaseConfiguration.create();
		Connection connection=ConnectionFactory.createConnection(configuration);
        Table table =connection.getTable(TableName.valueOf("blog"));
        try {
			rScanner=table.getScanner(scan);
			for (Result r:rScanner) {
				for (Cell kv:r.rawCells()) {
					System.out.println("family."+new String(CellUtil.cloneFamily(kv)));
					System.out.println("qualifer"+new String(CellUtil.cloneQualifier(kv)));
					System.out.println("value"+new String(CellUtil.cloneValue(kv)));
					System.out.println("TimeStamp"+kv.getTimestamp());
					System.out.println("......................");
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			rScanner.close();
			table.close();
		}
}
	
}
