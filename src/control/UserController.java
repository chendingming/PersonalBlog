package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import bean.UserBean;
import service.UserService;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService=new UserService();

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * ע��ʱ�ж��ǳ��Ƿ��ظ�
	 * ��¼�ж��û�����
	 * 
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action=request.getParameter("action");
		switch(action){
		case "adminlogin":adminlogin(request,response);break;
		case "userlogin":userlogin(request,response);break;
		case "register":register(request,response);break;
		case "logout":logout(request,response);break;
		case "findAllUser":findAllUser(request,response);break;
		case "findMyFriends":findMyFriends(request,response);break;
		case "addFriend":addFriend(request,response);break;
		case "deleteFriend":deleteFriend(request,response);break;
		case "deleteUser":deleteUser(request,response);break;
		}
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int userid=Integer.parseInt(request.getParameter("userid"));
		int i=0;
		i=userService.deleteUser(userid);
		if(i>0){
			response.getWriter().println(i);
		}
		
	}

	private void deleteFriend(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int userid=Integer.parseInt(request.getParameter("userid"));
		int i=0;
		i=userService.deleteFriend(userid);
		if(i>0){
			response.getWriter().println(i);
		}
		
	}

	private void addFriend(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int userid=Integer.parseInt(request.getParameter("userid"));
		int i=0;
		i=userService.addFriend(userid);
		if(i>0){
			response.getWriter().println(i);
		}
	}

	private void findMyFriends(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<UserBean> list=null;
		list=userService.findMyFriend();
		Gson gson=new Gson();
		String friend=gson.toJson(list);
		response.getWriter().println(friend);
	}

	private void findAllUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<UserBean> userList=userService.findAllUser();
		request.setAttribute("allUser", userList);
		request.getRequestDispatcher("friends.jsp").forward(request, response);
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute("user");
		
		response.sendRedirect("index.jsp");
		
		
	}

	private void userlogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		UserBean user=userService.find(email, password, 0);
		if(user!=null){
			HttpSession session=request.getSession();
			session.setAttribute("user", user);
			response.getWriter().println("<script>alert('��¼�ɹ�');window.location.href='index.jsp';</script>");
		}else{
			response.getWriter().println("<script>alert('������������');window.location.href='index.jsp';</script>");
		}
	}

	private void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name=request.getParameter("username");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		UserBean userCheck=null;
		userCheck=userService.find(name);
		if(userCheck==null){
			UserBean user=new UserBean();
			user.setName(name);
			user.setEmail_address(email);
			user.setPassword(password);
			user.setBlog_adress("http://localhost:8080/PersonalBlog/index.jsp");
			user.setType(0);
			user.setStranger(8);
			if(userService.add(user)){
				response.getWriter().println("<script>alert('ע��ɹ�');window.location.href='login.jsp';</script>");
			}else{
				response.getWriter().println("<script>alert('ע��ɹ�');window.location.href='register.jsp';</script>");
			}
		}else{
			response.getWriter().println("<script>alert('���ǳ��Ѵ��ڣ���ʹ�������ǳ�');window.location.href='register.jsp';</script>");
		}
		
	}

	private void adminlogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		UserBean user=userService.find(email, password, 1);
		if(user!=null){
			HttpSession session=request.getSession();
			session.setAttribute("user", user);
			response.getWriter().println("<script>alert('��¼�ɹ�');window.location.href='index.jsp';</script>");
		}else{
			response.getWriter().println("<script>alert('������������');window.location.href='index.jsp';</script>");
		}
		
	}

}
