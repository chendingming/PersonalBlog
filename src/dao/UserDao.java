package dao;

import java.sql.SQLException;
import java.util.List;

import bean.UserBean;

public interface UserDao {
	public int add(UserBean user) throws SQLException;
	public UserBean findByEmailsAndPw(String emails,String password,int type) throws SQLException;
	public UserBean findByName(String name) throws SQLException;
	public List<UserBean> findMyFriends() throws SQLException;
	public List<UserBean> findAllUser() throws SQLException;
	public int addFriend(int id) throws SQLException;
	public int deleteFriend(int id) throws SQLException;
	public int deleteUser(int id) throws SQLException;
	public List<UserBean> findMyFriend() throws SQLException;
}
