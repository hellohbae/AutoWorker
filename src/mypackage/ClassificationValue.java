package mypackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class ClassificationValue {
	private int year;
	private String district_name; //C1
	private int district_code;
	private LinkedHashMap<String, String> age_list; //C2
	
	ClassificationValue(){
		age_list = new LinkedHashMap<String, String>();
	}
	
	public void setInfo(String district_name, int min_age, int max_age, List<Map<String, Object>> json_data_list) {
		HashMap<String, String> unsorted_age_list = new HashMap<String, String>();
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
					if(age_str.contains("~"))
						age_int = Integer.valueOf(age_str.split("~")[0].replaceAll("[^0-9]", ""));
				}catch(NumberFormatException e) {
					continue;
				}
				if(age_int>=min_age && age_int<=max_age) {
					unsorted_age_list.put(age_str, json_data.get("ITM_ID").toString());
				}
			}
		}
		
		
		for(int i=min_age; i<max_age; i++) {
			try {
				String value = unsorted_age_list.get(i+"세");
			}catch(NullPointerException e) {
				continue;
			}
			age_list.put(i+"세",unsorted_age_list.get(i+"세"));	
		}
		try {
			String value = unsorted_age_list.get(max_age+"세이상");
		}catch(NullPointerException e) {
		}
		age_list.put(max_age+"세이상",unsorted_age_list.get(max_age+"세이상"));	
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void PrintClassValue() {
		System.out.println(district_name + "(" + district_code + ")");	
		Set<Entry<String, String>> entries = age_list.entrySet();
		for (Entry<String, String> entry : entries) {
			  System.out.println(entry.getKey() + "(" + entry.getValue().toString() + ")");
		}
	}
	
}


//
