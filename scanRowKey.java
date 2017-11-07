import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;


public class scanRowKey {

	public static void main(String[] args) throws IOException {
		Configuration configuration=HBaseConfiguration.create();
		Connection connection=ConnectionFactory.createConnection(configuration);
	      Get get=new Get(Bytes.toBytes("row1"));
          Table table=connection.getTable(TableName.valueOf("blog")); //获取表
          Result result=table.get(get);
          for(Cell kv:result.rawCells())
          {
              System.out.println("family:"+new String(CellUtil.cloneFamily(kv)));
              System.out.println("qualifier:"+new String(CellUtil.cloneQualifier(kv)));
              System.out.println("value:"+new String(CellUtil.cloneValue(kv)));
              System.out.println("Timestamp:"+kv.getTimestamp());
              System.out.println("------------------------------------------");
          /*    System.out.println("value:"+new String(CellUtil.cloneValue(kv)));*/
          }
          table.close();
         // return result;
	}
}
