package dao;

import java.sql.SQLException;
import java.util.List;

import bean.MessageBean;

public interface MessageDao {
	public List<MessageBean> getAll() throws SQLException;
	public int addReply(String reply,int id) throws SQLException;
	public int addMessage(MessageBean message) throws SQLException;
}
