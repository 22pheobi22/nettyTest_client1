package com.sa.service.client;

import java.util.Map;

import com.sa.net.Packet;
import com.sa.net.PacketHeadInfo;
import com.sa.net.PacketType;

public class ClientMsgReceipt extends Packet {

	public ClientMsgReceipt() {}

	public ClientMsgReceipt(PacketHeadInfo packetHead) {
		this.setPacketHead(packetHead);
		this.setToUserId(this.getFromUserId());
	}

	public ClientMsgReceipt(int transactionId, String roomId, String userId, int code) {
		super(transactionId, roomId, "0", userId, code);
	}

	public ClientMsgReceipt(PacketHeadInfo packetHead, Map<String, Object> result) {
		this.setPacketHead(packetHead);
		this.setStatus((Integer) result.get("code"));

		this.setToUserId(this.getFromUserId());

		this.setOption(254, result.get("msg"));
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.MsgReceipt;
	}

	@Override
	public void execPacket() {
//		this.printPacket(true, "", false, "");
	}

}
