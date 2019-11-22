package com.sa.service.client;

import com.sa.net.Packet;
import com.sa.net.PacketType;

public class ClientLogin extends Packet{

	public ClientLogin() {}

	@Override
	public PacketType getPacketType() {
		return PacketType.ClientLogin;
	}

	@Override
	public void execPacket() {
	}

}
