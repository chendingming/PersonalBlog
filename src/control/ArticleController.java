package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.ArticleBean;
import bean.UserBean;
import service.ArticleService;
import utils.FileUpLoadUtil;

/**
 * Servlet implementation class ArticleController
 */
@WebServlet("/ArticleController")
public class ArticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleService as=new ArticleService();
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action=request.getParameter("action");
		switch(action){
		case "getAllArticle":getAllArticle(request,response);break;
		case "getById":getById(request,response);break;
		case "writeArticle":writeArticle(request,response);break;
		case "finHot":finHot(request,response);break;
		case "search":search(request,response);break;
		case "delArticle":delArticle(request,response);break;
		case "editarticle":editarticle(request,response);break;
		case "update":update(request,response);break;
		}
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String filePath="D:/WebWorkplace/PersonalBlog/WebContent/img";
		FileUpLoadUtil f=new FileUpLoadUtil();
		ArticleBean article=null;
		try {
			article=f.fileUpLoad(request, filePath,0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int i=0;
		i=as.update(article);
		if(i>0){
			response.getWriter().println("<script>alert('更新成功');window.location.href='writeArticle.jsp';</script>");
		}else{
			response.getWriter().println("<script>alert('更新失败');window.location.href='writeArticle.jsp';</script>");
		}
		
	}
	private void delArticle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int articleid=Integer.parseInt(request.getParameter("articleid"));
		int i=0;
		i=as.delete(articleid);
		if(i>0){
			response.getWriter().println("<script>alert('删除成功');window.location.href='writeArticle.jsp';</script>");
		}
		
	}
	private void editarticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int articleid=Integer.parseInt(request.getParameter("articleid"));
		ArticleBean article=as.findById(articleid);
		request.setAttribute("article", article);
		request.getRequestDispatcher("articledetail.jsp?edit=0").forward(request, response);
		
	}
	private void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String searchText=request.getParameter("search");
		List<ArticleBean> list=null;
		list=as.search(searchText);
		Gson gson=new Gson();
		String result=gson.toJson(list);
		response.getWriter().println(result);
		
	}
	private void finHot(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<ArticleBean> list=null;
		list=as.finHot();
		Gson gson=new Gson();
		String hot=gson.toJson(list);
		response.getWriter().println(hot);
		
	}
	private void writeArticle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String filePath="D:/WebWorkplace/PersonalBlog/WebContent/img";
		FileUpLoadUtil f=new FileUpLoadUtil();
		UserBean user=(UserBean) request.getSession().getAttribute("user");
		ArticleBean article=null;
		try {
			article=f.fileUpLoad(request, filePath,1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		article.setAuthor(user.getName());
		int i=0;
		i=as.add(article);
		if(i>0){
			response.getWriter().println("<script>alert('发布成功');window.location.href='writeArticle.jsp';</script>");
		}
	}
	private void getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		ArticleBean article=as.findById(id);
		request.setAttribute("article", article);
		request.getRequestDispatcher("article.jsp").forward(request, response);
		
	}
	private void getAllArticle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Gson gson=new Gson();
		List<ArticleBean> list=as.finAll();
		String articles=gson.toJson(list);
		response.getWriter().println(articles);
	}

}
