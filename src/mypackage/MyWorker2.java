package mypackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MyWorker2 {
	public static void main(String[] args) {
		
		String api_key = "Mjk1OTc0NTcxODE4YmZjZmNiNDU0YzAwNjg2Mjg4NmI=";
		
		String strUrl = "http://kosis.kr/openapi/statisticsData.do?method=getMeta&apiKey=Mjk1OTc0NTcxODE4YmZjZmNiNDU0YzAwNjg2Mjg4NmI=&format=json&type=ITM&orgId=101&tblId=DT_1IN1503";
		
		int year = 2018;

		List<Map<String, Object>> json_data_list = KosisApiCall2.getKosisData(strUrl);
		
		ClassificationValue cv = new ClassificationValue();
		cv.setInfo("서울", 0, 85, json_data_list);
		cv.setYear(year);
		cv.PrintClassValue();
		
		strUrl = KosisApiCall2.buildURL(api_key, "DT_1IN1503", "11" , "065003", "T01", "2018");
		
		//System.out.println(strUrl);
		
		List<Map<String, Object>> temp = KosisApiCall2.getKosisData(strUrl);
		
		
		for(Map<String, Object> t1 : temp)
			System.out.println(t1.get("C1_NM"));
	}
}