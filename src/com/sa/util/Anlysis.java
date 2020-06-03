package com.sa.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javax.sound.midi.Soundbank;

import org.apache.http.client.utils.DateUtils;

import javafx.scene.shape.SVGPath;
import sun.print.resources.serviceui;

public class Anlysis {
	
	public static void main(String[] args) {
		//String fileContent = readFile("C:\\Users\\jackchen\\Desktop\\0317\\7-1-20200317.log");
		String fileContent = readFile("C:\\Users\\jackchen\\Desktop\\netty_2020-04-22.log");
		String[] split = fileContent.split("============================================");
		List<String> recClientLoginlist = new ArrayList<>();
		List<String> resClientLoginlist = new ArrayList<>();
		List<String> resServerLoginlist = new ArrayList<>();

		Map<String,Long> slmap = new HashMap<>();
		Map<String,Long> clmap = new HashMap<>();
		for (int i = 1; i < split.length; i++) {
				String newStr = split[i];
				if(newStr.contains("ClientLogin")){
					int indexStart = newStr.indexOf("1=房");
					int indexEnd = newStr.indexOf("2=3");
					String sequence = newStr.subSequence(indexStart, indexEnd).toString();
					if(newStr.contains("接收")){
						if(recClientLoginlist.contains(sequence)){
							System.out.println("recClientLogin重复用户:"+sequence);
						}
						recClientLoginlist.add(sequence);
					}
					if(newStr.contains("发送")){
						int timeStart = newStr.indexOf("发送");
						int timeEnd = newStr.indexOf("MSG");
						String time = newStr.subSequence(timeStart+2, timeEnd).toString();
						long c=0l;
						try {
							c = DateUtil.parse(time, "yyyy-MM-dd HH:mm:ss.SSS").getTime();
						} catch (ParseException e) {
							e.printStackTrace();
						}
						if(resClientLoginlist.contains(sequence)){
							System.out.println("resClientLogin重复用户:"+sequence);
						}
						resClientLoginlist.add(sequence);
						clmap.put(sequence, c);
					}
				}
				
				if(newStr.contains("ServerLogin")){
					int indexStart = newStr.indexOf("1=房");
					int indexEnd = newStr.indexOf("2=3");
					String sequence = newStr.subSequence(indexStart, indexEnd).toString();
					if(newStr.contains("接收")){
						int timeStart = newStr.indexOf("接收");
						int timeEnd = newStr.indexOf("MSG");
						String time = newStr.subSequence(timeStart+2, timeEnd).toString();
						long c=0l;
						try {
							c = DateUtil.parse(time, "yyyy-MM-dd HH:mm:ss.SSS").getTime();
						} catch (ParseException e) {
							e.printStackTrace();
						}

						if(resServerLoginlist.contains(sequence)){
							System.out.println("ServerLogin重复用户:"+sequence);
						}
						resServerLoginlist.add(sequence);
						slmap.put(sequence, c);
					}
				}
		}
		System.out.println("recClientLoginlist size:"+recClientLoginlist.size());
		System.out.println("resClientLoginlist size:"+resClientLoginlist.size());
		System.out.println("resServerLoginlist size:"+resServerLoginlist.size());

		recClientLoginlist.retainAll(resClientLoginlist);
		System.out.println("rec retain res"+recClientLoginlist.size());

		resServerLoginlist.retainAll(resClientLoginlist);
		System.out.println("recs retain res"+resServerLoginlist.size());
		
		List<Long> slipSort = new ArrayList<>();
		Map<String,Long> map = new HashMap<>();
		Set<Entry<String,Long>> slentrySet = slmap.entrySet();
		for (Entry<String, Long> sl : slentrySet) {
			Long stime = sl.getValue();
			Long ctime = clmap.get(sl.getKey());
			long slip = ctime-stime;
			map.put(sl.getKey(), slip);
			slipSort.add(slip);
		}
		Collections.sort(slipSort);
		System.out.println("最小："+slipSort.get(0));
		System.out.println("最大："+slipSort.get(slipSort.size()-1));

		int equalMin =0;
		int equalMax=0;
		int betMinand1000 = 0;
		int bet1000and2000 = 0;
		int bet2000and3000 = 0;
		int bet3000and4000 = 0;
		int bet4000and5000 = 0;
		int bet5000and6000 = 0;
		int bet6000and7000 = 0;
		int bet7000and8000 = 0;
		int bet8000and9000 = 0;
		int bet9000and10000 = 0;
		int bet10000and11000 = 0;
		int bet11000and12000 = 0;
		int bet12000and13000 = 0;
		int bet13000andMax = 0;
		for (int i = 0; i < slipSort.size(); i++) {
			//耗时等于0
			if(slipSort.get(i).toString().equals(slipSort.get(0).toString())){
				equalMin++;
			}
			if(slipSort.get(i).toString().equals(slipSort.get(slipSort.size()-1).toString())){
				equalMax++;
			}
			if(slipSort.get(i)>=slipSort.get(0)&&slipSort.get(i)<1000){
				betMinand1000++;
			}
			if(slipSort.get(i)>1000&&slipSort.get(i)<=2000){
				bet1000and2000++;
			}
			if(slipSort.get(i)>2000&&slipSort.get(i)<=3000){
				bet2000and3000++;
			}
			if(slipSort.get(i)>3000&&slipSort.get(i)<=4000){
				bet3000and4000++;
			}
			if(slipSort.get(i)>4000&&slipSort.get(i)<=5000){
				bet4000and5000++;
			}
			if(slipSort.get(i)>5000&&slipSort.get(i)<=6000){
				bet5000and6000++;
			}
			if(slipSort.get(i)>6000&&slipSort.get(i)<=7000){
				bet6000and7000++;
			}
			if(slipSort.get(i)>7000&&slipSort.get(i)<=8000){
				bet7000and8000++;
			}
			if(slipSort.get(i)>8000&&slipSort.get(i)<=9000){
				bet8000and9000++;
			}
			if(slipSort.get(i)>9000&&slipSort.get(i)<=10000){
				bet9000and10000++;
			}
			if(slipSort.get(i)>10000&&slipSort.get(i)<=11000){
				bet10000and11000++;
			}
			if(slipSort.get(i)>11000&&slipSort.get(i)<=12000){
				bet11000and12000++;
			}
			if(slipSort.get(i)>12000&&slipSort.get(i)<=13000){
				bet12000and13000++;
			}
			if(slipSort.get(i)>13000&&slipSort.get(i)<=slipSort.get(slipSort.size()-1)){
				bet13000andMax++;
			}
		}
		System.out.println("耗时等于最小："+equalMin);
		System.out.println("耗时等于最大："+equalMax);
		System.out.println("耗时介于min-1："+betMinand1000);
		System.out.println("耗时等于介于1-2："+bet1000and2000);
		System.out.println("耗时等于介于2-3："+bet2000and3000);
		System.out.println("耗时等于介于3-4："+bet3000and4000);
		System.out.println("耗时等于介于4-5："+bet4000and5000);
		System.out.println("耗时等于介于5-6："+bet5000and6000);
		System.out.println("耗时等于介于6-7："+bet6000and7000);
		System.out.println("耗时等于介于7-8："+bet7000and8000);
		System.out.println("耗时等于介于8-9："+bet8000and9000);
		System.out.println("耗时等于介于9-10："+bet9000and10000);
		System.out.println("耗时等于介于10-11："+bet10000and11000);
		System.out.println("耗时等于介于11-12："+bet11000and12000);
		System.out.println("耗时等于介于12-13："+bet12000and13000);
		System.out.println("耗时等于介于13-max："+bet13000andMax);
		Set<Entry<String,Long>> entrySet = map.entrySet();
		for (Entry<String, Long> entry : entrySet) {
			if(entry.getValue().toString().equals(slipSort.get(slipSort.size()-1).toString())){
				System.out.println("她她她"+entry.getKey());
			}
		}
		long total = 0l;
		for (int i = 0; i < slipSort.size(); i++) {
			total+=slipSort.get(i);
		}
		List<Long> svalues = new ArrayList<>(slmap.values());
		Collections.sort(svalues);
		List<Long> cvalues = new ArrayList<>(clmap.values());
		Collections.sort(cvalues);
		System.out.println("均值："+total/slipSort.size());
		long cost = cvalues.get(cvalues.size()-1)-svalues.get(0);
		System.out.println("耗时："+cost);
		
	}
	
/*	public static void main(String[] args) {
		//String fileContent = readFile("C:\\Users\\jackchen\\Desktop\\0317\\7-1-20200317.log");
		String fileContent = readFile("C:\\Users\\jackchen\\Desktop\\0317\\200-2000-2\\client4.txt");
		String[] split = fileContent.split("============================================");
		List<String> recClientLoginlist = new ArrayList<>();
		List<String> resServerLoginlist = new ArrayList<>();

		for (int i = 1; i < split.length; i++) {
				String newStr = split[i];
				if(newStr.contains("ClientLogin")){
					int indexStart = newStr.indexOf("1=房");
					int indexEnd = newStr.indexOf("2=3");
					String sequence = newStr.subSequence(indexStart, indexEnd).toString();
					if(newStr.contains("接收")){
						if(recClientLoginlist.contains(sequence)){
							System.out.println("recClientLogin重复用户:"+sequence);
						}
						recClientLoginlist.add(sequence);
					}
				}
				
				if(newStr.contains("ServerLogin")){
					int indexStart = newStr.indexOf("1=房");
					int indexEnd = newStr.indexOf("2=3");
					String sequence = newStr.subSequence(indexStart, indexEnd).toString();

						if(resServerLoginlist.contains(sequence)){
							System.out.println("ServerLogin重复用户:"+sequence);
						}
						resServerLoginlist.add(sequence);
				}
		}
		System.out.println("recClientLoginlist size:"+recClientLoginlist.size());
		System.out.println("resServerLoginlist size:"+resServerLoginlist.size());

		resServerLoginlist.retainAll(recClientLoginlist);
		System.out.println("rec retain res"+resServerLoginlist.size());
	}*/
	
	/*public static void main(String[] args) {
		String fileContent = readFile("C:\\Users\\jackchen\\Desktop\\0317\\100-1000\\server1.log");
		String[] split = fileContent.split("============================================");
		List<String> recClientLoginlist = new ArrayList<>();
		List<String> resClientLoginlist = new ArrayList<>();
		List<String> resServerLoginlist = new ArrayList<>();

		for (int i = 1; i < split.length; i++) {
				String newStr = split[i];
				if(newStr.contains("ServerLogin")){
					int indexStart = newStr.indexOf("1=房");
					int indexEnd = newStr.indexOf("2=3");
					String sequence = newStr.subSequence(indexStart, indexEnd).toString();
						if(resServerLoginlist.contains(sequence)){
							System.out.println("ServerLogin重复用户:"+sequence);
						}
						resServerLoginlist.add(sequence);
				}
				if(newStr.contains("ClientLogin")){
					int indexStart = newStr.indexOf("1=房");
					int indexEnd = newStr.indexOf("2=3");
					String sequence = newStr.subSequence(indexStart, indexEnd).toString();
					if(newStr.contains("接收")){
						if(recClientLoginlist.contains(sequence)){
							System.out.println("recClientLogin重复用户:"+sequence);
						}
						recClientLoginlist.add(sequence);
					}
					if(newStr.contains("发送")){
						if(resClientLoginlist.contains(sequence)){
							System.out.println("resClientLoginlist重复用户:"+sequence);
						}
						resClientLoginlist.add(sequence);
					}
				}
				
				
		}
		System.out.println("recClientLoginlist size:"+recClientLoginlist.size());
		System.out.println("resClientLoginlist size:"+resClientLoginlist.size());
		System.out.println("resServerLoginlist size:"+resServerLoginlist.size());

		resServerLoginlist.retainAll(recClientLoginlist);
		System.out.println("rec retain res"+resServerLoginlist.size());
		
	}*/
	/*public static void main(String[] args) {
		String fileContent = readFile("C:\\Users\\jackchen\\Desktop\\0317\\100-1000\\client2.log");
		String[] split = fileContent.split("============================================");
		List<String> recClientLoginlist = new ArrayList<>();
		List<String> resClientLoginlist = new ArrayList<>();
		List<String> resServerLoginlist = new ArrayList<>();

		for (int i = 1; i < split.length; i++) {
				String newStr = split[i];
				if(newStr.contains("ServerLogin")){
					int indexStart = newStr.indexOf("1=房");
					int indexEnd = newStr.indexOf("2=3");
					String sequence = newStr.subSequence(indexStart, indexEnd).toString();
						if(resServerLoginlist.contains(sequence)){
							System.out.println("ServerLogin重复用户:"+sequence);
						}
						resServerLoginlist.add(sequence);
				}
				if(newStr.contains("ClientLogin")){
					int indexStart = newStr.indexOf("1=房");
					int indexEnd = newStr.indexOf("2=3");
					String sequence = newStr.subSequence(indexStart, indexEnd).toString();
					if(newStr.contains("接收")){
						if(recClientLoginlist.contains(sequence)){
							System.out.println("recClientLogin重复用户:"+sequence);
						}
						recClientLoginlist.add(sequence);
					}
					if(newStr.contains("发送")){
						if(resClientLoginlist.contains(sequence)){
							System.out.println("resClientLoginlist重复用户:"+sequence);
						}
						resClientLoginlist.add(sequence);
					}
				}
				
				
		}
		System.out.println("recClientLoginlist size:"+recClientLoginlist.size());
		System.out.println("resClientLoginlist size:"+resClientLoginlist.size());
		System.out.println("resServerLoginlist size:"+resServerLoginlist.size());

		resServerLoginlist.retainAll(recClientLoginlist);
		System.out.println("rec retain res"+resServerLoginlist.size());
		
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
