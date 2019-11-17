package com.sa.service.client;

import com.sa.net.Packet;
import com.sa.net.PacketType;

import io.netty.buffer.ByteBuf;

public class ClientLogin extends Packet{

	public ClientLogin() {}

	@Override
	public void writePacketBody(ByteBuf buf) {
	}

	@Override
	public void readPacketBody(ByteBuf buf) {
	}

	@Override
	public String toString() {
		return null;
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.ClientLogin;
	}

	@Override
	public void execPacket() {
	}

}
