package service;

import java.sql.SQLException;
import java.util.List;

import bean.Life;
import dao.LifeDao;
import dao.impl.LifeDaoImpl;

public class LifeService {
	LifeDao ld=new LifeDaoImpl();
	public int add(Life life) {
		int i=0;
		try {
			i=ld.add(life);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	public List<Life> getAll() {
		List<Life> list=null;
		try {
			list=ld.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public int deleteById(int id) {
		int i=0;
		try {
			i=ld.deleteById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
}
