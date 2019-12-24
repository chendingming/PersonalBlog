package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bean.UserBean;
import dao.UserDao;
import utils.DataSourceUtils;

public class UserDaoImpl implements UserDao{

	@Override
	public int add(UserBean user) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into user values(?,?,?,?,?,?,?)";
		return runner.update(sql,user.getId(),user.getName(),user.getBlog_adress(),user.getEmail_address(),user.getPassword(),user.getType(),user.getStranger());
	}

	@Override
	public UserBean findByEmailsAndPw(String emails, String password,int type) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from user where email_address=? and password=? and type=?";
		UserBean user=runner.query(sql, new BeanHandler<UserBean>(UserBean.class),emails,password,type);
		return user;
	}

	@Override
	public UserBean findByName(String name) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from user where name=?";
		UserBean user=runner.query(sql, new BeanHandler<UserBean>(UserBean.class),name);
		return user;
	}

	@Override
	public List<UserBean> findMyFriends() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from user where stranger=1 and type=0";
		List<UserBean> user=runner.query(sql, new BeanListHandler<UserBean>(UserBean.class));
		return user;
	}

	@Override
	public List<UserBean> findAllUser() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from user where type=0";
		List<UserBean> user=runner.query(sql, new BeanListHandler<UserBean>(UserBean.class));
		return user;
	}

	@Override
	public int addFriend(int id) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update user set stranger=1 where id=?";
		
		return runner.update(sql,id);
	}

	@Override
	public int deleteFriend(int id) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update user set stranger=2 where id=?";
		
		return runner.update(sql,id);
	}

	@Override
	public int deleteUser(int id) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="delete from user where id=?";
		
		return runner.update(sql,id);
	}

	@Override
	public List<UserBean> findMyFriend() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from user where stranger=1 and type=0";
		List<UserBean> list=runner.query(sql, new BeanListHandler<UserBean>(UserBean.class));
		return list;
	}

}
