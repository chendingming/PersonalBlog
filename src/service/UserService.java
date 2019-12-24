package service;

import java.sql.SQLException;
import java.util.List;

import bean.UserBean;
import dao.UserDao;
import dao.impl.UserDaoImpl;

public class UserService {
	UserDao userDao=new UserDaoImpl();
	public boolean add(UserBean user){
		int i=0;
		try {
			i=userDao.add(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i==0){
			return false;
		}else{
			return true;
		}
	}
	public UserBean find(String emails,String password,int type){
		UserBean user=null;
		try {
			user=userDao.findByEmailsAndPw(emails, password,type);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	public UserBean find(String name){
		UserBean user=null;
		try {
			user=userDao.findByName(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	public List<UserBean> findMyFriends(){
		List<UserBean> user=null;
		try {
			user=userDao.findMyFriends();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	public List<UserBean> findAllUser(){
		List<UserBean> user=null;
		try {
			user=userDao.findAllUser();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	public int addFriend(int id){
		int i=0;
		try {
			i=userDao.addFriend(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	public int deleteFriend(int id){
		int i=0;
		try {
			i=userDao.deleteFriend(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	public int deleteUser(int id){
		int i=0;
		try {
			i=userDao.deleteUser(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	public List<UserBean> findMyFriend(){
		List<UserBean> user=null;
		try {
			user=userDao.findMyFriend();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
}
