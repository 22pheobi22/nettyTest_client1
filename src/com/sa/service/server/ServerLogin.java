package com.sa.service.server;

import com.sa.base.ServerManager;
import com.sa.net.Packet;
import com.sa.net.PacketType;

public class ServerLogin extends Packet{

	public ServerLogin(){
	}
	
	public ServerLogin(Integer transactionId, String roomId, String fromUserId, String toUserId, Integer status){
		super(transactionId, roomId, fromUserId, toUserId, status);
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.ServerLogin;
	}

	@Override
	public void execPacket() {
		ServerManager.INSTANCE.sendServerRequest(this);
	}

}
