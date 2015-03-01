package DB;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.sql.*;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MyDB {

	public Connection connect;
	public String url = "jdbc:mysql://devcloud.fulgentcorp.com:3306/soq971";
	public String user = "soq971";
	public String pw = "KzZSQolR5vFF69sboLh4";

	public MyDB() throws SQLException {

		MysqlDataSource src = new MysqlDataSource();
		src.setURL(url);
		src.setUser(user);
		src.setPassword(pw);
		connect = src.getConnection();
		closeDB();
	}

	// Properties p = new Properties();

	// FileInputStream f = null;

	// f = new FileInputStream("DatabaseProperties");
	// p.load(f);

	public void closeDB() throws SQLException {
		this.connect.close();
	}

}
