package com.kosta.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kosta.util.DBUtil;
import com.kosta.util.LoggableStatement;


public class DeptDAO {
	
	public List<ManagerVO> selectAllManager() {
		List<ManagerVO> mlist = new ArrayList<ManagerVO>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		String sql = "select employee_id, first_name||last_name fullname\r\n"
				+ " from employees\r\n"
				+ " where employee_id in(select distinct manager_id from employees) ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
//				ManagerVO vo=new ManagerVO(rs.getInt(1),rs.getString(2));
				ManagerVO vo=new ManagerVO();
				vo.setManager_id(rs.getInt(1));
				vo.setFullname(rs.getString(2));
			
				mlist.add(vo); // 이문장은 길기에 함수로 뺴놓자! ->makeEmp
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.dbClose(rs, st, conn);
		return mlist;
	}
	
	
	public List<LocationVO> selectAllLocation() {
		List<LocationVO> loclist = new ArrayList<LocationVO>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from locations ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				LocationVO vo=new LocationVO();
				vo.setLocation_id(rs.getInt(1));
				vo.setCity(rs.getString("city"));
			
				loclist.add(vo); // 이문장은 길기에 함수로 뺴놓자! ->makeEmp
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.dbClose(rs, st, conn);
		return loclist;
	}
	
	public int insertDept(DeptVO dept) {
		String sql ="insert into departments values(?,?,?,?)";
		Connection conn;
		PreparedStatement st=null;
		int result=0;
		
		conn = DBUtil.getConnection();
		try {
			
			st=conn.prepareStatement(sql);
			st.setInt(1, dept.getDepartment_id());
			st.setString(2, dept.getDepartment_name());
			st.setInt(3, dept.getManager_id());
			st.setInt(4, dept.getLocation_id());
			
			result =st.executeUpdate(); //insert/update/delete는 executeUpdate()를 써야한다.
		
		} catch (SQLException e) {
				e.printStackTrace();
		}finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}
	public List<DeptVO> selectAll() {
		List<DeptVO> deptlist = new ArrayList<DeptVO>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from departments ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				deptlist.add(makeDept(rs)); // 이문장은 길기에 함수로 뺴놓자! ->makeEmp
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.dbClose(rs, st, conn);
		return deptlist;
	}
	private DeptVO makeDept(ResultSet rs) throws SQLException {
		DeptVO dept = new DeptVO(); 		
		dept.setDepartment_id(rs.getInt("Department_id"));
		dept.setDepartment_name(rs.getString("Department_name"));
		dept.setManager_id(rs.getInt("Manager_id"));
		dept.setLocation_id(rs.getInt("Location_id"));
		return dept;
	}
	public DeptVO selectById(int deptid) {
		DeptVO dao = null;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from departments where department_id=?";
		
		
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement(sql);
			st.setLong(1, deptid);
			rs= st.executeQuery();
			while(rs.next()) {
				dao=makeDept(rs);
				
				
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return dao;
	}
	public int updateDept(DeptVO dept) {
		String sql ="update  departments set Department_name=?,Manager_id=?,Location_id=? where Department_id=?";
		Connection conn;
		PreparedStatement st=null;
		int result=0;
		
		conn = DBUtil.getConnection();
		try {
			
			st=conn.prepareStatement(sql);
			st.setInt(4, dept.getDepartment_id());
			st.setString(1, dept.getDepartment_name());
			st.setInt(2, dept.getManager_id());
			st.setInt(3, dept.getLocation_id());
			
			result =st.executeUpdate(); //insert/update/delete는 executeUpdate()를 써야한다.
		
		} catch (SQLException e) {
				e.printStackTrace();
		}finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}
	public int deleteDept(int deptid) {
		String sql ="delete from  departments where Department_id=?";
		Connection conn;
		PreparedStatement st=null;
		int result=0;
		
		conn = DBUtil.getConnection();
		try {
			
			st=conn.prepareStatement(sql);
			st.setInt(1, deptid);
			
			
			result =st.executeUpdate(); //insert/update/delete는 executeUpdate()를 써야한다.
		
		} catch (SQLException e) {
				e.printStackTrace();
		}finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}
}
