package mypackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClassificationValue {
	private int year;
	private String district_name; //C1
	private int district_code;
	private HashMap<String, Object> age_list; //C2
	
	ClassificationValue(){
		age_list = new HashMap<String, Object>();
	}
	
	public void setInfo(String district_name, int min_age, int max_age, List<Map<String, Object>> json_data_list) {
		for(Map<String, Object> json_data : json_data_list) {
			if(json_data.get("OBJ_ID").toString().equals("A")) {
				if(json_data.get("ITM_NM").toString().contains(district_name)) {
					this.district_name = json_data.get("ITM_NM").toString();
					district_code = Integer.valueOf(json_data.get("ITM_ID").toString());
				}
			}else {
				String age_str = json_data.get("ITM_NM").toString();
				int age_int = 0;
				try {
					age_int = Integer.valueOf(age_str.replaceAll("[^0-9]", ""));
				}catch(NumberFormatException e) {
					continue;
				}
				if(age_int>=min_age && age_int<=max_age) {
					age_list.put(age_str, Integer.valueOf(json_data.get("ITM_ID").toString()));
				}
			}
		}
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void PrintClass() {
		System.out.println(district_name + "(" + district_code + ")");
		Set<Map.Entry<String, Object>> entries = age_list.entrySet();
		for (Map.Entry<String, Object> entry : entries) {
			  System.out.println(entry.getKey() + "(" + entry.getValue().toString() + ")");
		}
	}
	
}
