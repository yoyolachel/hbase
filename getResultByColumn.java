import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

public class getResultByColumn {
	public static void main(String[] args) throws IOException {
		Configuration configuration = HBaseConfiguration.create();
		Connection connection = ConnectionFactory
				.createConnection(configuration);
		Table table = connection.getTable(TableName.valueOf("blog"));
		Get get = new Get(Bytes.toBytes("row1"));
		// 获取指定列簇和列修饰符对应的列
		get.addColumn(Bytes.toBytes("article"), Bytes.toBytes("math"));
		Result result = table.get(get);
		for (Cell kv : result.rawCells()) {
			System.out.println("family:" + new String(CellUtil.cloneFamily(kv)));
			System.out.println("qualifier:" + new String(CellUtil.cloneQualifier(kv)));
			System.out.println("value:" + new String(CellUtil.cloneValue(kv)));
			System.out.println("Timestamp:" + kv.getTimestamp());
			System.out.println("------------------------------------------");
			/* System.out.println("value:"+new String(CellUtil.cloneValue(kv))); */
		}
		table.close();
	}
}