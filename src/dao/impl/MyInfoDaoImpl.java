package dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import bean.MyInfo;
import dao.MyInfoDao;
import utils.DataSourceUtils;

public class MyInfoDaoImpl implements MyInfoDao{

	@Override
	public MyInfo getInfo() throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from myinfo where userid=1";
		MyInfo info=runner.query(sql, new BeanHandler<MyInfo>(MyInfo.class));
		return info;
	}

	@Override
	public int updateInfo(MyInfo info) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update myinfo set info=?,hobby=?,qq=?,email=?,description=? where userid=?";
		return runner.update(sql,info.getInfo(),info.getHobby(),info.getQq(),info.getEmail(),info.getDescription(),info.getUserid());
	}
	
}
