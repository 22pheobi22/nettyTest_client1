/**
 *
 */
package com.sa.util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @author Y.P
 *
 */
public class LogOutPrint {
	private static HashMap<String, Object[]> map = new HashMap<String, Object[]>();

	private static PrintWriter get(String logDir, String fileName) {
		try {
			Object[] arr = map.get(logDir);

			FileWriter fwl = null;
			PrintWriter outl = null;
			String datelog = null;

			if (null != arr) {
				fwl = (FileWriter) arr[0];
				outl = (PrintWriter) arr[1];
				datelog = (String) arr[2];
			}

			if (!new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date()).equals(datelog)) {
				if (null != fwl)
					fwl.close();

				if (!logDir.endsWith("/")) {
					logDir+="/";
				}

				File temp = new File(logDir);
				if (!temp.exists()) {
					temp.mkdirs();
				}

				java.text.DateFormat dflog = new java.text.SimpleDateFormat("yyyyMMdd");
				datelog = dflog.format(new java.util.Date());
				if (null != fileName && !"".equals(fileName)) {
					datelog += fileName;
				}

				// 按日期每天生成一个日志文件
				fwl = new FileWriter(logDir + datelog + ".log", true);
				outl = new PrintWriter(fwl);


				arr = new Object[3];
				arr[0] = fwl;
				arr[1] = outl;
				arr[2] = datelog;
				map.put(logDir, arr);
			}

			return outl;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 写消息日志
	public static void log(String logDir, String msg) {
		try {
			PrintWriter outl = get(System.getProperty("user.dir")+logDir, null);

			java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String datestr = df.format(new java.util.Date());

			outl.println(datestr + "\r\n" + msg+ "\r\n");
			outl.flush();

		} catch (Exception e) {
			String message = "写log文件错误!" + e;
			e.printStackTrace();
			System.out.println(message);
		}
	}

	// 写消息日志
	public static void log2(String logDir, String msg) {
		try {
			PrintWriter outl = get(logDir, null);

			java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String datestr = df.format(new java.util.Date());

			outl.println(datestr + "\r\n" + msg + "\r\n");
			outl.flush();

		} catch (Exception e) {
			String message = "写log文件错误!" + e;
			e.printStackTrace();
			System.out.println(message);
		}
	}

	public static void main(String[] argc) {
		for (int i=0;i<10;i++) {
			log("F:/log", "第"+i+"次写日志！");
		}
	}
}
