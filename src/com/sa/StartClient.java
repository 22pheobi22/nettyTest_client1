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
import com.sa.service.server.ServerRequestbAll;
import com.sa.service.server.ServerRequestbOne;
import com.sa.service.server.ServerRequestbRoom;
import com.sa.service.server.ServerRequestbRoomChat;
import com.sa.service.server.ServerRequestbRoomUser;
import com.sa.service.server.ServerRequestbShareGet;
import com.sa.service.server.ServerRequestbShareUpd;
import com.sa.service.server.ServerRequestcAgreeApplyAuth;
import com.sa.service.server.ServerRequestcApplyAuth;
import com.sa.service.server.ServerRequestcBegin;
import com.sa.service.server.ServerRequestcGag;
import com.sa.service.server.ServerRequestcNotGag;
import com.sa.service.server.ServerRequestcRemove;
import com.sa.service.server.ServerRequestcRoomRemove;
import com.sa.service.server.ServerRequestcShareRemove;
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
		"2.一对一消息;",
		"3.一对多[房间]消息;",
		"4.一对多[全部]消息;",
		"5.获取房间内用户列表;",
		"6.全员禁言;",
		"66.指定用户禁言;",
		"7.全员解除禁言;",
		"77.指定用户解除禁言;",
		"8.移除共享;",
		"9.登出;",
		"10.更新共享;",
		"11.获取共享;",
		"13.移除房间;",
		"14.踢人;",
		"15.开课;",
		"16.房间聊天记录;",
		"17.申请开通权限;",
		"18.申请开通权限同意;",
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
			
		case "2": // 一对一
			new ServerRequestbOne().execPacket();
			break;	
			
		case "3": // 一对多【房间内】
			//serverRequestbRoom(scan);
			new ServerRequestbRoom().execPacket();
			break;
		case "4": // 一对多【全部】
			ServerRequestbAll serverRequestbAll = new ServerRequestbAll();
			serverRequestbAll.setFromUserId("T366");
			serverRequestbAll.setRoomId("22421,22422,22423,");
			serverRequestbAll.setTransactionId(15724);
			serverRequestbAll.setStatus(0);
			serverRequestbAll.execPacket();
			break;
		case "5": // 获取房间内用户列表
			ServerRequestbRoomUser serverRequestbRoomUser = new ServerRequestbRoomUser();
			serverRequestbRoomUser.setFromUserId("T366");
			serverRequestbRoomUser.setRoomId("22421,22422,22423,");
			serverRequestbRoomUser.setTransactionId(15724);
			serverRequestbRoomUser.setStatus(0);
			serverRequestbRoomUser.execPacket();
			break;
		case "6": // 全员禁言
			ServerRequestcGag serverRequestcGag = new ServerRequestcGag();
			serverRequestcGag.setFromUserId("T366");
			serverRequestcGag.setRoomId("22421,22422,22423,");
			serverRequestcGag.setTransactionId(15724);
			serverRequestcGag.setStatus(0);
			TreeMap<Integer,Object> gagMap = new TreeMap<>();
			gagMap.put(1, "都别嗦了");
			serverRequestcGag.setOptions(gagMap);

			serverRequestcGag.execPacket();
			break;	
		case "7": //  全员解除禁言
			ServerRequestcNotGag serverRequestcNotGag = new ServerRequestcNotGag();
			serverRequestcNotGag.setFromUserId("T366");
			serverRequestcNotGag.setRoomId("22421,22422,22423,");
			serverRequestcNotGag.setTransactionId(15724);
			serverRequestcNotGag.setStatus(0);
			TreeMap<Integer,Object> notGagMap = new TreeMap<>();
			notGagMap.put(1, "come on~");
			serverRequestcNotGag.setOptions(notGagMap);

			serverRequestcNotGag.execPacket();
			break;	
		case "66": // 指定用户禁言
			ServerRequestcGag serverRequestcGag66 = new ServerRequestcGag();
			serverRequestcGag66.setFromUserId("T366");
			serverRequestcGag66.setRoomId("22421,22422,22423,");
			serverRequestcGag66.setTransactionId(15724);
			serverRequestcGag66.setStatus(0);
			serverRequestcGag66.setToUserId("147080");
			TreeMap<Integer,Object> gagMap66 = new TreeMap<>();
			gagMap66.put(1, "你可别嗦了");
			serverRequestcGag66.setOptions(gagMap66);

			serverRequestcGag66.execPacket();
			break;	
		case "77": //  指定用户解除禁言
			ServerRequestcNotGag serverRequestcNotGag77 = new ServerRequestcNotGag();
			serverRequestcNotGag77.setFromUserId("T366");
			serverRequestcNotGag77.setRoomId("22421,22422,22423,");
			serverRequestcNotGag77.setTransactionId(15724);
			serverRequestcNotGag77.setStatus(0);
			serverRequestcNotGag77.setToUserId("147080");
			TreeMap<Integer,Object> notGagMap77 = new TreeMap<>();
			notGagMap77.put(1, "请开始你的表演~");
			serverRequestcNotGag77.setOptions(notGagMap77);

			serverRequestcNotGag77.execPacket();
			break;
		case "13": // 移出房间
			ServerRequestcRoomRemove serverRequestcRoomRemove = new ServerRequestcRoomRemove();
			serverRequestcRoomRemove.setFromUserId("T366");
			serverRequestcRoomRemove.setRoomId("22421,22422,22423,");
			serverRequestcRoomRemove.setTransactionId(15724);
			serverRequestcRoomRemove.setStatus(0);

			serverRequestcRoomRemove.execPacket();
			break;	
		case "14": // 踢人
			ServerRequestcRemove serverRequestcRemove = new ServerRequestcRemove();
			serverRequestcRemove.setFromUserId("T366");
			serverRequestcRemove.setRoomId("22421,22422,22423,");
			serverRequestcRemove.setTransactionId(15724);
			serverRequestcRemove.setToUserId("147080");
			serverRequestcRemove.setStatus(0);
			TreeMap<Integer,Object> roomRemoveMap = new TreeMap<>();
			roomRemoveMap.put(255, "deleted");
			serverRequestcRemove.setOptions(roomRemoveMap);

			serverRequestcRemove.execPacket();
			break;
		case "15": // 开课
			ServerRequestcBegin serverRequestcBegin = new ServerRequestcBegin();
			serverRequestcBegin.setFromUserId("T366");
			serverRequestcBegin.setRoomId("22421,22422,22423,");
			serverRequestcBegin.setTransactionId(15724);
			serverRequestcBegin.setStatus(0);

			serverRequestcBegin.execPacket();
			break;
		case "16": // 房间聊天记录
			ServerRequestbRoomChat serverRequestbRoomChat = new ServerRequestbRoomChat();
			serverRequestbRoomChat.setFromUserId("T366");
			serverRequestbRoomChat.setRoomId("22421,22422,22423,");
			serverRequestbRoomChat.setTransactionId(15724);
			serverRequestbRoomChat.setStatus(0);
			TreeMap<Integer,Object> roomChatMap = new TreeMap<>();
			roomChatMap.put(1, "1");//起始页
			roomChatMap.put(2, "10");//每页显示条数
			serverRequestbRoomChat.setOptions(roomChatMap);

			serverRequestbRoomChat.execPacket();
			break;
		case "17": // 申请开通权限
			ServerRequestcApplyAuth serverRequestcApplyAuth = new ServerRequestcApplyAuth();
			serverRequestcApplyAuth.setFromUserId("T366");
			serverRequestcApplyAuth.setRoomId("22421,22422,22423,");
			serverRequestcApplyAuth.setTransactionId(15724);
			serverRequestcApplyAuth.setStatus(0);
			TreeMap<Integer,Object> applyAuthMap = new TreeMap<>();
			applyAuthMap.put(1, "呃。。申请权限");
			serverRequestcApplyAuth.setOptions(applyAuthMap);

			serverRequestcApplyAuth.execPacket();
			break;
		case "18": // 申请开通权限同意
			ServerRequestcAgreeApplyAuth serverRequestcAgreeApplyAuth = new ServerRequestcAgreeApplyAuth();
			serverRequestcAgreeApplyAuth.setFromUserId("T366");
			serverRequestcAgreeApplyAuth.setRoomId("22421,22422,22423,");
			serverRequestcAgreeApplyAuth.setTransactionId(15724);
			serverRequestcAgreeApplyAuth.setStatus(0);
			TreeMap<Integer,Object> agreeApplyAuthMap = new TreeMap<>();
			agreeApplyAuthMap.put(1, "哦。。申请权限同意");
			serverRequestcAgreeApplyAuth.setOptions(agreeApplyAuthMap);

			serverRequestcAgreeApplyAuth.execPacket();
			break;
		case "8": // 移除共享
			ServerRequestcShareRemove serverRequestcShareRemove = new ServerRequestcShareRemove();
			serverRequestcShareRemove.setFromUserId("T366");
			serverRequestcShareRemove.setRoomId("22421,22422,22423,");
			serverRequestcShareRemove.setTransactionId(15724);
			serverRequestcShareRemove.setStatus(0);
			TreeMap<Integer,Object> shareRemoveMap = new TreeMap<>();
			shareRemoveMap.put(1, "starcount");
			serverRequestcShareRemove.setOptions(shareRemoveMap);

			serverRequestcShareRemove.execPacket();
			break;	
		case "10": // 更新共享
			ServerRequestbShareUpd serverRequestbShareUpd = new ServerRequestbShareUpd();
			serverRequestbShareUpd.setFromUserId("T366");
			serverRequestbShareUpd.setRoomId("22421,22422,22423,");
			serverRequestbShareUpd.setTransactionId(15724);
			serverRequestbShareUpd.setStatus(0);
			TreeMap<Integer,Object> updateShareMap = new TreeMap<>();
			updateShareMap.put(1, "starcount");
			updateShareMap.put(2, "{'20147076':'1','20147078':'0','20147079':'0','20147080':'80','20147081':'81','20147082':'82'}");
			updateShareMap.put(3, "1");
			updateShareMap.put(4, "upd");
			serverRequestbShareUpd.setOptions(updateShareMap);

			serverRequestbShareUpd.execPacket();
			break;	
		case "11": // 获取共享
			ServerRequestbShareGet serverRequestbShareGet = new ServerRequestbShareGet();
			serverRequestbShareGet.setFromUserId("T366");
			serverRequestbShareGet.setRoomId("22421,22422,22423,");
			serverRequestbShareGet.setTransactionId(15724);
			serverRequestbShareGet.setStatus(0);
			
			TreeMap<Integer,Object> getShareMap = new TreeMap<>();
			getShareMap.put(1, "starcount");
			getShareMap.put(2, "1");
			serverRequestbShareGet.setOptions(getShareMap);
			
			serverRequestbShareGet.execPacket();

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
