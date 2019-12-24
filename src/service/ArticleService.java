package service;

import java.sql.SQLException;
import java.util.List;

import bean.ArticleBean;
import dao.ArticleDao;
import dao.impl.ArticleDaoImpl;

public class ArticleService {
	ArticleDao ad=new ArticleDaoImpl();
	public List<ArticleBean> finAll(){
		List<ArticleBean> list=null;
		try {
			list=ad.finAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public ArticleBean findById(int id){
		ArticleBean article=null;
		try {
			article=ad.findById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return article;
	}
	public int add(ArticleBean article){
		int i=0;
		try {
			i=ad.add(article);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	public List<ArticleBean> finHot(){
		List<ArticleBean> list=null;
		try {
			list=ad.finHot();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public int addCom(int id){
		int i=0;
		try {
			i=ad.addCom(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	public List<ArticleBean> search(String search){
		List<ArticleBean> list=null;
		try {
			list=ad.search(search);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public int delete(int id){
		int i=0;
		try {
			i=ad.delete(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	public int update(ArticleBean article){
		int i=0;
		try {
			i=ad.update(article);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
}
