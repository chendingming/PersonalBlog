package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.MyInfo;
import service.MyInfoService;

/**
 * Servlet implementation class MyInfoController
 */
@WebServlet("/MyInfoController")
public class MyInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MyInfoService ms=new MyInfoService();
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action=request.getParameter("action");
		switch(action){
		case "getMyInfo":getMyInfo(request,response);break;
		case "updateMyInfo":updateMyInfo(request,response);break;
		}
	}
	private void updateMyInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int userid=Integer.parseInt(request.getParameter("userID"));
		String info=request.getParameter("info");
		String hobby=request.getParameter("hobby");
		String qq=request.getParameter("qq");
		String email=request.getParameter("email");
		String description=request.getParameter("description");
		MyInfo myInfo=new MyInfo();
		myInfo.setDescription(description);
		myInfo.setEmail(email);
		myInfo.setHobby(hobby);
		myInfo.setUserid(userid);
		myInfo.setQq(qq);
		myInfo.setInfo(info);
		int i=0;
		i=ms.updateInfo(myInfo);
		if(i>0){
			response.getWriter().println("<script>alert('修改成功');window.location.href='index.jsp';</script>");
		}else{
			response.getWriter().println("<script>alert('修改失败');window.location.href='index.jsp';</script>");
		}
		
	}
	private void getMyInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MyInfo info=null;
		info=ms.getInfo();
		if(info!=null){
			request.setAttribute("me", info);
			request.getRequestDispatcher("aboutMe.jsp").forward(request, response);
		}else{
			response.getWriter().println("<script>alert('数据错误');window.location.href='index.jsp';</script>");
		}
		
	}

}
