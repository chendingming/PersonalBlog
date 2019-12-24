package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bean.AComment;
import dao.ACommentDao;
import utils.DataSourceUtils;

public class ACommentDaoImpl implements ACommentDao{

	@Override
	public int add(AComment ac) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into article_comment values(?,?,?,?,?)";
		return runner.update(sql, ac.getId(),ac.getArticle_id(),ac.getBody(),ac.getDate(),ac.getUser_name());
	}

	@Override
	public List<AComment> getAll(int articleid) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from article_comment where article_id=?";
		List<AComment> list=runner.query(sql, new BeanListHandler<AComment>(AComment.class),articleid);
		return list;
	}

}
