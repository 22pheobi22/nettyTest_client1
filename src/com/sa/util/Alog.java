package com.sa.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Alog {

	public static void main(String[] args) {
		String fileContent = readFile("C:\\Users\\jackchen\\Documents\\WeChat Files\\Mrs_Phelps\\FileStorage\\File\\2020-04\\netty_2020-04-22.log");
	}

	public static String readFile(String Path) {
		BufferedReader reader = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(Path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			String conStr = null;
			List<Map<String, String>> list = new ArrayList<>();
			int index = -1;
			Map<String, String> map = null;
			Map<String, Object> map2 = new HashMap<>();

			while ((tempString = reader.readLine()) != null) {
				if (tempString.equals("MSG TYPE:ServerLogin") || tempString.equals("MSG TYPE:ClientLogin")
						|| (index < 3 && index > -1)) {
					conStr += tempString;
					index++;
				}
				if (index == 3) {
					map = new HashMap<>();
					int rt = conStr.indexOf("TRANSACTION ID")+15;
					int rs = conStr.indexOf("ROOM ID");
					int re = conStr.indexOf("FROM USER ID");
					String tr = conStr.substring(rt,rs).trim();
					String roomId = conStr.substring(rs+8, re).trim();
					String userId = conStr.substring(re + 13, conStr.length()).trim();

					if (map2.containsKey(tr+"&"+userId+"&"+roomId)) {
						Map innerMap = (Map)map2.get(tr+"&"+userId+"&"+roomId);
						Integer c= Integer.parseInt(innerMap.get("count").toString())+1;
						innerMap.put("count", c.toString());
					}else {
						map.put("tr", tr);
						map.put("roomId", roomId);
						map.put("userId", userId);
						map.put("count", "1");
						map2.put(tr+"&"+userId+"&"+roomId, map);
					}
					map=null;
					index = -1;
					conStr = null;
				}
			}
			Set<Entry<String,Object>> entrySet = map2.entrySet();
			for (Entry<String, Object> entry : entrySet) {
				Map me = (Map)entry.getValue();
				System.out.println(me.toString());
				if(Integer.valueOf(me.get("count").toString())<3&&!me.get("userId").equals("0")){
					System.out.println(me.toString());
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
		return "";
	}
}
