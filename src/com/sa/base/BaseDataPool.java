package com.sa.base;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.ChannelHandlerContext;

public class BaseDataPool {
	public static Map<String, ChannelHandlerContext> USER_CHANNEL_MAP  = new ConcurrentHashMap<>();
	public static Map<String, String> USER_ROOM_MAP  = new ConcurrentHashMap<>();
}
