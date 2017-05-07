package cn.mldn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.mldn.dao.IDeptDAO;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.vo.Dept;

public class DeptDAOImpl extends AbstractDAO implements IDeptDAO {

	@Override
	public boolean doCreate(Dept vo) throws SQLException {
		String sql = "INSERT INTO dept(dname) VALUES(?)" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getDname());
		return super.pstmt.executeUpdate() > 0 ;
		
	}

	@Override
	public boolean doUpdate(Dept vo) throws SQLException {
		String sql = "UPDATE dept SET dname = ? WHERE deptno = ? " ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getDname());
		super.pstmt.setInt(2, vo.getDeptno());
		return super.pstmt.executeUpdate() > 0 ;
		
	}

	@Override
	public boolean doRemove(Integer id) throws SQLException {
		String sql = "DELETE FROM dept WHERE deptno = ?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, id);
		return super.pstmt.executeUpdate() > 0 ;
		
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
		throw new SQLException("未实现的方法") ;
	}

	@Override
	public Dept findById(Integer id) throws SQLException {
		throw new SQLException("未实现的方法") ;

	}

	@Override
	public List<Dept> findAll() throws SQLException {
		List<Dept> all = new ArrayList<Dept>() ;
		String sql = "SELECT deptno,dname FROM dept" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		while(rs.next()){
			Dept vo = new Dept() ;
			vo.setDeptno(rs.getInt(1));
			vo.setDname(rs.getString(2));
			all.add(vo) ;
		}
		return all ;
	}

	@Override
	public List<Dept> findAllSplit(Integer currentPage, Integer lineSize) throws SQLException {
		throw new SQLException("未实现的方法") ;

	}

	@Override
	public List<Dept> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws SQLException {
		throw new SQLException("未实现的方法") ;

	}

	@Override
	public Integer getAllCount() throws SQLException {
		throw new SQLException("未实现的方法") ;

	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws SQLException {
		throw new SQLException("未实现的方法") ;

	}

	@Override
	public Integer findAutoId() throws SQLException {
		 String sql = " SELECT last_insert_id() " ; 
		 super.pstmt = super.conn.prepareStatement(sql) ;
		 ResultSet rs = super.pstmt.executeQuery() ;
		 while(rs.next()){ 
			 return rs.getInt(1)	;			//返回最后一次增长的id
		 }
		 return 0 ;
	}

}
