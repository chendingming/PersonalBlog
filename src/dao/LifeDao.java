package dao;

import java.sql.SQLException;
import java.util.List;

import bean.Life;

public interface LifeDao {
	public int add(Life life) throws SQLException;
	public List<Life> getAll() throws SQLException;
	public int deleteById(int id) throws SQLException;
}
