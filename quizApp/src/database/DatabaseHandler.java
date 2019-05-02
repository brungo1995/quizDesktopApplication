package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Properties;


import org.apache.derby.impl.sql.catalog.SYSROUTINEPERMSRowFactory;

import quizApp.model.User;


public class DatabaseHandler {
	private Connection connect = null;
	private PreparedStatement statement = null;
	private static DatabaseHandler handler = null;
	private Properties props;
	
	private DatabaseHandler(){
		settings();
		if(createConnection()){
			createTables();			
		}
	}
	
	public static DatabaseHandler getInstance(){
		if(handler == null){
			handler = new DatabaseHandler();
		}
		return handler;
	}

	private void settings() {
		props = new Properties();
		try(FileInputStream file = new FileInputStream("configdb.properties")) {		
			props.load(file);
		
		} catch (Exception e) {

		}		
	}

	private boolean createConnection() {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			connect = DriverManager.getConnection("jdbc:derby:quizDB;create=true");
			System.out.println("database created");
			return true;
		}
		catch (Exception e) {
			return false;
		}
		
	}
	
	

	/**
	 * Create all the tables in needed to run the application
	 */
	private void createTables() {
//		createTableUsersTable();
//		createTableQuestion()
//		createTableAnswer()

	}
	
	private void createUsersTable() {
	//USER
	String TABLE_NAME = "users";
	
	try {                                     
		statement = connect.prepareStatement("CREATE TABLE "+ TABLE_NAME + "("
				+ " studentNumber integer primary key,\n"
				+ " username varchar(30),\n"
				+ " password varchar(50),\n"
				+ " email varchar(60)"
				+ " )"
				);
		
		DatabaseMetaData dbm = connect.getMetaData();
		ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
		
		if(!tables.next()){
			//table does not exist
			boolean isTable = statement.execute();
			System.out.println(TABLE_NAME + " created");
			
		}else{
			System.out.println(TABLE_NAME + " exist already");
		}
	} catch (Exception e) {
		
	}

	
}
	
	public User getUser(String query) {
		User user = null;
		try {
			ResultSet result;
			statement =connect.prepareStatement(query);
			result = statement.executeQuery();
			while(result.next()){
//				user = new User(
//						result.getInt("studentNumber"),
//						result.getString("username"),
//						result.getString("password"),
//						result.getString("email")
//						);
			}
			
			return user;
		} catch (SQLException e) {
			
			return null;
		}
	}


	public boolean checkUser(String query) {
		int sn=-1;
		try {
			ResultSet result;
			statement =connect.prepareStatement(query);
			result = statement.executeQuery();
			while(result.next()){
				sn = result.getInt(1);					
			}
			if(sn == -1){
				
				 return false;				
			}else{
				return true;
			}
		} catch (SQLException e) {
			
			return false;
		}	
	}
	

	public boolean insert(String query, User user){
		try {
			statement = connect.prepareStatement(query);
//			statement.setInt(1, user.getStudentNumber());
//			statement.setString(2, user.getUsername());
//			statement.setString(3, user.getPassword());
//			statement.setString(4, user.getEmail());
			statement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	

	public boolean updateProfile(String query, User user){
		try {
			statement = connect.prepareStatement(query);
//			statement.setString(1, user.getUsername());
//			statement.setString(2, user.getPassword());
//			statement.setInt(3, user.getStudentNumber());
			int r = statement.executeUpdate();
			System.out.println(r);
			if(r>0){
				return true;
			}else return false;
		} catch (SQLException e) {
			return false;
		}
	}

	
}

