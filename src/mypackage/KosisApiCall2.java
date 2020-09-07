package mypackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;



public class KosisApiCall2 {
	public static List<Map<String, Object>> getKosisData(String strUrl) {
		String jsonData = null;
		try {
			URL url = new URL(strUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection(); 
			
			con.setRequestMethod("GET");

			StringBuilder sb = new StringBuilder();
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(con.getInputStream(), "utf-8"));
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line).append("\n");
				}
				br.close();
				jsonData = sb.toString();
			} else {
				System.out.println(con.getResponseMessage());
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		List<Map<String, Object>> readValue = null;
        try {
        	readValue = objectMapper.readValue(jsonData, new TypeReference<List<Map<String, Object>>>() {});
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		return readValue;
	}
	
	public static String buildURL(String api_key, String table_id, String obj1_code, String obj2_code, String item_id, String year) {
		String result_url = "http://kosis.kr/openapi/Param/statisticsParameterData.do?"
				+ "method=getList"
				+ "&apiKey=" + api_key
				+ "&format=json&jsonVD=Y&orgId=101&loadGubun=2"
				+ "&tblId=" + table_id
				+ "&objL1=" + obj1_code
				+ "&objL2=" + obj2_code
				+ "&itmId=" + item_id
				+ "&prdSe=Y&startPrdDe="+year+"&endPrdDe="+year;
		
		return result_url;
	}
	
	//public static 
	
	/*
	public static ArrayList<JSONObject> filterJson(JSONArray jsonArray, String filter_key, String filter_value) {
		ArrayList<JSONObject> resultArray = new ArrayList<JSONObject>();
		for(int i=0; i<jsonArray.size(); i++) {
			JSONObject jsonObject = (JSONObject) jsonArray.get(i); 
			if(jsonObject.get(filter_key).equals(filter_value))
				resultArray.add(jsonObject);
		}
		
		return resultArray;
	}*/
}
