package com.sa;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TreeMap;

import com.sa.base.BaseDataPool;
import com.sa.service.server.ServerLoginOut;
import com.sa.service.server.ServerRequestbRoom;
import com.sa.transport.ChatClient;
import com.sa.transport.ClientConfigs;

public class StartClient {
	/*private static int count = 1;
	private static List<String> roomIds = Arrays.asList("房间A", "房间B", "房间C"
			,"房间D", "房间E", "房间F"
			,"房间G", "房间H", "房间I"
			,"房间J"
			);*/
	
	private static List<String> menuList = Arrays.asList(
		"1.登录绑定;",
//		"2.一对一消息;",
		"3.一对多[房间]消息;",
//		"4.一对多[全部]消息;",
//		"5.获取房间内用户列表;",
//		"6.将指定用户禁言;",
//		"7.将指定用户解除禁言;",
//		"8.将指定用户踢出房间;",
		"9.登出;",
//		"10.共享;",
//		"11.获取共享;",
		"0.退出测试;"
	);

	/*public static void startChatClient() {
		for (int i=0; i<count; i++) {
			String roomId = roomIds.get(i%roomIds.size());

			new Thread(new ChatClient(ClientConfigs.REMOTE_SERVER_IP, ClientConfigs.REMOTE_SERVER_PORT, roomId, i+1)).start();
		}
	}*/

	public static void menu() throws Exception {
		Scanner scan = new Scanner(System.in);

		while(true) {
			System.out.println("===============[请选择操作项目]================");
			for (String menu : menuList) {
				System.out.println(menu);
			}
			System.out.println("==========================================");

//			System.out.print("请选择[1、2、3、4、5、6、7、8、9、10、11、0]? ");
			System.out.println("请选择[1、3、9、0]? ");
			String menu = scan.nextLine();

			if ("0".equals(menu)) {
				System.out.println("退出");
				break;
			} else {
				functionProcessing(menu, scan);
			}
		}

		scan.close();
	}

	private static void functionProcessing(String menu, Scanner scan) {
		switch (menu) {
		
		case "1"://登录
			new Thread(new ChatClient(ClientConfigs.REMOTE_SERVER_IP, ClientConfigs.REMOTE_SERVER_PORT, "22421,22422,22423,", 1)).start();
			break;
		case "100"://教师二次登录
			new Thread(new ChatClient(ClientConfigs.REMOTE_SERVER_IP, ClientConfigs.REMOTE_SERVER_PORT, "22421,22422,22423,", 1)).start();
			break;
			
		case "3": // 一对多【房间内】
			serverRequestbRoom(scan);
			new ServerRequestbRoom().execPacket();

			break;

		case "9":
			serverLoginOut();

			break;
			
		default:
			System.err.println("无此功能");
			break;

		}
	}

	private static void serverLoginOut() {
		for (Map.Entry<String, String> entry : BaseDataPool.USER_ROOM_MAP.entrySet()) {
			ServerLoginOut serverLoginOut = new ServerLoginOut();
			Integer transactionId = (int) (1 + Math.random()*100000000);
			serverLoginOut.setTransactionId(transactionId);
			serverLoginOut.setRoomId(entry.getValue());
			serverLoginOut.setFromUserId(entry.getKey());
			serverLoginOut.setToUserId("");
			serverLoginOut.setStatus(0);
			
			TreeMap<Integer, Object> options = new TreeMap<>(); // 消息记录集
			options.put(1, entry.getKey());
			options.put(2, "STUDENT");
			options.put(3, entry.getKey());
			options.put(4, "icon");
			options.put(5, "agoraId");
			serverLoginOut.setOptions(options);
	
			serverLoginOut.execPacket();
			
		}
	}

	/** CASE 3 一对多【全房间】*/
	private static void serverRequestbRoom(Scanner scanner) {
		System.out.println("请输入消息 : ");
		String content = scanner.nextLine();

		Long c = System.currentTimeMillis();
		c += 1000;
		c = c/1000*1000;
		Date firstTime = new Date(c);
		
		new Timer().scheduleAtFixedRate(new MyTask(content), firstTime, 1000000);

	}

	public static void main(String[] args)  throws Exception {
		//startChatClient();
		
		menu();

		System.exit(0);
	}
}
