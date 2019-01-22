package gis.action;

import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gis.ActionForward;
import gis.IAction;
import gis.dao.CampsiteInfoDAO;
import gis.datasource.DBConnection;
import gis.vo.CampsiteInfo;

/**
 * 캠프장 선택에 대한 Action 클래스
 */
public class SelectCampsiteAction implements IAction {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// Parameter 처리
		String trip_id = request.getParameter("trip_id");
		String strTrip_ID = request.getParameter("selectcam_id");
		
		int intTripid =Integer.parseInt(trip_id);
		
		//CampsiteInfoDAO(DB Access Object)로 부터 CampID에 해당하는 VO를
		Connection conn=null;
		CampsiteInfo campsiteinfo=null;
		try{
			conn = DBConnection.getInstance();
			
			CampsiteInfoDAO campsiteInfoDAO=new CampsiteInfoDAO(conn);
			campsiteinfo = campsiteInfoDAO.selectByID(intTripid);
			System.out.println("###################:");

		}finally{
			
			if(conn!=null)try{conn.close();}catch (Exception e) {}
		}
	    
	    // Forward 정보 생성
	    ActionForward forward=new ActionForward();
	    request.setAttribute("CampsiteInfo", campsiteinfo);//파라미터 추가
	    request.setAttribute("trip_ID", strTrip_ID);//파라미터 추가
	    forward.setPath("/WEB-INF/view/informationWindow.jsp");	    

		return forward;
	}


}
