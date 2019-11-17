package com.sa.service.client;

import com.sa.net.Packet;
import com.sa.net.PacketType;

import io.netty.buffer.ByteBuf;

public class ClientHeartBeat extends Packet{

	@Override
	public void writePacketBody(ByteBuf buf) {
	}

	@Override
	public void readPacketBody(ByteBuf buf) {
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.ClientHeartBeat;
	}

	@Override
	public void execPacket() {
	}

	@Override
	public String toString() {
		return null;
	}
}
