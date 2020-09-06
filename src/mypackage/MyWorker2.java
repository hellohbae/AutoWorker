package mypackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MyWorker2 {
	public static void main(String[] args) {
		
		String strUrl = "http://kosis.kr/openapi/statisticsData.do?method=getMeta&apiKey=Mjk1OTc0NTcxODE4YmZjZmNiNDU0YzAwNjg2Mjg4NmI=&format=json&type=ITM&orgId=101&tblId=DT_1IN1503";

		List<Map<String, Object>> json_data_list = KosisApiCall2.getKosisData(strUrl);
		
		ClassificationValue cv = new ClassificationValue();
		cv.setInfo("서울", 0, 85, json_data_list);
		cv.PrintClassValue();
	}
}