package gis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gis.vo.CampsiteInfo;

public class CampsiteInfoDAO {
	private Connection conn;
	
	public CampsiteInfoDAO(Connection conn){
		this.conn=conn;
	}
	
	public CampsiteInfo selectByID(int trip_id) throws SQLException {
		String jsql = "SELECT * FROM (tbl_baseinfo INNER JOIN tbl_facility USING(trip_id)) INNER JOIN tbl_grade USING(trip_id) WHERE trip_id=?";
		PreparedStatement pstmt = conn.prepareStatement(jsql);
	    pstmt.setInt(1,trip_id);
	    
	    ResultSet rs = pstmt.executeQuery();
	    rs.next();
	    
	    String name = rs.getString("name");
        String region = rs.getString("region");
        String address = rs.getString("address");	        
        int phone = rs.getInt("phone");
        String price = rs.getString("price");
        
        int bed =rs.getInt("bed");
        int toilet =rs.getInt("toilet");
        int wifi =rs.getInt("wifi");
        
        int first =rs.getInt("first");
        int second =rs.getInt("second");
        int third =rs.getInt("third");
        
        CampsiteInfo campsiteinfo = new CampsiteInfo(name, region, address, phone, price, bed, toilet, wifi,first, second, third);
        
        return campsiteinfo;
	    
	}
	
	
}
