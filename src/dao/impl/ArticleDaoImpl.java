package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bean.ArticleBean;
import dao.ArticleDao;
import utils.DataSourceUtils;

public class ArticleDaoImpl implements ArticleDao{

	@Override
	public List<ArticleBean> finAll() throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from article order by date desc";
		List<ArticleBean> list=runner.query(sql, new BeanListHandler<ArticleBean>(ArticleBean.class));
		return list;
	}

	@Override
	public ArticleBean findById(int id) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from article where id=?";
		ArticleBean article=runner.query(sql, new BeanHandler<ArticleBean>(ArticleBean.class),id);
		return article;
	}

	@Override
	public int add(ArticleBean article) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into article values(?,?,?,?,?,?,?)";
		return runner.update(sql,article.getId(),article.getTitle(),article.getBody(),article.getNum(),article.getImage(),article.getDate(),article.getAuthor());
	}

	@Override
	public List<ArticleBean> finHot() throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="SELECT * from article ORDER BY num DESC LIMIT 5";
		List<ArticleBean> list=runner.query(sql, new BeanListHandler<ArticleBean>(ArticleBean.class ));
		return list;
	}

	@Override
	public int addCom(int id) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update article set num=num+1 where id=?";
		return runner.update(sql,id);
	}

	@Override
	public List<ArticleBean> search(String search) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from article where title like ? or body like ?";
		String parm="%"+search+"%";
		List<ArticleBean> list=runner.query(sql, new BeanListHandler<ArticleBean>(ArticleBean.class),parm,parm);
		return list;
	}

	@Override
	public int delete(int id) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="delete from article where id=?";
		return runner.update(sql,id);
	}

	@Override
	public int update(ArticleBean article) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update article set title=?,body=?,image=? where id=?";
		return runner.update(sql,article.getTitle(),article.getBody(),article.getImage(),article.getId());
	}

}
