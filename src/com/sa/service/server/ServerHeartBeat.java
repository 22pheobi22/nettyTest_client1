package com.sa.service.server;

import com.sa.net.Packet;
import com.sa.net.PacketType;

public class ServerHeartBeat extends Packet{

	@Override
	public PacketType getPacketType() {
		return PacketType.ServerHearBeat;
	}

	@Override
	public void execPacket() {
		System.err.println("收到客户端的pong响应");
	}

}
