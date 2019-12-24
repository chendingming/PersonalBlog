package service;


import java.sql.SQLException;

import bean.MyInfo;
import dao.MyInfoDao;
import dao.impl.MyInfoDaoImpl;

public class MyInfoService {
	MyInfoDao md=new MyInfoDaoImpl();
	public MyInfo getInfo(){
		MyInfo info=null;
		try {
			info=md.getInfo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}
	public int updateInfo(MyInfo info) {
		int i=0;
		try {
			i=md.updateInfo(info);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
}
