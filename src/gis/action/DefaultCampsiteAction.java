package gis.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gis.ActionForward;
import gis.IAction;

/**
 * 기본 CampsiteAction, 초기화면으로 이동
 */
public class DefaultCampsiteAction implements IAction {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

	    // Forward 정보 생성
	    ActionForward forward=new ActionForward();
	    forward.setPath("/WEB-INF/view/index.jsp");	    

		return forward;
	}

}
