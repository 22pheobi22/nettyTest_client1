package com.sa.base;

import com.sa.net.Packet;
import com.sa.util.LogOutPrint;

import io.netty.channel.ChannelHandlerContext;

public enum ServerManager {
	INSTANCE;

	public void sendServerRequest(Packet request){
		ChannelHandlerContext channelContext = BaseDataPool.USER_CHANNEL_MAP.get(request.getFromUserId());

		if (null == channelContext) return;

		channelContext.writeAndFlush(request);
		
//		System.out.println(request.toString());
	}
	public synchronized void log(Packet request) {
		String str = "============================================\r\n"
				+ "接收" + "\r\n" + this.toString()
				+ "============================================\r\n";
			LogOutPrint.log("/logs", str);
	}
}
