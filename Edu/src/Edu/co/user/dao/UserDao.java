package Edu.co.user.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Edu.co.common.DAO;
import Edu.co.user.vo.UserVo;

public class UserDao extends DAO {
	private PreparedStatement psmt;
	private ResultSet rs;

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

	// 이부분에 sql작성 (기본적인 형식부터 작성해놓자.) sql문은 public으로 작성한다.
	private String LOGIN = "SELECT * FROM USER_EDU WHERE ID=? AND PW=?";
	private final String SELECT_ALL = "SELECT * FROM USER_EDU ORDER BY NO DESC";
	private final String SELECT_ONE = "SELECT * FROM USER_EDU WHERE ID=?";
	private final String INSERT = "INSERT INTO USER_EDU(NO, TYPE, NAME, AGE, TEL, ADDR, SORT, ID, PW)"
			+ "VALUES(U_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final String UPDATE = "UPDATE USER_EDU SET PASSWORD=? WHERE ID=?";
	private final String DELETE = "DELETE FROM USER_EDU WHERE ID=?";
	private final String EINSERT = "INSERT INTO EDU(E_NO, NAME, TEL, ADDR, SORT, AREA, E_DATE, NO)"
			+ "VALUES(E_SEQ.NEXTVAL, ?, ?, ?, ?, '서울', SYSDATE, ?)";

	// 전체조회
	public ArrayList<UserVo> selectAll() {
		ArrayList<UserVo> list = new ArrayList<UserVo>();
		UserVo vo;
		try {
			psmt = conn.prepareStatement(SELECT_ALL); // 실어보내는것
			rs = psmt.executeQuery(); // 보낸명령을 실행시켜달라
			while (rs.next()) {
				vo = new UserVo(); // 초기화하고
				vo.setNo(rs.getString("NO"));
				vo.setType(rs.getString("TYPE"));
				vo.setName(rs.getString("NAME"));
				vo.setAge(Integer.parseInt(rs.getString("age")));
				vo.setTel(rs.getString("tel"));
				vo.setAddr(rs.getString("ADDR"));
				vo.setSort(rs.getString("sort"));
				vo.setId(rs.getString("ID"));
				vo.setPw(rs.getString("pw"));

				list.add(vo); // 리스트에 담아라
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // finally되면 닫아주는 프로그램 실행 (밑의 메소드 만들어서)
			close();
		}
		return list;
	}

	// 특정조회
	public UserVo selectOne(UserVo vo) {
		try {
			psmt = conn.prepareStatement(SELECT_ONE);
			psmt.setString(1, vo.getId());
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo.setNo(rs.getString("NO"));
				vo.setType(rs.getString("TYPE"));
				vo.setName(rs.getString("NAME"));
				vo.setAge(rs.getInt("AGE"));
				vo.setTel(rs.getString("TEL"));
				vo.setAddr(rs.getString("ADDR"));
				vo.setSort(rs.getString("SORT"));
				vo.setId(rs.getString("ID"));
				vo.setPw(rs.getString("PW"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // finally되면 닫아주는 프로그램 실행 (밑의 메소드 만들어서)
			close();
		}

		return vo;
	}

	// 로그인정보
	public UserVo CheckId(UserVo vo) {
		try {
			psmt = conn.prepareStatement(LOGIN);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPw());
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo.setId(rs.getString("ID"));
				vo.setName(rs.getString("NAME")); // getString("NAME"):DB에서 가져오는 컬럼
				vo.setType(rs.getString("TYPE"));
			} else {
				vo.setId(null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	// 회원가입
	public int insert(UserVo vo) {
		int n = 0;
		try {
			psmt = conn.prepareStatement(INSERT);
			psmt.setString(1, vo.getType());
			psmt.setString(2, vo.getName());
			psmt.setInt(3, vo.getAge());
			psmt.setString(4, vo.getTel());
			psmt.setString(5, vo.getAddr());
			psmt.setString(6, vo.getSort());
			psmt.setString(7, vo.getId());
			psmt.setString(8, vo.getPw());

			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return n;
	}

	// 학원등록
	public int eInsert(UserVo vo) {
		int n = 0;
		try {
			psmt = conn.prepareStatement(EINSERT);
			psmt.setString(1, vo.getName());
			psmt.setString(2, vo.getTel());
			psmt.setString(3, vo.getAddr());
			psmt.setString(4, vo.getSort());
			psmt.setString(5, vo.getNo());

			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return n;
	}

	// 비번수정
	public int update(UserVo vo) {
		int n = 0;
		try {
			psmt = conn.prepareStatement(UPDATE); // psmt: conn해서 sql실어보내기
			psmt.setString(1, vo.getPw());
			psmt.setString(2, vo.getId());
			n = psmt.executeUpdate(); // executeUpdate메소드는 건수를 돌려준다.
		} catch (SQLException e) { // SQLException하는 catch
			e.printStackTrace();
		} finally {
			close();
		}

		return n;
	}

	// 회원탈퇴
	public int delete(UserVo vo) {
		int n = 0;
		try {
			psmt = conn.prepareStatement(DELETE); // psmt: conn해서 sql실어보내기
			psmt.setString(1, vo.getId());
			n = psmt.executeUpdate(); // executeUpdate메소드는 건수를 돌려준다.
		} catch (SQLException e) { // SQLException하는 catch
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	// 로그인 중복 조회
	public List<UserVo> checkId(UserVo vo) {
		List<UserVo> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(SELECT_ONE);
			psmt.setString(1, vo.getId());
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo = new UserVo();
				vo.setId(rs.getString("ID"));
				vo.setName(rs.getString("NAME"));
				vo.setPw(rs.getString("PW"));

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // finally되면 닫아주는 프로그램 실행 (밑의 메소드 만들어서)
			close();
		}

		return list;
	}

//		//중복체크
//		public String checkId(String id) {
//			String name = null;
//			List<MemberVo> list = new ArrayList<>();
//			try {
//				psmt = conn.prepareStatement(SELECT_ONE);
//				psmt.setString(1, id);
//				rs = psmt.executeQuery(); 
//				if (rs.next()) { 				
//					name = rs.getString("M_NAME");
//					list.add(name);
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} finally { // finally되면 닫아주는 프로그램 실행 (밑의 메소드 만들어서)
//				close();
//			}
	//
//			return name;
//		}

}
