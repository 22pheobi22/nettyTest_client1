package com.sa.transport;


public class ClientConfigs {

	/**  服务器ip */
	//public static String REMOTE_SERVER_IP = "39.107.26.80";
	//public static String REMOTE_SERVER_IP = "192.168.21.115";
	//public static String REMOTE_SERVER_IP = "192.168.40.163";
	public static String REMOTE_SERVER_IP = "192.168.0.168";
	//public static String REMOTE_SERVER_IP = "192.168.9.6";
	/**  服务器端口 */
	public static int REMOTE_SERVER_PORT = 8180;//8080

	/**  客户端断线重连最大尝试次数 */
	public final static int MAX_RECONNECT_TIMES = 10;
	
	/** 报文是否输出 */
	public final static Boolean CONSOLE_FLAG = true;
}
