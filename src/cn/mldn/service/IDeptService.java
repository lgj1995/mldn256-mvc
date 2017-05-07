package cn.mldn.service;

import java.util.List;

import cn.mldn.vo.Dept;

public interface IDeptService {
	/**
	 * 进行Depe的增加操作
	 * @return
	 * @throws Exception
	 */
	public boolean add(Dept vo) throws  Exception ;
	public boolean edit(Dept vo) throws Exception ;
	public boolean delete(Integer id) throws Exception ;
	public List<Dept> list() throws Exception ;
	
}
