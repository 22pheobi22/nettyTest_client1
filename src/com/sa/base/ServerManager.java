package com.sa.base;

import com.sa.net.Packet;

import io.netty.channel.ChannelHandlerContext;

public enum ServerManager {
	INSTANCE;

	public void sendServerRequest(Packet request){
		ChannelHandlerContext channelContext = BaseDataPool.USER_CHANNEL_MAP.get(request.getFromUserId());

		if (null == channelContext) return;

		channelContext.writeAndFlush(request);
		
//		System.out.println(request.toString());
	}

}
