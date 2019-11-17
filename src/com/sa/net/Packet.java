package com.sa.net;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import com.sa.util.ByteBufUtil;

import io.netty.buffer.ByteBuf;

public abstract class Packet extends PacketHead{

	private TreeMap<Integer, Object> options = new TreeMap<>(); // 消息记录集

	public Packet(){}

	/** 带参数构造方法*/
	public Packet(Integer transactionId, String roomId, String fromUserId, String toUserId, Integer status) {
		super(transactionId, roomId, fromUserId, toUserId, status);
	}

	/** 写消息体*/
	public void writePacketBody(ByteBufUtil byteBufUtil) throws Exception {
		int size = options.size();
		byteBufUtil.writeInt(size);

		for (Map.Entry<Integer, Object> entry : options.entrySet()) {
			byteBufUtil.writeInt(entry.getKey());
			byteBufUtil.writeStr(null==entry.getValue()?"":(String) entry.getValue());
		}
	}

	/** 读消息体*/
	public void readPacketBody(ByteBufUtil byteBufUtil) throws Exception {
		int size = byteBufUtil.readInt();

		for (int i=0; i<size; i++) {
			options.put(byteBufUtil.readInt(), byteBufUtil.readStr());
		}
	}

	/** 写消息体*/
	public void writePacketBody(ByteBuf buf) {
		int size = options.size();
		buf.writeInt(size);

		for (Map.Entry<Integer, Object> entry : options.entrySet()) {
			buf.writeInt(entry.getKey());
			writeUTF8(buf, (String) entry.getValue());
		}
	}

	/** 读消息体*/
	public void readPacketBody(ByteBuf buf) throws Exception {
		int size = buf.readInt();

		for (int i=0; i<size; i++) {
			options.put(buf.readInt(), readUTF8(buf));
		}
	}

	public void writePacketHead(ByteBuf out) {
		out.writeInt(this.getTransactionId());
		writeUTF8(out, this.getRoomId());
		writeUTF8(out, this.getFromUserId());
		writeUTF8(out, this.getToUserId());
		out.writeInt(this.getStatus());
	}

	// 执行消息包的处理方法
	abstract public void execPacket();

	@Override
	public String toString() {
		String date = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).format(new Date());

		String head = super.toString();

		String body = "";
		for (Map.Entry<Integer, Object> entry : options.entrySet()) {
			body += entry.getKey()+"="+entry.getValue()+"\r\n";
		}

		String str = date + "\r\n" + head + "\r\n" + body;

		return str;
	}

	/**
	 *  是否开启gzip压缩(默认关闭)
	 *  消息体数据大的时候才开启，非常小的包压缩后体积反而变大，而且耗时
	 */
	public boolean isUseCompression() {
		return false;
	}

	/** 在控制台打印消息*/
	public void printPacket(Boolean consoleFlag, String consoleHead, Boolean consoleLogFlag, String fileLogPath) {
		String str = "============================================\r\n"
				+ consoleHead + "\r\n" + this.toString()
				+ "============================================\r\n";
		System.out.println(str);
	}

	public TreeMap<Integer, Object> getOptions() {
		return options;
	}

	public void setOptions(TreeMap<Integer, Object> options) {
		this.options = options;
	}

	public Object getOption(Integer key) {
		return options.get(key);
	}

	public void setOption(Integer key, Object value) {
		options.put(key, value);
	}
}
