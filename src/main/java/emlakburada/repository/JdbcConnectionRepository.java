package emlakburada.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class JdbcConnectionRepository implements DbConnectionRepository {

	 static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://remotemysql.com/NzwlmHkOID";

	private static final String USER = "NzwlmHkOID";
	private static final String PASS = "Q2cqiN1DTB";

	@Override
	public Connection connect() {

		try {
			
			Class.forName(JDBC_DRIVER);
			return DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
