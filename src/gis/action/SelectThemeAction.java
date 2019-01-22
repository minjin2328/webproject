package gis.action;

import java.sql.Connection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import gis.ActionForward;
import gis.IAction;
import gis.dao.CampsiteIDListDAO;
import gis.datasource.DBConnection;

public class SelectThemeAction implements IAction{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
	    String strGrade = request.getParameter("grade");
	    //TOMCAT 서버의 기본 URLEncoding이 ISO-8859-1(서유럽어)로 되어있어서 바이트로 읽어서 UTF-8로변환
	    //근본적인 해결읜 TOMCAT 서버의 URLEncoding 관련 설정을 수정해줘야함
		//<Connector connectionTimeout="20000" port="8080" protocol="HTTP/1.1" redirectPort="8443" URIEncoding="UTF-8" />
		//<Connector port="8009" protocol="AJP/1.3" redirectPort="8443" URIEncoding="UTF-8" />

	    //if(strGrade!=null)   strGrade = new String(grade.getBytes("ISO-8859-1"), "UTF-8");
	    
	    String strResult = null;
	    
	    //CampsiteInfoDAO(DB Access Object)로 부터 CampID에 해당하는 VO를
  		Connection conn=null;
  		try{
  			conn = DBConnection.getInstance();
  			
  			//CampsiteIDListDAO 클래스를 DBConnecion으로 초기화
  			CampsiteIDListDAO campsiteIDlistDAO=new CampsiteIDListDAO(conn);
  			
  			//campsiteIDlistDAO에서 regionname으로 선택하여 campsiteID 목록을 가져옴
  			List<String> listCAM_ID = campsiteIDlistDAO.selectByGrade(strGrade);
  			// Gson: Google의 JAVA용 JSON 라이브러리
  			Gson gson = new Gson();
  			
  			strResult = gson.toJson(listCAM_ID);

  		}finally{
  			
  			if(conn!=null)try{conn.close();}catch (Exception e) {}
  		}
  				
		// Forward 정보 생성
	    ActionForward forward=new ActionForward();
	    request.setAttribute("output", strResult);//파라미터 추가
	    forward.setPath("/WEB-INF/view/json.jsp");
	    
		return forward;
	}	
	
}
