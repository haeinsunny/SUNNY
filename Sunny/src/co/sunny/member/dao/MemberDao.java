package co.sunny.member.dao;  //Board처럼 상속 안받고 쓰는 방식도 있다는걸 보여줌 (보통은 나눠서 하기)

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.sunny.member.vo.MemberVo;

public class MemberDao {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // @뒤 : 포트
	private String user = "SUNNY";
	private String password = "1234";

	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	// 생성자 선언
	public MemberDao() { 
		try {
			Class.forName(driver); // Class.forName로 드라이버 로드
			conn = DriverManager.getConnection(url, user, password); // conn객체는 DriverManager를 통해서 보낼때(괄호안을)가져온다.
			System.out.println("DB연결 성공");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
	
	//객체닫는처리
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
	private String MLOGIN = "SELECT * FROM MEMBER WHERE M_ID=? AND PASSWORD=?"; 
	private final String SELECT_ALL = "SELECT * FROM MEMBER ORDER BY M_ID DESC";
	private final String SELECT_ONE = "SELECT * FROM MEMBER WHERE M_ID=?";
	private final String INSERT = "INSERT INTO MEMBER(M_ID, M_NAME, PASSWORD, M_AUTH)"
			+ "VALUES(?, ?, ?, ?)";
	private final String UPDATE = "UPDATE MEMBER SET PASSWORD=? WHERE M_AUTH=?";
	private final String DELETE = "DELETE FROM MEMBER WHERE M_ID=?";
	private final String JOIN = "INSERT INTO MEMBER(M_ID, M_NAME, PASSWORD, M_AUTH)" + 
								"VALUES(?, ?, ?, 'user')";	
	
	//전체조회
	public ArrayList<MemberVo> selectAll() {
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		MemberVo vo;
		try {
			psmt = conn.prepareStatement(SELECT_ALL); // 실어보내는것
			rs = psmt.executeQuery(); // 보낸명령을 실행시켜달라
			while (rs.next()) {
				vo = new MemberVo(); // 초기화하고
				vo.setmId(rs.getString("M_ID")); // 값들을 가져와서
				vo.setmName(rs.getString("M_NAME"));
				vo.setPassword(rs.getString("PASSWORD"));
				vo.setmAuth(rs.getString("M_AUTH"));
				vo.setmPoint(rs.getInt("M_POINT"));
				list.add(vo); // 리스트에 담아라
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // finally되면 닫아주는 프로그램 실행 (밑의 메소드 만들어서)
			close();
		}
		return list;
	}

	//특정조회
	public MemberVo selectOne(MemberVo vo) {
		try {
			psmt = conn.prepareStatement(SELECT_ONE);
			psmt.setString(1, vo.getmId());
			rs = psmt.executeQuery(); 
			if (rs.next()) { 
				vo = new MemberVo(); 
				vo.setmId(rs.getString("M_ID")); 
				vo.setmName(rs.getString("M_NAME"));
				vo.setPassword(rs.getString("PASSWORD"));
				vo.setmAuth(rs.getString("M_AUTH"));
				vo.setmPoint(rs.getInt("M_POINT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // finally되면 닫아주는 프로그램 실행 (밑의 메소드 만들어서)
			close();
		}

		return vo;
	}
	
	//로그인체크
	public MemberVo mLoginCheck(MemberVo vo) { 
		try {
			psmt = conn.prepareStatement(MLOGIN);	
			psmt.setString(1, vo.getmId());
			psmt.setString(2, vo.getPassword());
			rs = psmt.executeQuery();	
			if(rs.next())	{
				vo.setmName(rs.getString("M_NAME"));  //getString("M_NAME"):DB에서 가져오는 컬럼	
				vo.setmAuth(rs.getString("M_AUTH"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close();
		}
		return vo;
	}
	
	//입력
	public int insert(MemberVo vo) {
		int n = 0;	
		try {
			psmt = conn.prepareStatement(INSERT);
			psmt.setString(1, vo.getmId());
			psmt.setString(2, vo.getmName());
			psmt.setString(3, vo.getPassword());
			psmt.setString(4, vo.getmAuth());
			n = psmt.executeUpdate(); // executeUpdate메소드는 건수를 돌려준다.
		} catch (SQLException e) { // SQLException하는 catch
			e.printStackTrace();
		} finally {
			close();
		}

		return n;
	}
	
	//수정
	public int update(MemberVo vo) {
		int n = 0;	
		try {
			psmt = conn.prepareStatement(UPDATE); // psmt: conn해서 sql실어보내기			
			psmt.setString(1, vo.getPassword());
			psmt.setString(2, vo.getmAuth());
			n = psmt.executeUpdate(); // executeUpdate메소드는 건수를 돌려준다.
		} catch (SQLException e) { // SQLException하는 catch
			e.printStackTrace();
		} finally {
			close();
		}

		return n;
	}
		
	//삭제
	public int delete(MemberVo vo) {
		int n = 0;	
		try {
			psmt = conn.prepareStatement(DELETE); // psmt: conn해서 sql실어보내기
			psmt.setString(1, vo.getmId());
			n = psmt.executeUpdate(); // executeUpdate메소드는 건수를 돌려준다.
		} catch (SQLException e) { // SQLException하는 catch
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}
	
	//회원가입
	public int join(MemberVo vo) {
		int n = 0;	
		try {
			psmt = conn.prepareStatement(JOIN);
			psmt.setString(1, vo.getmId());
			psmt.setString(2, vo.getmName());
			psmt.setString(3, vo.getPassword());
			n = psmt.executeUpdate(); // executeUpdate메소드는 건수를 돌려준다.
		} catch (SQLException e) { // SQLException하는 catch
			e.printStackTrace();
		} finally {
			close();
		}

		return n;
	}
	
//	//중복체크
//	public String checkId(String id) {
//		String name = null;
//		List<MemberVo> list = new ArrayList<>();
//		try {
//			psmt = conn.prepareStatement(SELECT_ONE);
//			psmt.setString(1, id);
//			rs = psmt.executeQuery(); 
//			if (rs.next()) { 				
//				name = rs.getString("M_NAME");
//				list.add(name);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally { // finally되면 닫아주는 프로그램 실행 (밑의 메소드 만들어서)
//			close();
//		}
//
//		return name;
//	}
	
	//특정조회
		public List<MemberVo> checkId(MemberVo vo) {
			List<MemberVo> list = new ArrayList<>();
			try {
				psmt = conn.prepareStatement(SELECT_ONE);
				psmt.setString(1, vo.getmId());
				rs = psmt.executeQuery(); 
				if (rs.next()) { 
					vo = new MemberVo(); 
					vo.setmId(rs.getString("M_ID")); 
					vo.setmName(rs.getString("M_NAME"));
					vo.setPassword(rs.getString("PASSWORD"));
					vo.setmAuth(rs.getString("M_AUTH"));
					vo.setmPoint(rs.getInt("M_POINT"));
					
					list.add(vo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally { // finally되면 닫아주는 프로그램 실행 (밑의 메소드 만들어서)
				close();
			}

			return list;
		}
	
}

















