package com.kosta.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kosta.util.DBUtil;
import com.kosta.util.LoggableStatement;


//DAO(Data Access Object)
public class BoardDAO {
	//상세보기
	public BoardVO selectByNo(int boardNo) {
		BoardVO board = null;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from board where Board_seq=?";
		String sql2 = "update board set board_viewcount=nvl(board_viewcount,0)+1 where board_seq=? ";
		
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement(sql);
			st.setLong(1, boardNo);
			rs= st.executeQuery();
			while(rs.next()) {
				board=makeBoard(rs);
				st=conn.prepareStatement(sql2);
				st.setLong(1, boardNo);
				int result = st.executeUpdate();
				System.out.println(result>0?"board_view수정":"board_view수정 실패");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return board;
	}
	
	// 입력
	public int boardInsert(BoardVO board) {

		int result = 0;
		Connection conn = null;
		PreparedStatement st = null;
		// sequence 만들기 create sequence board_autonum;
		//--제약조건 지우기
		//ALTER TABLE BOARD
		//DROP CONSTRAINT BOARD_BOARD_VIEWCOUNT_CHECK;
		String sql = "insert into board values(board_autonum.nextval,?,?,?,sysdate,0,?,?)";

		conn = DBUtil.getConnection();
		try {
			conn.setAutoCommit(false); // 한번에 여러 sql문을 실행하고자 하는경우 사용
			st = conn.prepareStatement(sql);
			st.setString(1, board.getBoard_title());
			st.setString(2, board.getBoard_contents());
			st.setInt(3, board.getBoard_writer());
			st.setString(4, board.getBoard_password());
			st.setString(5, board.getBoard_image());

			result = st.executeUpdate(); // insert/update/delete는 executeUpdate()를 써야한다.
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}

		return result;
	}

	// 전체출력
	public List<BoardVO> selectAll() {
		List<BoardVO> blist = new ArrayList<BoardVO>();
		Connection conn = null;

		PreparedStatement st = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql = "select * from Board";
		try {
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				blist.add(makeBoard(rs)); // 이문장은 길기에 함수로 뺴놓자! ->makeEmp
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}

		return blist;

	}

	// 중요한 함수!!
	private BoardVO makeBoard(ResultSet rs) throws SQLException {
		// ResultSet에서 읽어서 VO객체를 만든다
		BoardVO board = new BoardVO(); // 너무많으므로 default를 만든다
		// new BoardVO("8개")넣는것보다 ->이렇게 하나하나 직접넣는게 best
		board.setBoard_seq(rs.getLong("Board_seq"));
		board.setBoard_title(rs.getString("Board_title"));
		board.setBoard_contents(rs.getString("Board_contents"));
		board.setBoard_writer(rs.getInt("Board_writer"));
		board.setBoard_date(rs.getDate("Board_date"));
		board.setBoard_viewcount(rs.getInt("Board_viewcount"));
		board.setBoard_password(rs.getString("Board_password"));
		board.setBoard_image(rs.getString("Board_image"));

		return board;
	}

	

	public int boardUpdate(BoardVO emp) {
		// update
		Connection conn = null;
		PreparedStatement st = null;
		int result = 0;
		String sql = "update board"+
					" set board_title =? ," +
					" board_contents=?, " +
					" board_date=sysdate, " +
					" board_password=?, " +
					" board_image=? "
				+ " where board_seq=? ";
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement(sql);

			st.setLong(5, emp.getBoard_seq());
			st.setString(1, emp.getBoard_title());
			st.setString(2, emp.getBoard_contents());
			st.setString(3, emp.getBoard_password());
			st.setString(4, emp.getBoard_image());

			result = st.executeUpdate(); // insert/update/delete는 executeUpdate()를 써야한다.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		return result;

	}

	public int boardDelete(int boardNo,String passwd) {
		// delete
		Connection conn=null;
		PreparedStatement st = null;
		int result = 0;
		BoardVO board=selectByNo(boardNo);
		
		String sql = "delete from board where board_seq=? ";
		conn = DBUtil.getConnection();
		try {
			if(!board.getBoard_password().equals(passwd)) throw new SQLException("비밀번호 오류~ 삭제불가");
			st = conn.prepareStatement(sql);
			st.setLong(1, boardNo);
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}

		return result;
	}
	//삭제 2번쨰방법
	public int boardDelete1(int boardNo,String passwd) {
		// delete
		Connection conn=null;
		PreparedStatement st = null;
		int result = 0;
		BoardVO board=selectByNo(boardNo);
		
		String sql = "delete from board where board_seq=? and board_password=? ";
		conn = DBUtil.getConnection();
		try {
			
			st = conn.prepareStatement(sql);
			st.setLong(1, boardNo);
			st.setString(2, passwd);
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}

		return result;
	}

}
