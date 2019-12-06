/**
 *
 * 项目名称：NettyClient
 * 类名称：ServerRequestbShareUpd
 * 类描述：
 * 创建人：Y.P
 * 创建时间：2018年12月25日 下午6:12:44
 * 修改人：Y.P
 * 修改时间：2018年12月25日 下午6:12:44
 * @version  1.0
 *
 */
package com.sa.service.server;

import com.sa.base.ServerManager;
import com.sa.net.Packet;
import com.sa.net.PacketType;

/**
 * @author Y.P
 *
 */
public class ServerRequestbShareUpdSelf extends Packet {
	public ServerRequestbShareUpdSelf(){}
	
	public ServerRequestbShareUpdSelf(Integer transactionId, String roomId, String fromUserId, String toUserId, Integer status){
		super(transactionId, roomId, fromUserId, toUserId, status);
	}

	@Override
	public void execPacket() {
		ServerManager.INSTANCE.sendServerRequest(this);
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.ServerRequestbShareUpdSelf;
	}
}
