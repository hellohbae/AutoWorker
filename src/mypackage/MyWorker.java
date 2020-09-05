package mypackage;

import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray; 
import org.json.simple.JSONObject;

public class MyWorker {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("연도를 입력하세요 : ");
		String year = scan.nextLine().trim();
		System.out.println("시군구를 입력하세요 : ");
		String location = scan.nextLine().trim();
		
		String strUrl = "http://kosis.kr/openapi/statisticsData.do?"
				+ "method=getList&"
				+ "apiKey=Mjk1OTc0NTcxODE4YmZjZmNiNDU0YzAwNjg2Mjg4NmI=&"
				+ "format=json&jsonVD=Y&"
				+ "userStatsId=wert321/101/DT_1IN1503/2/1/20200811015550&"
				+ "prdSe=Y&startPrdDe=" + year + "&endPrdDe=" + year;
		
		String strUrl2 = "http://kosis.kr/openapi/statisticsData.do?"
				+ "method=getList&"
				+ "apiKey=Mjk1OTc0NTcxODE4YmZjZmNiNDU0YzAwNjg2Mjg4NmI=&"
				+ "format=json&jsonVD=Y&"
				+ "userStatsId=wert321/101/DT_1IN1503/2/1/20200811040145&"
				+ "prdSe=Y&startPrdDe=" + year + "&endPrdDe=" + year;
		
		JSONArray jsonData = KosisApiCall.getKosisData(strUrl);
		JSONArray jsonData2 = KosisApiCall.getKosisData(strUrl2);
		ArrayList<JSONObject> json_obj_list = KosisApiCall.filterJson(jsonData,"C1_NM", location);
		ArrayList<JSONObject> json_obj_list2 = KosisApiCall.filterJson(jsonData2,"C1_NM", location);
		ArrayList<ArrayList<JSONObject>> json_obj_lists = new ArrayList<ArrayList<JSONObject>>();
		json_obj_lists.add(json_obj_list);
		json_obj_lists.add(json_obj_list2);
		
		ExelController ec = new ExelController("abc.xlsx");
		ec.updateExelWithJson("인구산정", json_obj_lists, "C2_NM", "ITM_NM", "DT");
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