package com.sa.net;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.sa.service.client.ClientHeartBeat;
import com.sa.service.client.ClientLogin;
import com.sa.service.client.ClientLoginOut;
import com.sa.service.client.ClientMsgReceipt;
import com.sa.service.client.ClientResponebRoom;
import com.sa.service.client.ClientResponebRoomUser;
import com.sa.service.client.ClientResponebShareGet;
import com.sa.service.client.ClientResponebShareUpd;
import com.sa.service.server.ServerHeartBeat;
import com.sa.service.server.ServerLogin;
import com.sa.service.server.ServerLoginOut;
import com.sa.service.server.ServerRequestbRoom;
import com.sa.service.server.ServerRequestbShareGet;
import com.sa.service.server.ServerRequestbShareUpd;

public enum PacketType {
	//业务上行数据包
	ServerLogin((short)101, ServerLogin.class),
	ServerHearBeat((short)102, ServerHeartBeat.class),
	ServerLoginOut((short) 105, ServerLoginOut.class),				// 上行 退出

	// 下行 消息回执数据包
	MsgReceipt((short)501, ClientMsgReceipt.class),

	//业务下行数据包
	ClientLogin((short)201, ClientLogin.class),
	ClientHeartBeat((short)202, ClientHeartBeat.class),
	ClientLoginOut((short) 205, ClientLoginOut.class),				// 下行 退出

	ServerRequestbRoom((short) 302, ServerRequestbRoom.class),		// 上行 发消息-房间内
	ServerRequestbShareGet((short) 305, ServerRequestbShareGet.class),	// 上行 获取共享
	ServerRequestbShareUpd((short) 306, ServerRequestbShareUpd.class),	// 上行 修改共享
	
	ClientResponebRoom((short)402, ClientResponebRoom.class),		// 下行 发消息-房间内
	ClientResponebRoomUser((short)404,ClientResponebRoomUser.class),	// 下行 房间内用户列表
	ClientResponebShareGet((short)405,ClientResponebShareGet.class), // 下行 获取共享内容
	ClientResponebShareUpd((short)406,ClientResponebShareUpd.class), // 下行 共享修改
	
	;

	private short type;
	private Class<? extends Packet> packetClass;
	private static Map<Short,Class<? extends Packet>> PACKET_CLASS_MAP = new HashMap<Short,Class<? extends Packet>>();

	static{
		Set<Short> typeSet = new HashSet<Short>();
		for(PacketType p:PacketType.values()){
			Short type = p.getType();
			if(typeSet.contains(type)){
				throw new IllegalStateException("packet type 协议类型重复"+type);
			}
			PACKET_CLASS_MAP.put(type,p.getPacketClass());
			typeSet.add(type);
		}
	}

	PacketType(short type,Class<? extends Packet> packetClass){
		this.setType(type);
		this.packetClass = packetClass;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public Class<? extends Packet> getPacketClass() {
		return packetClass;
	}

	public void setPacketClass(Class<? extends Packet> packetClass) {
		this.packetClass = packetClass;
	}


	public static  Class<? extends Packet> getPacketClassBy(short packetType){
		return PACKET_CLASS_MAP.get(packetType);
	}

	public static void main(String[] args) {
		for(PacketType p:PacketType.values()){
			System.err.println(p.getPacketClass().getSimpleName());
		}
	}

}
