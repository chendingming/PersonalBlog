package dao;

import java.sql.SQLException;
import java.util.List;

import bean.ArticleBean;

public interface ArticleDao {
	public List<ArticleBean> finAll() throws SQLException;
	public ArticleBean findById(int id) throws SQLException;
	public int add(ArticleBean article)	throws SQLException;
	public List<ArticleBean> finHot() throws SQLException;
	public int addCom(int id) throws SQLException;
	public List<ArticleBean> search(String search) throws SQLException;
	public int delete(int id) throws SQLException;
	public int update(ArticleBean article) throws SQLException;
}
