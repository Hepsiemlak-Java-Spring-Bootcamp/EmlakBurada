package emlakburada.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import emlakburada.model.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserRepository extends JdbcConnectionRepository {

	private static final String INSERT_USER = "INSERT INTO User (ID, NAME, EMAIL, BIO) VALUES (?,?,?,?);";
//
//	public void save(User user) {
//
//		Connection connection = connect();
//
//		PreparedStatement prepareStatement;
//		try {
//
//			prepareStatement = connection.prepareStatement(INSERT_USER);
//			prepareStatement.setInt(1, user.getId());
//			prepareStatement.setString(2, user.getName());
//			prepareStatement.setString(3, user.getEmail());
//			prepareStatement.setString(4, user.getBio());
//
//			int executeUpdate = prepareStatement.executeUpdate();
//
//			log.info("Inserted record into the table...");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void save(User user) {	
		jdbcTemplate.update(INSERT_USER, user.getId(),user.getName(),user.getEmail(),user.getBio());
	}

	public void findAll() {

	}

	public void findOne(int id) {

	}

}
