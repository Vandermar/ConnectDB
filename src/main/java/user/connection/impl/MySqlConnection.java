package user.connection.impl;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import user.connection.ConnectionFactory;

public class MySqlConnection implements ConnectionFactory {

    private DataSource dataSource;


    public Connection openConnection() {

	Connection conn=null;

	try {
	    conn = dataSource.getConnection();
	} 
	catch (SQLException e) {

	    throw new RuntimeException(e);
	}


	return conn;
    }

    public void setDataSource(DataSource dataSource) {
	this.dataSource = dataSource;
    }
}
