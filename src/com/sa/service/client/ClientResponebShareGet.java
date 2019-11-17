/**
 *
 * 项目名称：NettyClient
 * 类名称：ClientResponebShareGet
 * 类描述：
 * 创建人：Y.P
 * 创建时间：2018年12月25日 下午6:12:57
 * 修改人：Y.P
 * 修改时间：2018年12月25日 下午6:12:57
 * @version  1.0
 *
 */
package com.sa.service.client;

import com.sa.net.Packet;
import com.sa.net.PacketType;

/**
 * @author Y.P
 *
 */
public class ClientResponebShareGet extends Packet {
	@Override
	public void execPacket() {
		System.out.println(this.toString());
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.ClientResponebShareGet;
	}
}
