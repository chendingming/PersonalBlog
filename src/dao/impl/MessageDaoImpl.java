package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bean.MessageBean;
import dao.MessageDao;
import utils.DataSourceUtils;

public class MessageDaoImpl implements MessageDao{

	@Override
	public List<MessageBean> getAll() throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from messagelist";
		List<MessageBean> list=runner.query(sql, new BeanListHandler<MessageBean>(MessageBean.class));
		return list;
	}

	@Override
	public int addReply(String reply,int id) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update messagelist set reply=? where id=?";
		return runner.update(sql,reply,id);
	}

	@Override
	public int addMessage(MessageBean message) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into messagelist values(?,?,?,?,?,?)";
		return runner.update(sql,message.getId(),message.getUserid(),message.getUsername(),message.getMessage(),message.getDate(),message.getReply());
	}

}
