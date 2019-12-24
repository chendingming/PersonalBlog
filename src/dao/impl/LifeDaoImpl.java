package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bean.Life;
import dao.LifeDao;
import utils.DataSourceUtils;

public class LifeDaoImpl implements LifeDao{

	@Override
	public int add(Life life) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into life values(?,?,?)";
		return runner.update(sql,life.getId(),life.getBody(),life.getDate());
	}

	@Override
	public List<Life> getAll() throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from life order by date desc";
		List<Life> list=runner.query(sql, new BeanListHandler<Life>(Life.class));
		return list;
	}

	@Override
	public int deleteById(int id) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="delete from life where id=?";
		return runner.update(sql,id);
	}

}
