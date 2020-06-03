package com.sa.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

public class ASame {
	public static void main(String[] args) {
		cycle();
	}

	public static String cycle(){
		String fileContent = readFile("C:\\Users\\jackchen\\Desktop\\513LOG.txt");
		
		String[] split = fileContent.split("============================ ");

		List<String> list = new ArrayList<>();
		HashMap<String, Integer> map = new HashMap<>();
		for (int i = 1; i < split.length; i++) {
				String newStr = split[i];
				int sindex = newStr.indexOf("options");
				int eindex = newStr.indexOf("packetHead");
				if(sindex!=-1&&eindex!=-1){
					String ostr = newStr.substring(sindex, eindex);
					if(!map.containsKey(ostr)){
						map.put(ostr, 1);
					}else {
						map.put(ostr, map.get(ostr)+1);
					}
				}
		}
		
		
		Set<Entry<String,Integer>> entrySet = map.entrySet();
		System.out.println(entrySet.size());
		for (Entry<String, Integer> entry : entrySet) {
			System.out.println(entry.getValue()+"=="+entry.getKey());
		}
		return null;
		
	}
	
/*	public static String readFile(String Path) {
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
	}*/
	public static String readFile(String Path) {
		BufferedReader reader = null;
		String laststr = "";
		try {
			FileInputStream fileInputStream = new FileInputStream(Path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr += tempString;
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
