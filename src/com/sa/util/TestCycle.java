package com.sa.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class TestCycle {

	public static void main(String[] args) {
		cycle();
	}

	public static String cycle(){
		String fileContent = readFile("C:\\Users\\jackchen\\Desktop\\415\\169center.log");
		String[] split = fileContent.split("============================================");

		List<String> list = new ArrayList<>();
		HashMap<String, Integer> map = new HashMap<>();
		for (int i = 1; i < split.length; i++) {
				String newStr = split[i];
				int indexOf = newStr.indexOf("MSG");
				String ostr = newStr.substring(indexOf, newStr.length());
				if(list.contains(ostr)){
					Integer c = map.get("ostr");
					if(null==c){
						map.put(ostr, 1);	
					}else{
						map.put(ostr, c++);
					}
				}
				list.add(ostr);
		}
		
		Set<Entry<String,Integer>> entrySet = map.entrySet();
		for (Entry<String, Integer> entry : entrySet) {
			System.out.println(entry.getKey()+"=="+entry.getValue());
		}
		return null;
		
	}
	
	public static String readFile(String Path) {
		BufferedReader reader = null;
		String laststr = "";
		try {
			FileInputStream fileInputStream = new FileInputStream(Path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "gbk");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			int index = -1;
			int count =0;
			while ((tempString = reader.readLine()) != null) {
				if(tempString.equals("============================================")){
					count++;
				}
				if(!tempString.equals("============================================")&&(count%2==0)){
					tempString+=tempString;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return laststr;
	}

}
