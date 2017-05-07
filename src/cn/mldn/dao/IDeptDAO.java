package cn.mldn.dao;

import java.sql.SQLException;

import cn.mldn.util.dao.IBaseDAO;
import cn.mldn.vo.Dept;

public interface IDeptDAO extends IBaseDAO<Integer,Dept> {
	/**
	 * 返回最后一次追加数据返回的id号码
	 * 作用：是为了操作Dept.setDeptno()
	 * @return
	 * @throws SQLException
	 */
	public Integer findAutoId() throws SQLException ;
	
}
