package dao;

import java.sql.SQLException;

import bean.MyInfo;

public interface MyInfoDao {
	public MyInfo getInfo() throws SQLException;
	public int updateInfo(MyInfo info) throws SQLException;
}
