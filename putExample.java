import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;


public class putExample {
public static void main(String[] args)throws IOException{
	Configuration configuraion=HBaseConfiguration.create();
	configuraion.set("hbase.zookeeper.quorum", "master");
	configuraion.set("hbase.zookeeper.property.clientPort", "2181");
	Connection connection = ConnectionFactory.createConnection(configuraion);
        Put put=new Put(Bytes.toBytes("row1")); //设置rowKey
        Table table=connection.getTable(TableName.valueOf("blog")); //获取表
        HColumnDescriptor[] columnFamilies=table.getTableDescriptor().getColumnFamilies();//获取所有的列簇
        for(int i=0;i<columnFamilies.length;i++){
            String familyName=columnFamilies[i].getNameAsString();//获取列簇名
            if(familyName.equals("article")){ //article列簇put数据
                for(int j=0;j<10;j++){
                    put.addColumn(Bytes.toBytes(familyName),
                               Bytes.toBytes("math"),
                               Bytes.toBytes("1874")
                            );                        
                  }
              }
                 
                  }
        table.put(put);
        table.close();
        System.out.println("add data Sucess!");
         
}
}