/**
 *
 * 项目名称：NettyClient
 * 类名称：ClientLoginOut
 * 类描述：
 * 创建人：Y.P
 * 创建时间：2018年12月25日 下午5:25:24
 * 修改人：Y.P
 * 修改时间：2018年12月25日 下午5:25:24
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
public class ClientLoginOut extends Packet {

	@Override
	public void execPacket() {
		System.out.println(PacketType.ClientLoginOut);
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.ClientLoginOut;
	}

}
