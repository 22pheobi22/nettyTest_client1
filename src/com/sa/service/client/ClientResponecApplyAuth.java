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
package com.sa.service.client;

import java.util.TreeMap;

import com.sa.net.Packet;
import com.sa.net.PacketHeadInfo;
import com.sa.net.PacketType;

public class ClientResponecApplyAuth extends Packet {

	public ClientResponecApplyAuth() {
	}

	public ClientResponecApplyAuth(PacketHeadInfo packetHead, TreeMap<Integer, Object> options) {
		this.setOptions(options);
		this.setPacketHead(packetHead);
		this.setOption(1, String.valueOf(this.getOption(1)));
		this.setOption(253, String.valueOf(System.currentTimeMillis()));
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.ClientResponecApplyAuth;
	}

	@Override
	public void execPacket() {
		//System.out.println("RECEIVE ClientResponebOne " + this.getOption(1));
	}
}
