package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Life;
import service.LifeService;
import utils.Time;

/**
 * Servlet implementation class LifeController
 */
@WebServlet("/LifeController")
public class LifeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LifeService ls=new LifeService();
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action=request.getParameter("action");
		switch(action){
		case "addLife":addLife(request,response);break;
		case "delete":delete(request,response);break;
		case "getAllLife":getAllLife(request,response);break;
		}
	}
	private void getAllLife(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Life> list=null;
		list=ls.getAll();
		request.setAttribute("life", list);
		request.getRequestDispatcher("life.jsp").forward(request, response);
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id=Integer.parseInt(request.getParameter("lifeid"));
		int i=0;
		i=ls.deleteById(id);
		if(i>0){
			response.getWriter().println(i);
		}
		
	}
	private void addLife(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String lifeBody=request.getParameter("lifeBody");
		int i=0;
		Life life=new Life();
		Time time=new Time();
		life.setDate(time.getDay());
		life.setBody(lifeBody);
		i=ls.add(life);
		if(i>0){
			response.getWriter().println(i);
		}
	}

}
