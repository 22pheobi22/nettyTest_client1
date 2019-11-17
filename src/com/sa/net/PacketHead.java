/**
 *
 * 项目名称:[NettyServer]
 * 包:	 [com.sa.net]
 * 类名称: [PacketHead]
 * 类描述: [消息头处理]
 * 创建人: [Y.P]
 * 创建时间:[2017年7月10日 下午4:30:49]
 * 修改人: [Y.P]
 * 修改时间:[2017年7月10日 下午4:30:49]
 * 修改备注:[说明本次修改内容]
 * 版本:	 [v1.0]
 *
 */
package com.sa.net;

import java.io.UnsupportedEncodingException;

import com.sa.util.ByteBufUtil;

import io.netty.buffer.ByteBuf;

public abstract class PacketHead {
	/** 实例化消息头信息*/
	private PacketHeadInfo packetHeadInfo = new PacketHeadInfo();

	private String remoteIp = null;

	abstract public PacketType getPacketType();

	public PacketHead() {}

	/** 带参数构造方法*/
	public PacketHead(Integer transactionId, String roomId, String fromUserId, String toUserId, Integer status) {
		packetHeadInfo.setTransactionId(transactionId);
		packetHeadInfo.setRoomId(roomId);
		packetHeadInfo.setFromUserId(fromUserId);
		packetHeadInfo.setToUserId(toUserId);
		packetHeadInfo.setStatus(status);
	}

	/** 读消息转成UTF-8*/
	protected  String readUTF8(ByteBuf buf){
		int strSize = buf.readInt();
		byte[] content = new byte[strSize];
		buf.readBytes(content);
		try {
			return new String(content,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}

	}

	/** 写消息转成UTF-8*/
	protected  void writeUTF8(ByteBuf buf,String msg){
		byte[] content ;
		try {
			if(null == msg || "null".equals(msg)) {
				msg = "";
			}
			content = msg.getBytes("UTF-8");
			buf.writeInt(content.length);
			buf.writeBytes(content);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "MSG TYPE:" + this.getPacketType()
				+ "\r\nTRANSACTION ID:\t"	+ this.getTransactionId()
				+ "\r\nROOM ID:\t" + this.getRoomId()
				+ "\r\nFROM USER ID:\t" + this.getFromUserId()
				+ "\r\nTO USER ID:\t" + this.getToUserId()
				+ "\r\nSTATUS:\t" + this.getStatus()
				+"\r\n";
	}

	/** 写消息头*/
	public void writePacketHead(ByteBufUtil buf) throws Exception {
		buf.writeInt(packetHeadInfo.getTransactionId());
		buf.writeStr(packetHeadInfo.getRoomId());
		buf.writeStr(packetHeadInfo.getFromUserId());
		buf.writeStr(packetHeadInfo.getToUserId());
		buf.writeInt(packetHeadInfo.getStatus());
	}

	public Integer getTransactionId() {
		return packetHeadInfo.getTransactionId();
	}

	public void setTransactionId(Integer transactionId) {
		packetHeadInfo.setTransactionId(transactionId);
	}

	public String getRoomId() {
		return packetHeadInfo.getRoomId();
	}

	public void setRoomId(String roomId) {
		packetHeadInfo.setRoomId(roomId);
	}

	public String getFromUserId() {
		return packetHeadInfo.getFromUserId();
	}

	public void setFromUserId(String fromUserId) {
		packetHeadInfo.setFromUserId(fromUserId);
	}

	public String getToUserId() {
		return packetHeadInfo.getToUserId();
	}

	public void setToUserId(String toUserId) {
		packetHeadInfo.setToUserId(toUserId);
	}

	public Integer getStatus() {
		return packetHeadInfo.getStatus();
	}

	public void setStatus(Integer status) {
		packetHeadInfo.setStatus(status);
	}

	public PacketHeadInfo getPacketHead() {
		return packetHeadInfo;
	}

	/** 给消息头赋值*/
	public void setPacketHead(PacketHeadInfo packetHead) {
		packetHeadInfo.setTransactionId(packetHead.getTransactionId());
		packetHeadInfo.setRoomId(packetHead.getRoomId());
		packetHeadInfo.setFromUserId(packetHead.getFromUserId());
		packetHeadInfo.setToUserId(packetHead.getToUserId());
		packetHeadInfo.setStatus(packetHead.getStatus());
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

}
