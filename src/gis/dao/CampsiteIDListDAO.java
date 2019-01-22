package gis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CampsiteIDListDAO {
	private Connection conn;
	
	public CampsiteIDListDAO(Connection conn){
		this.conn=conn;
	}
	
	/**
	 * 지역명에 의해 CAM_ID 목록 생성합니다
	 * @param region
	 * @return
	 * @throws SQLException
	 */
	public List<String> selectByRegion(String regionname) throws SQLException{
		
		String jsql = "SELECT * FROM tbl_baseinfo WHERE region=?";
		PreparedStatement pstmt=null;
		switch (regionname) {
		case "서울본부":
			jsql= "SELECT * FROM tbl_baseinfo WHERE region='서울'";
			pstmt = conn.prepareStatement(jsql);
			break;
		case "수도동부":
			jsql= "SELECT * FROM tbl_baseinfo WHERE region='경기'";
			pstmt = conn.prepareStatement(jsql);
			break;
		case "수도서부":
			jsql= "SELECT * FROM tbl_baseinfo WHERE region='경기'";
			pstmt = conn.prepareStatement(jsql);
			break;
		case "충북본부":
			jsql= "SELECT * FROM tbl_baseinfo WHERE region='충북'";
			pstmt = conn.prepareStatement(jsql);
			break;
		case "강원본부":
			jsql= "SELECT * FROM tbl_baseinfo WHERE region='강원'";
			pstmt = conn.prepareStatement(jsql);
			break;
		case "전남본부":
			jsql= "SELECT * FROM tbl_baseinfo WHERE region='전남'";
			pstmt = conn.prepareStatement(jsql);
			break;
		case "전북본부":
			jsql= "SELECT * FROM tbl_baseinfo WHERE region='전북'";
			pstmt = conn.prepareStatement(jsql);
			break;
		case "경북본부":
			jsql= "SELECT * FROM tbl_baseinfo WHERE region='경북'";
			pstmt = conn.prepareStatement(jsql);
			break;
		default:
			jsql= "SELECT * FROM tbl_baseinfo WHERE region=?";
			pstmt = conn.prepareStatement(jsql);
			pstmt.setString(1, regionname);
			break;
		}		
		
		
		ResultSet rs = pstmt.executeQuery();
		List<String> listCAMID=new ArrayList<String>();
		
		//sql문을 통해 선택된 결과를 한줄씩(레코드) 읽는다
		while(rs.next())
		{
			int intCAM_ID = rs.getInt("trip_id");
			String strCAM_ID = Integer.toString(intCAM_ID);
			listCAMID.add(strCAM_ID);
		}
		
		return listCAMID;
	}
	
	/**
	/**
	 * SelectGrade
	 * @param
	 * @return
	 * @throws SQLException
	 */
	public List<String> selectByGrade(String strGrade) throws SQLException {
		//String jsql=null;
		
		String jsql = "SELECT * FROM tbl_grade WHERE grade=?";
		PreparedStatement pstmt=null;
		
		switch (strGrade) {
		case "1등급":
			jsql= "SELECT * FROM tbl_grade WHERE grade='1'";	
			pstmt = conn.prepareStatement(jsql);
			break;
		case "2등급":
			jsql= "SELECT * FROM tbl_grade WHERE grade='2'";	
			pstmt = conn.prepareStatement(jsql);
			break;
		case "3등급":
			jsql= "SELECT * FROM tbl_grade WHERE grade='3'";	
			pstmt = conn.prepareStatement(jsql);
			break;
		default:
			jsql= "SELECT * FROM tbl_grade WHERE grade=?";
			pstmt.setString(1, strGrade);
			break;
		}
		 
		//PreparedStatement pstmt = conn.prepareStatement(jsql);
		ResultSet rs = pstmt.executeQuery();
		
		List<String> listCAMID=new ArrayList<String>();
		//sql문을 통해 선택된 결과를 한줄씩(레코드) 읽는다
		while(rs.next())
		{
			int intCAM_ID = rs.getInt("trip_id");
			String strCAM_ID = Integer.toString(intCAM_ID);
			listCAMID.add(strCAM_ID);
		}
		return listCAMID;     
		
	}
		
	/**
	 * (미사용) 통합 검색을 위한 예비
	 * @return
	 * @throws SQLException 
	 */
	public List<String> selectByIntergration(String[] paramRegion, String[] paramFacility, String[] paramTheme, String paramGrade) throws SQLException{
		String jsql = 
				String.format("SELECT * FROM (tbl_baseinfo INNER JOIN tbl_facility USING(trip_id)) INNER JOIN tbl_grade USING(trip_id)"+
							  " WHERE (%s) AND (%s) AND (%s)"
							  , this.whereStatementForRegions(paramRegion)
							  , this.whereStatementForFacilities(paramFacility));
						//	  , this.whereStatementForGrade(paramTheme));
						//	  , this.whereStatementForGrade(paramGrade));
		PreparedStatement pstmt = conn.prepareStatement(jsql);
		ResultSet rs = pstmt.executeQuery();
		List<String> listTRIPID=new ArrayList<String>();
		//sql문을 통해 선택된 결과를 한줄씩(레코드) 읽는다
		while(rs.next())
		{
			int intCAM_ID = rs.getInt("CAM_ID");
			String strCAM_ID = Integer.toString(intCAM_ID);
			listTRIPID.add(strCAM_ID);
		}
		return listTRIPID; 

	}
	
	/**
	 * 통합검색 지역 조건문 생성
	 * SELECT ~~ Where ([여기에 출력될 내용]) AND ~~;
	 * @param paramRegion
	 * @return
	 */
	private String whereStatementForRegions(String[] param){
		if(param==null){
			//SELECT ~~ Where (true) AND ~~;
			return "(true)";
		}
		StringBuilder stringbuilder=new StringBuilder();
		
		//문자열 배열의 크기
		int count = param.length;
		
		//만약 배열의 크기가 0이라면!
		if(count==0){
			//SELECT ~~ Where (true) AND ~~;
			stringbuilder.append("true");
		}
		for(int i=0;i<count;i++){

			if(i>0){//이전 내용이 있으면  OR 를 붙임
				stringbuilder.append(" OR ");	
			}
			
			// 만약 paramRegion[i]가 서울이라면 (tbl_baseinfo.region='서울')
			stringbuilder.append(String.format("(tbl_baseinfo.region='%s')", param[i]));
		}
		// stringbuilder에 누적된 내용을 문자열로 변환하여 반환한다.
		return stringbuilder.toString();
	}
	
	/**
	 * 통합검색 시설 조건문 생성
	 * @param param
	 * @return
	 */
	private String whereStatementForFacilities(String[] param){
		if(param==null){
			//SELECT ~~ Where (true) AND ~~;
			return "(true)";
		}
		StringBuilder stringbuilder=new StringBuilder();
		
		//문자열 배열의 크기
		int count = param.length;
		
		//만약 배열의 크기가 0이라면!
		if(count==0){
			//SELECT ~~ Where (true) AND ~~;
			return "(true)";
		}
		for(int i=0;i<count;i++){

			if(i>0){//이전 내용이 있으면  OR 를 붙임
				stringbuilder.append(" OR ");	
			}
			
			switch(param[i]){
			case "bed":
				stringbuilder.append("(tbl_facility.bed=1)");
				break;
			case "toilet":
				stringbuilder.append("(tbl_facility.toilet=1)");
				break;
			case "wifi":
				stringbuilder.append("(tbl_facility.wifi=1)");
				break;
			default:
				stringbuilder.append("(true)");
			}
		}
		// stringbuilder에 누적된 내용을 문자열로 변환하여 반환한다.
		return stringbuilder.toString();
	}
	
	/**
	 * 통합검색 테마 조건문 생성
	 * @param param
	 * @return
	 */
	private String whereStatementForGrade(String[] param){
		if(param==null){
			//SELECT ~~ Where (true) AND ~~;
			return "(true)";
		}
		StringBuilder stringbuilder=new StringBuilder();
		
		//문자열 배열의 크기
		int count = param.length;
		
		//만약 배열의 크기가 0이라면!
		if(count==0){
			//SELECT ~~ Where (true) AND ~~;
			return "(true)";
		}
		for(int i=0;i<count;i++){

			if(i>0){//이전 내용이 있으면  OR 를 붙임
				stringbuilder.append(" OR ");	
			}
			
			switch(param[i]){
			case "1":
				stringbuilder.append("(tbl_grade.grade=1)");
				break;
			case "2":
				stringbuilder.append("(tbl_grade.grade=2)");
				break;
			case "3":
				stringbuilder.append("(tbl_grade.grade=3)");
				break;
			default:
				stringbuilder.append("(true)");
			}
		}
		// stringbuilder에 누적된 내용을 문자열로 변환하여 반환한다.
		return stringbuilder.toString();
	}	
	
	private String whereStatementForGrade(String param){
		if(param==null){
			return "(true)";//여기서 "(true)" 반환
		}
		int intGrade;
		
		try{
			intGrade = Integer.parseInt(param);
		}catch(Exception ex){//형변환에 오류가 있으면
			return "(true)";//여기서 "(true)" 반환
		}
		
		return String.format("(tbl_grade.grade=%d)",intGrade);
	}

}
