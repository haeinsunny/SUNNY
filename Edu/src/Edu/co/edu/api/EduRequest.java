package Edu.co.edu.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Edu.co.edu.dao.EduDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class EduRequest { 
	
//	public static void Edu() {
//		GetEdu();
//
//		String response = GetEdu();
//
//		JSONObject obj = JSONObject.fromObject(response);
//		JSONObject obj1 = obj.getJSONObject("InstutBuildInfo");
//		JSONArray arr = obj1.getJSONArray("row");
//
//		// 가져온 JSON데이터를 ArrayList에 담기(DB에 넣기 위함)
//		ArrayList<GetEduVo> elist = new ArrayList<GetEduVo>();
//		GetEduVo vo;
//
//		for (int i = 0; i < arr.size(); i++) {
//			JSONObject json = arr.getJSONObject(i);
//			String id = json.getString("ID");
//			int n = id.indexOf('.');
//			vo = new GetEduVo();
//			vo.setID(id.substring(0,n));
//			vo.setNM(json.getString("NM"));
//			vo.setADDR(json.getString("ADDR"));
//			vo.setTEL(json.getString("TEL"));
//
//			elist.add(vo);
//			System.out.println(vo.ID);
//		}
//
//		EduDao dao = new EduDao();
//		int n = dao.insert(elist);
//		System.out.println("총" + n + "건" );
//
//	}
	
	public static String GetEdu() { // 어디에서나 쓸수있도록 get메서드 공용클래스만듬
		StringBuilder sb = new StringBuilder(); // String을 이어줄 빌더 만들고
		String strUrl = "http://openapi.seoul.go.kr:8088/66497a7a48676f643132344a57456141/json/InstutBuildInfo/1/37/";
		try {
			URL url = new URL(strUrl); // 1. URL객체 선언 자바패키지
			HttpURLConnection con = (HttpURLConnection) url.openConnection(); // 2. 서버연결 자바패키지, HttpURLConnection에 서버녕ㄴ결
																				// 정보 담아놓음
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader( // 4. 버퍼리더 생성
						new InputStreamReader(con.getInputStream(), "utf-8")); // 3. 서버연결 후 InputStream():ajax로부터 결과
																				// 받아온것
				String line;
				while ((line = br.readLine()) != null) { // 5. 버퍼리더를 거친것을 sb에 담는다.
					sb.append(line).append("\n");
				}
				br.close(); // 버퍼닫고
				System.out.println("" + sb.toString());
			} else {
				System.out.println(con.getResponseMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb.toString(); // 버퍼안의 스트링값 던져줌

	}
}
