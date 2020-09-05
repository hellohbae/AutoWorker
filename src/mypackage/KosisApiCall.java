package mypackage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class KosisApiCall {
	public static JSONArray getKosisData(String strUrl) {
		String jsonData = null;
		try {
			URL url = new URL(strUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection(); 
			
			con.setRequestMethod("GET");

			StringBuilder sb = new StringBuilder();
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				//Stream을 처리해줘야 하는 귀찮음이 있음. 
				BufferedReader br = new BufferedReader(
						new InputStreamReader(con.getInputStream(), "utf-8"));
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line).append("\n");
				}
				br.close();
				jsonData = sb.toString();
				//System.out.println("" + sb.toString());
			} else {
				System.out.println(con.getResponseMessage());
			}

		} catch (Exception e) {
			System.err.println(e.toString());
		}
		
		JSONArray jsonArray = null;
		try { 
			JSONParser jsonParse = new JSONParser(); 
			System.out.println(jsonData);
			jsonArray = (JSONArray) jsonParse.parse(jsonData); 		
			
		} catch (ParseException e) { 
			e.printStackTrace(); 
		}
		
		return jsonArray;
	}
	
	public static ArrayList<JSONObject> filterJson(JSONArray jsonArray, String filter_key, String filter_value) {
		ArrayList<JSONObject> resultArray = new ArrayList<JSONObject>();
		for(int i=0; i<jsonArray.size(); i++) {
			JSONObject jsonObject = (JSONObject) jsonArray.get(i); 
			if(jsonObject.get(filter_key).equals(filter_value))
				resultArray.add(jsonObject);
		}
		
		return resultArray;
	}
}
