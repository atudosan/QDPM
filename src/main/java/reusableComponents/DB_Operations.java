package reusableComponents;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;  

public class DB_Operations {
	
		//this method will get the reponse from DB in stored into a map variable
		public synchronized HashMap<String, String> getSqlResultInMap(String sql) {  
            HashMap<String, String> data_map = new HashMap<>();

			try{  
				Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection(  
						"jdbc:mysql://localhost:3306/qdpm_qa?serverTimezone=UTC","root",""); 
						
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery(sql);  
	            ResultSetMetaData md = rs.getMetaData();

	            while (rs.next()) {            
	                for (int i = 1; i <= md.getColumnCount(); i++) {
	                    data_map.put(md.getColumnName(i), rs.getString(i));
	                }
	            }
	            System.err.println(data_map);
				con.close();  
			}catch(Exception e){ System.err.println(e);}
			return data_map;  
		}  

}
