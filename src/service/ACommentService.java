package service;

import java.sql.SQLException;
import java.util.List;

import bean.AComment;
import dao.ACommentDao;
import dao.impl.ACommentDaoImpl;

public class ACommentService {
	ACommentDao acd=new ACommentDaoImpl();
	public int add(AComment ac){
		int i=0;
		try {
			i=acd.add(ac);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	public List<AComment> getAll(int articleid){
		List<AComment> list=null;
		try {
			list=acd.getAll(articleid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
