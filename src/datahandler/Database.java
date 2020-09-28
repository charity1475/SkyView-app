package datahandler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Database {
	public String url;
	public Connection conn;
	public String locationString;

	public Database(String url, Connection conn,String locatioString) {
		this.url = url;
		this.conn = conn;
		this.locationString = locatioString;
	}
	public Database() {
		this.url ="jdbc:sqlserver://localhost:1433;instanceName=sqlservr;databaseName=students";
		this.conn = null;
	}
	
	


	public void setLocationString(String locationString) {
		this.locationString = locationString;
	}
	public void store() {
	    try {
	      conn = DriverManager.getConnection(url,"sa","@Charity1475");
	      System.out.println("Connected...");
	      String sql = ("insert into locations values(1,'"+locationString+"')");
	      System.out.println(sql);
	      java.sql.Statement statement =conn.createStatement();
	      statement.executeUpdate(sql);
	      ResultSet rs = statement.executeQuery("select * from locations");
	      while(rs.next()) {
	    	  System.out.println(rs.getString("place"));
	      }
	      } catch (SQLException e ) {
	          System.out.println("Error");
	          System.out.println(e.getMessage());
	     
	    } catch (Exception e) {
			System.out.println("Rejected transaction");
			System.out.println(e.getMessage());
		} finally {
	      try {
	        if (conn != null) {
	            conn.close();
	        }
	      } catch (SQLException ex) {
	    	  System.out.println("Final catch");
	          System.out.println(ex.getMessage());
	      }
	    }
	}
}
