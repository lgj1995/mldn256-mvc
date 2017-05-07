package cn.mldn.service.impl;

import java.util.List;

import cn.mldn.dao.IDeptDAO;
import cn.mldn.service.IDeptService;
import cn.mldn.util.factory.Factory;
import cn.mldn.vo.Dept;

public class DeptServiceImpl implements IDeptService {

	@Override
	public boolean add(Dept vo) throws Exception {
		IDeptDAO dao = Factory.getDAOInstance("dept.dao") ;
		if(dao.doCreate(vo)){
			vo.setDeptno(dao.findAutoId());	//设置deptno
			return true ;
		}
		vo.setDeptno(0);  //deptno=0表示添加失败 
		return false ;
		
	}

	@Override
	public boolean edit(Dept vo) throws Exception {
		IDeptDAO dao = Factory.getDAOInstance("dept.dao") ;
		return dao.doUpdate(vo) ;
	}

	@Override
	public boolean delete(Integer id) throws Exception {
		IDeptDAO dao = Factory.getDAOInstance("dept.dao") ;
		return dao.doRemove(id);

	}

	@Override
	public List<Dept> list() throws Exception {
		IDeptDAO dao = Factory.getDAOInstance("dept.dao") ;
		return dao.findAll() ; 
	}

}
