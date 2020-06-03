package com.sa.transport;

import java.util.TreeMap;

import com.sa.base.BaseDataPool;
import com.sa.net.Packet;
import com.sa.net.PacketManager;
import com.sa.service.server.ServerLogin;
import com.sa.util.LogOutPrint;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class ClientTransportHandler extends ChannelInboundHandlerAdapter {
	private String roomId = "roomId";
	private String userId="-1";
	
	//private String roomId = "22421";

	public ClientTransportHandler(){ }
	
	public ClientTransportHandler(String roomId, String userId){
		this.roomId = roomId;
		this.userId = userId;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		//Integer transactionId = (int) (1 + Math.random()*100000000);
		//String fromUserId = "147080";
		String fromUserId = userId;
		ServerLogin serverLogin = new ServerLogin(999, roomId, fromUserId, "", 0);
		TreeMap<Integer, Object> options = new TreeMap<>(); // 消息记录集
		options.put(1, fromUserId);
		//options.put(2, "STUDENT");
		options.put(2, "TEACHER");
		options.put(3, fromUserId);
		options.put(4, "icon");
		options.put(5, "agoraId");
		//options.put(5, "20147080");
		serverLogin.setOptions(options);
		
		BaseDataPool.USER_ROOM_MAP.put(fromUserId, roomId);
		BaseDataPool.USER_CHANNEL_MAP.put(fromUserId, ctx);
		String str = "============================================\r\n"
				+ "发送" + "\r\n" + serverLogin.toString()
				+ "============================================\r\n";
		LogOutPrint.log("/log", str);
		serverLogin.execPacket();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception{
		Packet  packet = (Packet) msg;
		//System.out.println("【"+ctx+"】");
		System.out.println(packet.toString());
//		packet.printPacket(ClientConfigs.CONSOLE_FLAG, "", packet.toString());
		String str = "============================================\r\n"
				+ "接收" + "\r\n" + packet.toString()
				+ "============================================\r\n";
		LogOutPrint.log("/log", str);
		PacketManager.INSTANCE.execPacket(packet);
	}

	public void close(ChannelHandlerContext ctx,ChannelPromise promise){
		System.err.println("TCP closed...");

		ctx.close(promise);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.err.println("客户端关闭1");
		ctx.close();
	}

	public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		ctx.disconnect(promise);
		System.err.println("客户端关闭2");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.err.println("客户端关闭3");
//		ctx.fireExceptionCaught(cause);
		Channel channel = ctx.channel();
		cause.printStackTrace();
		if(channel.isActive()){
			System.err.println("simpleclient"+channel.remoteAddress()+"异常");
		}
	}
}
