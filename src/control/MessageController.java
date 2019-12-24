package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.MessageBean;
import service.MessageService;
import utils.Time;

/**
 * Servlet implementation class MessageController
 */
@WebServlet("/MessageController")
public class MessageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MessageService ms=new MessageService();
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action=request.getParameter("action");
		switch(action){
		case "getAllMessage":getAllMessage(request,response);break;
		case "addnewreply":addnewreply(request,response);break;
		case "addMessage":addMessage(request,response);break;
		}
	}
	private void addMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int userid=Integer.parseInt(request.getParameter("userid"));
		String username=request.getParameter("username");
		String message=request.getParameter("message");
		MessageBean messageBean=new MessageBean();
		Time time=new Time();
		messageBean.setUserid(userid);
		messageBean.setUsername(username);
		messageBean.setMessage(message);
		messageBean.setDate(time.getDay());
		messageBean.setReply("");
		int i=0;
		i=ms.addMessage(messageBean);
		if(i>0){
			Gson gson=new Gson();
			String mess=gson.toJson(messageBean);
			response.getWriter().println(mess);
		}
		
	}
	private void addnewreply(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id=Integer.parseInt(request.getParameter("messid"));
		String reply=request.getParameter("reply");
		int i=0;
		i=ms.addReply(reply, id);
		if(i>0){
			response.getWriter().println(i);
		}
		
	}
	private void getAllMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Gson gson=new Gson();
		List<MessageBean> list=ms.getAll();
		String message=gson.toJson(list);
		response.getWriter().println(message);
		
	}

}
