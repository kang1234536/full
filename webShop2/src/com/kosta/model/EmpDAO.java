package com.kosta.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.kosta.util.DBUtil;
import com.kosta.util.LoggableStatement;


//DAO(Data Access Object)

public class EmpDAO {
	
	public EmpVO loginChk(int empid,String email) {
		EmpVO emp = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where Employee_id=? and email=?"; // 직원의 아이디 가변 ?
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, empid);
			st.setString(2, email);
			rs = st.executeQuery();
			while (rs.next()) {
				emp = makeEmp(rs);// 이문장은 길기에 함수로 뺴놓자! ->makeEmp

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.dbClose(rs, st, conn);
		return emp;
	}
	
	
	
	
	
	
	
	public List<JobVO> selectAllJobs() {
		List<JobVO> joblist = new ArrayList<JobVO>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from jobs order by 1";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				JobVO job = new JobVO(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
				
				joblist.add(job);
				// 이문장은 길기에 함수로 뺴놓자! ->makeEmp
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.dbClose(rs, st, conn);
		return joblist;
	}
	
	
	
	
	//3월 17일 
	//CRUD(Create: insert, Read:select,U:update,D:delete)
	///사용자가 웹을 통해서 회원가입 ->Controller->DAO
	public int insertEmp(EmpVO emp) {
		String sql ="insert into employees values(?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn;
		PreparedStatement st=null;
		int result=0;
		
		conn = DBUtil.getConnection();
		try {
			conn.setAutoCommit(false);  //한번에 여러 sql문을 실행하고자 하는경우 사용
			st=conn.prepareStatement(sql);
			st.setInt(1, emp.getEmployee_id());
			st.setString(2, emp.getFirst_name());
			st.setString(3, emp.getLast_name());
			st.setString(4, emp.getEmail());
			st.setString(5, emp.getPhone_number());
			st.setDate(6, emp.getHire_date());
			st.setString(7, emp.getJob_id());
			st.setInt(8, emp.getSalary());
			st.setDouble(9,emp.getCommission_pct());
			st.setInt(10, emp.getManager_id());
			st.setInt(11, emp.getDepartment_id());
			result =st.executeUpdate(); //insert/update/delete는 executeUpdate()를 써야한다.
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}
	
	
	// 1.모든직원조회
	public List<EmpVO> selectAll() {
		List<EmpVO> emplist = new ArrayList<EmpVO>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from employees";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				emplist.add(makeEmp(rs)); // 이문장은 길기에 함수로 뺴놓자! ->makeEmp
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.dbClose(rs, st, conn);
		return emplist;
	}

	// 2.기본키(유일키):Primary key ... null불가, 필수칼럼,중복없음
	// 직원번호(기본키)로 조회
	public EmpVO selectById(int empid) {
		EmpVO emp = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where Employee_id=?"; // 직원의 아이디 가변 ?
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, empid);
			rs = st.executeQuery();
			while (rs.next()) {
				emp = makeEmp(rs);// 이문장은 길기에 함수로 뺴놓자! ->makeEmp

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.dbClose(rs, st, conn);
		return emp;
	}

	// 3.부서로 조회하기 -> 겹치는 사람이 있다!
	public List<EmpVO> selectByDept(int deptid) {
		List<EmpVO> emplist = new ArrayList<EmpVO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where Department_id=?";
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, deptid);
			rs = st.executeQuery();
			while (rs.next()) {
				emplist.add(makeEmp(rs)); // 이문장은 길기에 함수로 뺴놓자! ->makeEmp
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.dbClose(rs, st, conn);
		return emplist;
	}

	// 4.job_id로 조회
	public List<EmpVO> selectByjobId(String jobid) {
		List<EmpVO> emplist = new ArrayList<EmpVO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where job_id=?";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, jobid);
			rs = st.executeQuery();
			while (rs.next()) {
				emplist.add(makeEmp(rs)); // 이문장은 길기에 함수로 뺴놓자! ->makeEmp
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.dbClose(rs, st, conn);
		return emplist;
	}

	// 5.급여로 조회
	public List<EmpVO> selectBySal(int minsal, int maxsal) {
		List<EmpVO> emplist = new ArrayList<EmpVO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where salary between ? and ?";
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, minsal);
			st.setInt(2, maxsal);
			rs = st.executeQuery();
			while (rs.next()) {
				emplist.add(makeEmp(rs)); // 이문장은 길기에 함수로 뺴놓자! ->makeEmp
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.dbClose(rs, st, conn);
		return emplist;
	}

	// 6.입사일로 조회
	public List<EmpVO> selectHire_Date(String date, String date1) {
		List<EmpVO> emplist = new ArrayList<EmpVO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where hire_date between ? and ?";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, date);
			st.setString(2, date1);
			rs = st.executeQuery();
			while (rs.next()) {
				emplist.add(makeEmp(rs)); // 이문장은 길기에 함수로 뺴놓자! ->makeEmp
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.dbClose(rs, st, conn);
		return emplist;
	}

	// 7.이름에 특정문자가 들어간 사람 조회
	public List<EmpVO> selectSpecialName(String name) {
		List<EmpVO> emplist = new ArrayList<EmpVO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where first_name=initcap(?) ";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, name);
			rs = st.executeQuery();
			while (rs.next()) {
				emplist.add(makeEmp(rs)); // 이문장은 길기에 함수로 뺴놓자! ->makeEmp
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.dbClose(rs, st, conn);
		return emplist;
	}

	// 7-1.이름에 특정문자가 들어간 사람 조회
	public List<EmpVO> selectSpecialName2(String name) {
		List<EmpVO> emplist = new ArrayList<EmpVO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where first_name like ?||'%' ";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, name);
			rs = st.executeQuery();
			while (rs.next()) {
				emplist.add(makeEmp(rs)); // 이문장은 길기에 함수로 뺴놓자! ->makeEmp
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.dbClose(rs, st, conn);
		return emplist;
	}

	// 7-2.이름에 특정문자가 들어간 사람 조회
	public List<EmpVO> selectByName(String ch) {
		List<EmpVO> emplist = new ArrayList<EmpVO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where first_name like ? ";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, "%"+ch+"%");
			rs = st.executeQuery();
			while (rs.next()) {
				emplist.add(makeEmp(rs)); // 이문장은 길기에 함수로 뺴놓자! ->makeEmp
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.dbClose(rs, st, conn);
		return emplist;
	}

	// 8.여러조건으로 조회 (부서,job,salary,hire_date)
	public List<EmpVO> selectByCondition(int deptid,String jobid,int sal,Date hdate) {
		List<EmpVO> emplist = new ArrayList<EmpVO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select *"+
					" from employees"+
					" where department_id=?"+
					" and job_id=?"+
					" and salary>=?"+
					" and hire_date between ? and add_months(?,24)";
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, deptid);
			st.setString(2, jobid);
			st.setInt(3, sal);
			st.setDate(4, hdate);
			st.setDate(5, hdate);
			rs = st.executeQuery();
			while (rs.next()) {
				emplist.add(makeEmp(rs)); // 이문장은 길기에 함수로 뺴놓자! ->makeEmp
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.dbClose(rs, st, conn);
		return emplist;
	}

	// 중요한 함수!!
	private EmpVO makeEmp(ResultSet rs) throws SQLException {
		EmpVO emp = new EmpVO(); // 너무많으므로 default를 만든다
		// new EmpVo("11개")넣는것보다 ->이렇게 하나하나 직접넣는게 best
		emp.setCommission_pct(rs.getDouble("Commission_pct"));
		emp.setDepartment_id(rs.getInt("Department_id"));
		emp.setEmail(rs.getString("Email"));
		emp.setEmployee_id(rs.getInt("Employee_id"));
		emp.setFirst_name(rs.getString("First_name"));
		emp.setHire_date(rs.getDate("Hire_date"));
		emp.setJob_id(rs.getString("Job_id"));
		emp.setLast_name(rs.getString("Last_name"));
		emp.setManager_id(rs.getInt("Manager_id"));
		emp.setPhone_number(rs.getString("Phone_number"));
		emp.setSalary(rs.getInt("Salary"));
		return emp;
	}


	public int updateEmp(EmpVO empid) {
		String sql ="update  employees set first_name=?,last_name=?,salary=?,job_id=?,commission_pct=?,email=?,manager_id=?,phone_number=?,department_id=?,hire_date=? where employee_id=?";
		Connection conn;
		PreparedStatement st=null;
		int result=0;
		
		conn = DBUtil.getConnection();
		try {
			
			st=conn.prepareStatement(sql);
			st.setInt(11,empid.getEmployee_id() );
			st.setString(1, empid.getFirst_name());
			st.setString(2, empid.getLast_name());
			st.setInt(3, empid.getSalary());
			st.setString(4, empid.getJob_id());
			st.setDouble(5, empid.getCommission_pct());
			st.setString(6, empid.getEmail());
			if(empid.getManager_id()==0) {
				st.setNull(7, Types.INTEGER);
			}else {
			st.setInt(7, empid.getManager_id());
			}
			st.setString(8, empid.getPhone_number());
			st.setInt(9, empid.getDepartment_id());
			st.setDate(10, empid.getHire_date());
			
			result =st.executeUpdate(); //insert/update/delete는 executeUpdate()를 써야한다.
		
		} catch (SQLException e) {
				e.printStackTrace();
		}finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}




	public int deleteEmp(int empid) {
		String sql ="delete from  employees where employee_id=?";
		Connection conn;
		PreparedStatement st=null;
		int result=0;
		
		conn = DBUtil.getConnection();
		try {
			
			st=conn.prepareStatement(sql);
			st.setInt(1, empid);
			
			
			result =st.executeUpdate(); //insert/update/delete는 executeUpdate()를 써야한다.
		
		} catch (SQLException e) {
				e.printStackTrace();
		}finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}

}






