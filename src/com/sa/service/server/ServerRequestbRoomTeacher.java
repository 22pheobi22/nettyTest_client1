/**
 *
 * 项目名称:[NettyServer]
 * 包:	 [com.sa.service]
 * 类名称: [ServerRequestbRoom]
 * 类描述: [一句话描述该类的功能]
 * 创建人: [Y.P]
 * 创建时间:[2017年7月4日 上午11:43:47]
 * 修改人: [Y.P]
 * 修改时间:[2017年7月4日 上午11:43:47]
 * 修改备注:[说明本次修改内容]
 * 版本:	 [v1.0]
 *
 */
package com.sa.service.server;

import java.util.TreeMap;

import com.sa.base.ServerManager;
import com.sa.net.Packet;
import com.sa.net.PacketType;

public class ServerRequestbRoomTeacher extends Packet {
	public ServerRequestbRoomTeacher(){}
	
	@Override
	public PacketType getPacketType() {
		return PacketType.ServerRequestbRoomTeacher;
	}

	@Override
	public void execPacket() {
/*		System.out.println("SEND ServerRequestbRoomUser " + this.getFromUserId() + "[" + this.getOption(1) + "]");
		this.setFromUserId("147080");
		this.setRoomId("22421");
		this.setTransactionId(15724);
		this.setStatus(0);*/
		ServerManager.INSTANCE.sendServerRequest(this);
	}

}
