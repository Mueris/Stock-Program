import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class sql {
private Connection conn;

String url="jdbc:sqlserver://localhost;encrypt=true;databaseName=./SQLEXPRESS01;integratedSecurity=true";
String user="Eren";
String password="1234";
 public Connection baglan() {
	try {
		conn=DriverManager.getConnection(url,user,password);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return conn;
}
 public void kull() {
	 if(conn==null) {
		 System.out.println("Baðlanamýyorum...");
		 baglan();
	 }
	 try {
		Statement stmt=conn.createStatement();
		ResultSet rs =stmt.executeQuery("select * from Ogrenci");
		while(rs.next()) {
			System.out.println(rs.getString(1));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
 	public static void main(String args[]) {
 		sql ne = new sql();
 		ne.kull();
 	}

}
