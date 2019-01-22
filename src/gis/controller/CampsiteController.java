package gis.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gis.ActionForward;
import gis.CommandFactory;
import gis.IAction;


/**
 * Servlet implementation class Campsite
 */
@WebServlet("/Tripsite.do")
public class CampsiteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * HttpServlet#HttpServlet()
     */
    public CampsiteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Get 방식의 요청이 들어왔을 경우
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * Post 방식으로 요청이 들어왔을 경우
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IAction action=null;
		ActionForward forward=null;
		request.setCharacterEncoding("utf-8");
		try{
			CommandFactory commandFactory=CommandFactory.getInstance();
			
			String strAction = (String)request.getParameter("action");
			if( (strAction==null) || (strAction.equals("")) ) 
			{
				strAction="DefaultCampsite";
			}
			//CommandFactory로부터 Action을 얻어옴
			action=commandFactory.getAction(strAction);
			
			//Action으롭터의 결과(전달인자, forward 정보(JSP(View)페이지와 리다이렉트/포워드방식))를 받아옴
			forward=action.execute(request, response);
			
		}catch(Exception ex)
		{
			throw new ServletException(ex.getMessage());
		}
		
		//포워드 정보가 리다이렉트방식인지 포워드방식인지에 따라 처리
		
		if(forward.isRedirect()){
			response.sendRedirect(forward.getPath());
		}else{
			RequestDispatcher rd = request.getRequestDispatcher(forward.getPath());
			rd.forward(request, response);
		}
	}

}
