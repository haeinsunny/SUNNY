package Edu.co.edu.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Edu.co.common.DAO;
import Edu.co.edu.api.EduRequest;
import Edu.co.edu.api.GetEduVo;
import Edu.co.edu.vo.EduVo;

public class EduDao extends DAO {
	private PreparedStatement psmt;
	private ResultSet rs;

	// 서울 OpenAPI 입력 sql
	private final String OINSERT = "INSERT INTO SEOUL VALUES(?, ?, ?, ?, '대형', '서울', sysdate)";

	// 서울 List MERGE문
	private final String SMERGE = "MERGE INTO EDU A USING SEOUL B ON (A.e_no = B.e_no)"
			+ " WHEN MATCHED THEN UPDATE SET  A.name = B.name, A.addr = B.addr, A.tel = B.tel, A.e_date = B.e_date"
			+ " WHEN NOT MATCHED THEN INSERT(e_no, name, tel, addr, sort, area, e_date)"
			+ " VALUES (B.e_no, B.name, B.tel, B.addr, B.sort, B.area, B.e_date)";

	// 전체조회 sql
	private final String SELECT_SEOUL = "SELECT * FROM (SELECT A.*, ROWNUM RN FROM"
			+ " (SELECT * FROM EDU WHERE AREA='서울' ORDER BY E_NO DESC) A ) B WHERE RN BETWEEN ? AND ? ";

	// 서울 총 레코드 수
	private final String COUNT = "SELECT COUNT(*) FROM EDU";

	// 분류검색 sql
	private final String SELECT_A = "SELECT * FROM (SELECT A.*, ROWNUM RN FROM"
			+ " (SELECT * FROM EDU WHERE SORT='대형' ORDER BY E_NO DESC) A ) B WHERE RN BETWEEN ? AND ? ";
	private final String SELECT_B = "SELECT * FROM (SELECT A.*, ROWNUM RN FROM"
			+ " (SELECT * FROM EDU WHERE SORT='입시' ORDER BY E_NO DESC) A ) B WHERE RN BETWEEN ? AND ? ";
	private final String SELECT_C = "SELECT * FROM (SELECT A.*, ROWNUM RN FROM"
			+ " (SELECT * FROM EDU WHERE SORT='단과' ORDER BY E_NO DESC) A ) B WHERE RN BETWEEN ? AND ? ";

	// OpenAPI INSERT
	public int insert(ArrayList<GetEduVo> elist) {
		int n = 0, sum = 0; // 입력건 선언 validation한다
		try {
			psmt = conn.prepareStatement(OINSERT);
			for (GetEduVo vo : elist) {
				psmt.setString(1, vo.getID());
				psmt.setString(2, vo.getNM());
				psmt.setString(3, vo.getTEL());
				psmt.setString(4, vo.getADDR());

				n = psmt.executeUpdate(); // executeUpdate메소드는 건수를 돌려준다.
				if (n != 0) {
					sum = sum + n;
				}
			}
		} catch (SQLException e) { // SQLException하는 catch
			e.printStackTrace();
		} finally {
			close();
		}

		return sum; // 총 몇건
	}

	// 서울 MERGE
	public int smerge() {
		int m = 0;
		try {
			psmt = conn.prepareStatement(SMERGE);
			m = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return m;
	}

	// 전체조회 : 페이징 처리를 위한 sql / 인라인뷰, rownum 사용
	public ArrayList<EduVo> selectAllPage(int startRow, int endRow) {
		ArrayList<EduVo> list = new ArrayList<EduVo>();
		EduVo vo;

		try {
			psmt = conn.prepareStatement(SELECT_SEOUL); // 실어보내는것
			psmt.setInt(1, startRow);
			psmt.setInt(2, endRow);
			rs = psmt.executeQuery(); // 보낸명령을 실행시켜달라
			while (rs.next()) {
				vo = new EduVo(); // 초기화하고
				vo.setE_no(rs.getString("E_NO")); // 값들을 가져와서
				vo.setName(rs.getString("NAME"));
				vo.setAddr(rs.getString("ADDR"));
				vo.setSort(rs.getString("SORT"));
				vo.setE_date(rs.getDate("E_DATE"));

				list.add(vo); // 리스트에 담아라
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // finally되면 닫아주는 프로그램 실행 (밑의 메소드 만들어서)
			close();
		}
		return list;
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
