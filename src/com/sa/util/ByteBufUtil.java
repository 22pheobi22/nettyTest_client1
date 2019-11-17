/**
 * 
 * 项目名称:[WebSocketServer]
 * 包:	 [com.sa.util]
 * 类名称: [ByteBuf]
 * 类描述: [一句话描述该类的功能]
 * 创建人: [Y.P]
 * 创建时间:[2017年7月13日 下午12:51:10]
 * 修改人: [Y.P]
 * 修改时间:[2017年7月13日 下午12:51:10]
 * 修改备注:[说明本次修改内容]  
 * 版本:	 [v1.0]   
 * 
 */
package com.sa.util;

public class ByteBufUtil {
	private int index = 0;
	private byte[] bytes = null;
	public int length = 0;

	public ByteBufUtil() {
		bytes = new byte[0];
	}

	public ByteBufUtil(byte[] bytes) {
		this.bytes = bytes;
		this.length = bytes.length;
	}

	public int readInt() {
		int i = ConvertUtil.bytesToInt(bytes, index);
		index += 4;

		return i;
	}

	public String readStr() throws Exception {
		int len = readInt() * 4;
		String str = ConvertUtil.unicodeToUtf8(bytes, index, len);
		index += len;

		return str;
	}

	public void writeInt(int i) {
		byte[] tmp = ConvertUtil.int2bytes(i);

		byte[] data = new byte[tmp.length+bytes.length];
		System.arraycopy(bytes, 0, data, 0, bytes.length);
		System.arraycopy(tmp, 0, data, bytes.length, tmp.length);

		bytes = data;
		
		index += 4;
		length = index;
	}

	public void writeStr(String str) throws Exception {
		byte[] tmp = ConvertUtil.utf82unicode(str);

		int len = tmp.length;
		writeInt(len);
		
		byte[] data = new byte[tmp.length+bytes.length];
		System.arraycopy(bytes, 0, data, 0, bytes.length);
		System.arraycopy(tmp, 0, data, bytes.length, tmp.length);

		bytes = data;
		
		index += len;
		length = index;
	}

	public byte[] getBytes() {
		return bytes;
	}
}
