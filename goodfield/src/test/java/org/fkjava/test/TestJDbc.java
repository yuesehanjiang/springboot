package org.fkjava.test;

import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;

public class TestJDbc {
/*	jdbc.user=gdfl
			jdbc.password=GFfl1234
			jdbc.url=jdbc:mysql://rm-wz97231s8840ks0k3po.mysql.rds.aliyuncs.com:3306/dbuser1?characterEncoding=UTF-8&useSSL=true
			jdbc.driver=com.mysql.jdbc.Driver*/


	public static void main(String[] args) throws SQLException {
		 Driver driver=new Driver();
	        Properties info=new Properties();
	        String user="gdfl";
	        String password="GDfl1234";
	        info.setProperty("user", user);
	        info.setProperty("password", password);
	        
	        String url="jdbc:mysql://rm-wz97231s8840ks0k3po.mysql.rds.aliyuncs.com:3306/dbuser1";
	        //调用connect方法 来连接数据库
	        Connection coon=  (Connection) driver.connect(url, info);
	        System.out.println(info);
	        System.out.println(coon);
	        
	                                           
		
	}

}
