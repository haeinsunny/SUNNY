package co.sunny.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.sunny.board.vo.BoardVo;

public class BoardDao {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // @뒤 : 포트
	private String user = "SUNNY";
	private String password = "1234";

	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	// 생성자 선언
	public BoardDao() {
		try {
			Class.forName(driver); // Class.forName로 드라이버 로드
			conn = DriverManager.getConnection(url, user, password); // conn객체는 DriverManager를 통해서 보낼때(괄호안을)가져온다.
			System.out.println("DB연결 성공");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

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

	// sql작성
	private final String selectAllPage = "SELECT * FROM (SELECT A.*, ROWNUM RN FROM"
			+ "(SELECT * FROM BOARD ORDER BY BNO ASC) A ) B WHERE RN BETWEEN ? AND ? ";
	private final String selectAll = "SELECT * FROM BOARD ORDER BY BNO ASC";
	private final String INSERT = "INSERT INTO BOARD VALUES(bo_seq.nextval, ?, sysdate, ?, 0, ?)";
	private final String COUNT = "SELECT COUNT(*) FROM BOARD";
	private final String HIT_UPDATE = "UPDATE BOARD SET BHIT = BHIT+1 WHERE BNO =?";
	private final String UPDATE = "UPDATE BOARD SET BCONTENT=? WHERE BNO=?";
	private final String DELETE = "DELETE FROM BOARD WHERE BNO=?";
	private final String SELECT_ONE = "SELECT * FROM BOARD WHERE BNO=?";
	
	// 한건조회 및 조회수 증가
	public BoardVo selectOne(BoardVo vo) {
		try {
			psmt = conn.prepareStatement(SELECT_ONE); // 실어보내는것
			psmt.setInt(1, vo.getBno());
			rs = psmt.executeQuery(); // 보낸명령을 실행시켜달라
			if (rs.next()) { // 한레코드일때는 if(전체조회 일때는 while)
				psmt = conn.prepareStatement(HIT_UPDATE); // 조회수 증가
				psmt.setInt(1, vo.getBno());
				psmt.execute(); // 조회수를 1증가한다

				vo = new BoardVo(); // 초기화하고
				vo.setBno(rs.getInt("bno")); // 값들을 가져와서
				vo.setBid(rs.getString("bid"));
				vo.setBtitle(rs.getString("btitle"));
				vo.setBcontent(rs.getString("bcontent"));
				vo.setBdate(rs.getDate("bdate"));
				vo.setBhit(rs.getInt("bhit"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // finally되면 닫아주는 프로그램 실행 (밑의 메소드 만들어서)
			close();
		}

		return vo;
	}
	
	//UPDATE: 레코드수정
	public int update(BoardVo vo) { // 게시글 update
		int n = 0;
		try {
			psmt = conn.prepareStatement(UPDATE); // psmt: conn해서 sql실어보내기
			psmt.setString(1, vo.getBcontent());
			psmt.setInt(2, vo.getBno());
			n = psmt.executeUpdate(); // executeUpdate메소드는 건수를 돌려준다.
		} catch (SQLException e) { // SQLException하는 catch
			e.printStackTrace();
		} finally {
			close();
		}

		return n;
	}
	
	//DELETE: 레코드 삭제
	public int delete(BoardVo vo) {
		int n = 0;
		try {
			psmt = conn.prepareStatement(DELETE); // psmt: conn해서 sql실어보내기
			psmt.setInt(1, vo.getBno());
			n = psmt.executeUpdate(); // executeUpdate메소드는 건수를 돌려준다.
		} catch (SQLException e) { // SQLException하는 catch
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}
	
	// 전체조회 : 페이징 처리를 위한 sql / 인라인뷰, rownum 사용
	public ArrayList<BoardVo> selectAllPage(int startRow, int endRow) {
		ArrayList<BoardVo> list = new ArrayList<BoardVo>();
		BoardVo vo;

		try {
			psmt = conn.prepareStatement(selectAllPage); // 실어보내는것
			psmt.setInt(1, startRow);
			psmt.setInt(2, endRow);
			rs = psmt.executeQuery(); // 보낸명령을 실행시켜달라
			while (rs.next()) {
				vo = new BoardVo(); // 초기화하고
				vo.setBno(Integer.parseInt(rs.getString("BNO"))); // 값들을 가져와서
				vo.setBtitle(rs.getString("BTITLE"));
				vo.setBdate(rs.getDate("BDATE"));
				vo.setBid(rs.getString("Bid"));
				vo.setBhit(Integer.parseInt(rs.getString("BHIT")));

				list.add(vo); // 리스트에 담아라
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // finally되면 닫아주는 프로그램 실행 (밑의 메소드 만들어서)
			close();
		}
		return list;
	}

	// selectAll: 전체조회 
	public ArrayList<BoardVo> selectAll() {
		ArrayList<BoardVo> list = new ArrayList<BoardVo>();
		BoardVo vo;

		try {
			psmt = conn.prepareStatement(selectAll); // 실어보내는것
			rs = psmt.executeQuery(); // 보낸명령을 실행시켜달라
			while (rs.next()) {
				vo = new BoardVo(); // 초기화하고
				vo.setBno(Integer.parseInt(rs.getString("BNO"))); // 값들을 가져와서
				vo.setBtitle(rs.getString("BTITLE"));
				vo.setBdate(rs.getDate("BDATE"));
				vo.setBid(rs.getString("Bid"));
				vo.setBhit(Integer.parseInt(rs.getString("BHIT")));

				list.add(vo); // 리스트에 담아라
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // finally되면 닫아주는 프로그램 실행 (밑의 메소드 만들어서)
			close();
		}
		return list;
	}
	
	// INSERT: 게시글 입력
	public int insert(BoardVo vo) { 
		int n = 0; // 입력건 선언 validation한다
		try {
			psmt = conn.prepareStatement(INSERT);
			psmt.setString(1, vo.getBtitle());
			psmt.setString(2, vo.getBid());
			psmt.setString(3, vo.getBcontent());
			n = psmt.executeUpdate(); // executeUpdate메소드는 건수를 돌려준다.
		} catch (SQLException e) { // SQLException하는 catch
			e.printStackTrace();
		} finally {
			close();
		}

		return n;
	}
	
	// 총 레코드 수
	public int getCount() {
		int n = 0;
		try {
			psmt = conn.prepareStatement(COUNT);
			rs = psmt.executeQuery();
			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n; // 총 레코드 수 리턴
	}

}
