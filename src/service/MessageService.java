package service;

import java.sql.SQLException;
import java.util.List;

import bean.MessageBean;
import dao.MessageDao;
import dao.impl.MessageDaoImpl;

public class MessageService {
	MessageDao md=new MessageDaoImpl();
	public List<MessageBean> getAll(){
		List<MessageBean> list=null;
		try {
			list=md.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public int addReply(String reply,int id){
		int i=0;
		try {
			i=md.addReply(reply, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	public int addMessage(MessageBean message){
		int i=0;
		try {
			i=md.addMessage(message);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
}
