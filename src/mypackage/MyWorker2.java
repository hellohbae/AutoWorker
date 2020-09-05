package mypackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray; 
import org.json.simple.JSONObject;

public class MyWorker2 {
	public static void main(String[] args) {
		//Scanner scan = new Scanner(System.in);
		//System.out.print("연도를 입력하세요 : ");
		//String year = scan.nextLine().trim();
		//System.out.print("시군구를 입력하세요 : ");
		//String location = scan.nextLine().trim();
		
		String strUrl = "http://kosis.kr/openapi/statisticsData.do?method=getMeta&apiKey=Mjk1OTc0NTcxODE4YmZjZmNiNDU0YzAwNjg2Mjg4NmI=&format=json&type=ITM&orgId=101&tblId=DT_1IN1503";

		List<Map<String, Object>> json_data_list = KosisApiCall2.getKosisData(strUrl);
		
		ClassificationValue cv = new ClassificationValue();
		cv.setInfo("서울", 0, 85, json_data_list);
		cv.PrintClass();
		/*
		for(int i=0; i<jsonData.size(); i++) {
			JSONObject jsonObject = (JSONObject) jsonData.get(i); 
			System.out.println(jsonObject.get("ITM_NM"));
		}*/
		
		
		/*
		ArrayList<JSONObject> json_obj_list = KosisApiCall.filterJson(jsonData,"C1_NM", location);
		ArrayList<ArrayList<JSONObject>> json_obj_lists = new ArrayList<ArrayList<JSONObject>>();
		json_obj_lists.add(json_obj_list);
		
		ExelController ec = new ExelController("abc.xlsx");
		ec.updateExelWithJson("인구산정", json_obj_lists, "C2_NM", "ITM_NM", "DT");
		*/
		//ec.getAllExel();
		
		
		
		
		
	}
}
//C:\\Users\\user\\Desktop\\1.의창구-사회적유입전까지 완료\\조성법(의창구)_200723.xlsx

/*
JSONArray personArray = (JSONArray) jsonObj.get("Persons"); 
for(int i=0; i < personArray.size(); i++) { 
	System.out.println("======== person : " + i + " ========"); 
	JSONObject personObject = (JSONObject) personArray.get(i); 
	System.out.println(personObject.get("name")); 
	System.out.println(personObject.get("age")); 
} 
JSONArray booksArray = (JSONArray) jsonObj.get("Books"); 
for(int i=0; i < booksArray.size(); i++) { 
	System.out.println("======== person : " + i + " ========"); 
	JSONObject bookObject = (JSONObject) booksArray.get(i); 
	System.out.println(bookObject.get("name")); 
	System.out.println(bookObject.get("price")); } 
	*/