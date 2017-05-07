package cn.mldn.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mldn.service.IDeptService;
import cn.mldn.util.factory.Factory;
import cn.mldn.vo.Dept;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//一个servlet的简单分发处理 
@SuppressWarnings("serial")
@WebServlet("/DeptServlet/*") 		//注解的方式完成路径访问
public class DeptServlet extends HttpServlet { 
	public void list (HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 
		System.out.println("*** 【提示信息】部门列表。...");
		IDeptService service = Factory.getServiceInstance("dept.service"); 
		try {
			List<Dept> all = service.list() ;
			JSONObject obj = new JSONObject() ;		
			JSONArray array = new JSONArray() ;
 			Iterator<Dept> iter = all.iterator() ;
			while(iter.hasNext()){ 
				array.add(iter.next()) 	;		//将所有的部门添加JSON数组对象之中 
			} 
			obj.put("alldepts", array) ;		//将JSON数组添加到JSON对象中 
			response.getWriter().println(obj);	//取得客户端字符输出流，向客户端回应请求,其实就是向客户端输出内容  
		} catch (Exception e ) { 
			 
		} 	 
	}
	public void  add (HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		 System.out.println("*****【提示信息】部门增加 *****") ;
		 IDeptService service = Factory.getServiceInstance("dept.service") ; 
		 Dept vo = new Dept() ;
		 vo.setDname(request.getParameter("dname")) ;
		 
		 try {
			service.add(vo) ;		//进行添加操作 
			//向客户端回应信息，vo.getDeptno()==0 表示添加失败
			//vo.getDeptno()不等于表示添加成功 (vo.getDeptno()是最后一次增长号 )
			response.getWriter().print(vo.getDeptno()); 	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 
		  
		 


	}
	//进行部门修改  
	public void edit(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 

		System.out.println("*** 【提示信息】部门修改***");
		IDeptService service = Factory.getServiceInstance("dept.service") ;
		Dept vo = new Dept() ;					//从客户端获取信息 
  		vo.setDeptno(Integer.parseInt(request.getParameter("deptno")));
		vo.setDname(request.getParameter("dname"));
		
		try {
			//并不是向屏幕输出内容，而是向客户端输出内容（就是返回内容，和return的功能一样 ） 
 			response.getWriter().println(service.edit(vo) );	//向客户端回应，返回修改部门是否成功 
 		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	public void delete (HttpServletRequest request, HttpServletResponse response) throws Exception    {
		 System.out.println("*****【提示信息】部门删除 *****") ;
		 IDeptService service = Factory.getServiceInstance("dept.service") ;
		 //将从客户端获取的部门编号格式化为int型 
		 int deptno = Integer.parseInt(request.getParameter("deptno")) ;
 		 System.out.println(deptno);
 		 try {				
			//向客户端回应删除操作的状态 
			response.getWriter().println(service.delete(deptno));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
	}
	public  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8"); 
		String uri = request.getRequestURI();  	//得到客户端的uri地址
		//对客户端发出的地址进行截取 ，取得要调用的方法名称（add、delete、list、edit）
		String methodName = uri.substring(uri.lastIndexOf("/") + 1);  		 
			try {	//其实就是Class.getMethodName(方法名称，String.class,int.class) ;
				Method method =  this.getClass().getMethod(methodName, 
						HttpServletRequest.class, HttpServletResponse.class) ;
				//调用方法传递参数 (其实就是调用了此类的中的方法)
				method.invoke(this, request, response); 		//对象，参数1，参数2 [返回值	]
			 
			} catch ( Exception e) {   
				e.printStackTrace();
			}  		 
	
}	 
 
	 
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
