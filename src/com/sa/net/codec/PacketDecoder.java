package com.sa.net.codec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;

import com.sa.net.Packet;
import com.sa.net.PacketManager;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class PacketDecoder extends LengthFieldBasedFrameDecoder{

	public PacketDecoder(int maxFrameLength, int lengthFieldOffset,
						 int lengthFieldLength, int lengthAdjustment,
						 int initialBytesToStrip) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment,
				initialBytesToStrip);
	}

	protected  String readUTF8(ByteBuf buf){
		int strSize = buf.readInt();
		byte[] content = new byte[strSize];
		buf.readBytes(content);
		try {
			return new String(content,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}

	}

	@Override
	public Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
//		byte[] temp = new byte[in.readableBytes()];
//		in.readBytes(temp);

		ByteBuf frame = (ByteBuf) super.decode(ctx, in);
		if(null == frame || frame.readableBytes() <= 0) return null ;

		short packetType = frame.readShort();
		Integer transactionId = frame.readInt();
		String roomId = readUTF8(frame);
		String fromUserId = readUTF8(frame);
		String toUserId = readUTF8(frame);
		Integer status = frame.readInt();

		Packet packet = PacketManager.INSTANCE.createNewPacket(packetType, transactionId, roomId, fromUserId, toUserId, status);

//		boolean useCompression = packet.isUseCompression();
//		ByteBuf realBuf = decompression(frame,useCompression);
		packet.readPacketBody(frame);

		return packet;
	}

	private ByteBuf decompression(ByteBuf sourceBuf,boolean useCompression) throws Exception{
		if(!useCompression){
			return sourceBuf;
		}

		int bodyLength = sourceBuf.readInt();	//先读压缩数据的长度
		byte[] sourceBytes  = new byte[bodyLength];
		sourceBuf.readBytes(sourceBytes); //得到压缩数据的字节数组

		//解压缩
		ByteArrayInputStream bis = new ByteArrayInputStream(sourceBytes);
		GZIPInputStream gzip = new GZIPInputStream(bis);

		final int MAX_MSG_LENGTH = bodyLength * 2;  //假设压缩率最大为100%！！！！！
		byte[] content = new byte[MAX_MSG_LENGTH];
		int num = -1;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while ((num = gzip.read(content, 0, content.length)) != -1) {
			baos.write(content, 0, num);
		}
		baos.flush();
		gzip.close();
		bis.close();

		//重新封装成ByteBuf对象
		ByteBuf resultBuf = Unpooled.buffer();
		byte[] realBytes = baos.toByteArray();  //压缩前的实际数据
		resultBuf.writeBytes(realBytes);
		baos.close();

		return resultBuf;
	}
}
