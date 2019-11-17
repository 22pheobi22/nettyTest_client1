package com.sa.net.codec;

import com.sa.net.Packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out)
			throws Exception {
		// 消息头
		out.writeShort(msg.getPacketType().getType());
		msg.writePacketHead(out);

		msg.writePacketBody(out);

		
//		// 消息体
//		if(msg.isUseCompression()){  //开启gzip压缩
//			ByteBuf buf =  Unpooled.buffer();
//			msg.writePacketBody(buf);
//			byte[] content = new byte[buf.readableBytes()];
//			buf.getBytes(0, content);
//			ByteArrayOutputStream bos = new ByteArrayOutputStream();
//			GZIPOutputStream gzip = new GZIPOutputStream(bos);
//			gzip.write(content);
//			gzip.close();
//			byte[] destBytes = bos.toByteArray();
//			out.writeInt(destBytes.length);
//			out.writeBytes(destBytes);
//			bos.close();
//		}else{
//			msg.writePacketBody(out);
//		}
	}

}
