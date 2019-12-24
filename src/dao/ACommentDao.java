package dao;

import java.sql.SQLException;
import java.util.List;

import bean.AComment;

public interface ACommentDao {
	public int add(AComment ac) throws SQLException;
	public List<AComment> getAll(int articleid) throws SQLException;
}
