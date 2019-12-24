package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.AComment;
import service.ACommentService;
import service.ArticleService;
import utils.Time;

/**
 * Servlet implementation class ACommentController
 */
@WebServlet("/ACommentController")
public class ACommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ACommentService service=new ACommentService();
	ArticleService as=new ArticleService();
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action=request.getParameter("action");
		switch(action){
		case "add":add(request,response);break;
		case "getAll":getAll(request,response);break;
		}
	}
	private void getAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id=Integer.parseInt(request.getParameter("articleid"));
		System.out.println(id);
		Gson gson=new Gson();
		List<AComment> list=service.getAll(id);
		String acomment=gson.toJson(list);
		response.getWriter().println(acomment);
	}
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int article_id=Integer.parseInt(request.getParameter("articleid"));
		String body=request.getParameter("body");
		String user_name=request.getParameter("username");
		AComment ac=new AComment();
		Time time=new Time();
		ac.setArticle_id(article_id);
		ac.setBody(body);
		ac.setDate(time.getDay());
		ac.setUser_name(user_name);
		int i=0;
		int x=0;
		i=service.add(ac);
		x=as.addCom(article_id);
		if(i>0&&x>0){
			Gson gson=new Gson();
			String acm=gson.toJson(ac);
			response.getWriter().println(acm);
		}
		
	}

}
