package Edu.co.consult.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Edu.co.common.DAO;
import Edu.co.consult.vo.ConVo;
import Edu.co.consult.vo.ReqVo;
import Edu.co.edu.api.GetEduVo;
import Edu.co.edu.vo.EduVo;
import Edu.co.user.vo.UserVo;
import oracle.sql.DATE;

public class ConDao extends DAO {
	private PreparedStatement psmt;
	private ResultSet rs;

	// 상담입력 sql
	private final String UINSERT = "INSERT INTO REQ VALUES(R_SEQ.NEXTVAL, ?, ?, ?, ?,'NO', SYSDATE)";

	private final String CINSERT = "INSERT INTO CON VALUES(C_SEQ.NEXTVAL, ?, ?, ?, ?,'NO', SYSDATE)";

	// 전체조회 sql
	private final String SELECT = "SELECT * FROM (SELECT A.*, ROWNUM RN FROM"
			+ " (SELECT * FROM EDU WHERE AREA='서울' ORDER BY E_NO DESC) A ) B WHERE RN BETWEEN ? AND ? ";

	// 서울 총 레코드 수
	private final String COUNT = "SELECT COUNT(*) FROM EDU";

	// 한건 조회
	private final String SELECT_CON = "SELECT * FROM EDU WHERE NAME=?";
	private final String SELECT_ONE = "SELECT * FROM USER_EDU WHERE NAME=?";

	// 학원조회
	public EduVo selectCon(EduVo vo) {
		try {
			psmt = conn.prepareStatement(SELECT_CON);
			psmt.setString(1, vo.getName());
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo = new EduVo();
				vo.setName(rs.getString("name"));
				vo.setTel(rs.getString("tel"));
				vo.setAddr(rs.getString("addr"));
				vo.setSort(rs.getString("sort"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	// user INSERT (학원 정보를 req에 입력)
	public int uInsert(EduVo vo) {
		int n = 0; // 입력건 선언 validation한다
		try {
			psmt = conn.prepareStatement(UINSERT);
			psmt.setString(1, vo.getName());
			psmt.setString(2, vo.getTel());
			psmt.setString(3, vo.getAddr());
			psmt.setString(4, vo.getSort());

			n = psmt.executeUpdate();
		} catch (SQLException e) { // SQLException하는 catch
			e.printStackTrace();
		} finally {
			close();
		}

		return n; // 총 몇건
	}

	// 학생조회
	public UserVo selectOne(UserVo uo) {
		try {
			psmt = conn.prepareStatement(SELECT_ONE);
			psmt.setString(1, uo.getName());
			rs = psmt.executeQuery();
			if (rs.next()) {
				uo = new UserVo();
				uo.setName(rs.getString("name"));
				uo.setTel(rs.getString("tel"));
				uo.setAddr(rs.getString("addr"));
				uo.setAge(rs.getInt("age"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return uo;
	}

	// client INSERT (상담 정보를 conList에 입력)
	public int cInsert(UserVo vo) {
		int n = 0; // 입력건 선언 validation한다
		try {
			psmt = conn.prepareStatement(CINSERT);
			psmt.setString(1, vo.getName());
			psmt.setString(2, vo.getTel());
			psmt.setString(3, vo.getAddr());
			psmt.setInt(4, vo.getAge());

			n = psmt.executeUpdate();
		} catch (SQLException e) { // SQLException하는 catch
			e.printStackTrace();
		} finally {
			close();
		}

		return n; // 총 몇건
	}

	// 전체조회 : 페이징 처리를 위한 sql / 인라인뷰, rownum 사용
	public ArrayList<ReqVo> selectAllPage(int startRow, int endRow) {
		ArrayList<ReqVo> list = new ArrayList<ReqVo>();
		ReqVo vo;

		try {
			psmt = conn.prepareStatement(SELECT); // 실어보내는것
			psmt.setInt(1, startRow);
			psmt.setInt(2, endRow);
			rs = psmt.executeQuery(); // 보낸명령을 실행시켜달라
			while (rs.next()) {
				vo = new ReqVo(); // 초기화하고
				vo.setR_no(rs.getString("r_NO")); // 값들을 가져와서
				vo.setName(rs.getString("name"));
				vo.setAddr(rs.getString("addr"));
				vo.setSort(rs.getString("sort"));
				vo.setR_date(rs.getDate("r_date"));

				list.add(vo); // 리스트에 담아라
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // finally되면 닫아주는 프로그램 실행 (밑의 메소드 만들어서)
			close();
		}
		return list;
	}

	// 한건 조회
	public ReqVo selectOne(ReqVo vo) {
		try {
			psmt = conn.prepareStatement(SELECT_ONE);
			psmt.setString(1, vo.getR_no());
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo = new ReqVo();
				vo.setR_no(rs.getString("r_no"));
				vo.setR_date(rs.getDate("r_date"));
				vo.setSort(rs.getString("sort"));
				vo.setName(rs.getString("name"));
				vo.setAddr(rs.getString("addr"));
				vo.setTel(rs.getString("tel"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return vo;
	}

	// 총 레코드 수
	public int getCount() {
		int c = 0;
		try {
			psmt = conn.prepareStatement(COUNT);
			rs = psmt.executeQuery();
			if (rs.next()) {
				c = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return c; // 총 레코드 수 리턴
	}

	// 객체닫는처리
	private void close() {
		try {
			if (rs != null)
				rs.close(); // rs객체가 not null이면(열려있다면) 닫아주는 역할
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
