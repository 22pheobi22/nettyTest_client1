/**
 * 
 * 项目名称:[WebSocketServer]
 * 包:	 [com.sa.util]
 * 类名称: [ConvertUtil]
 * 类描述: [一句话描述该类的功能]
 * 创建人: [Y.P]
 * 创建时间:[2017年7月13日 上午9:29:32]
 * 修改人: [Y.P]
 * 修改时间:[2017年7月13日 上午9:29:32]
 * 修改备注:[说明本次修改内容]  
 * 版本:	 [v1.0]   
 * 
 */
package com.sa.util;

import java.net.URLDecoder;

public class ConvertUtil {
	/**
	    * byte数组中取int数值，本方法适用于(低位在前，高位在后)的顺序，和和intToBytes（）配套使用
	    *
	    * @param src
	    *            byte数组
	    * @param offset
	    *            从数组的第offset位开始
	    * @return int数值
	    */
	public static int bytesToInt(byte[] src, int offset) {
	    int value;
	    value = (int) ((src[offset] & 0xFF)
	            | ((src[offset+1] & 0xFF)<<8)
	            | ((src[offset+2] & 0xFF)<<16)
	            | ((src[offset+3] & 0xFF)<<24));
	    return value;
	}

	public static String unicodeToUtf8(byte[] bytes, int offset, int len) throws Exception {
		char aChar;
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = offset; x < offset + len;) {
			aChar = (char) bytesToInt(bytes, x);
			x+=4;
			if (aChar == '\\') {
				aChar = (char) bytesToInt(bytes, x);
				x+=4;
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = (char) bytesToInt(bytes, x);
						x+=4;
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException(
									"Malformed   \\uxxxx   encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);
		}

		return URLDecoder.decode(outBuffer.toString(), "utf-8");
	}

	public static byte[] int2bytes(int data) {
        byte[] bytes = new byte[4];

        bytes[0] = (byte) (data & 0xff);
        bytes[1] = (byte) ((data & 0xff00) >> 8);
        bytes[2] = (byte) ((data & 0xff0000) >> 16);
        bytes[3] = (byte) ((data & 0xff000000) >> 24);

        return bytes;
    }

	public static byte[] utf82unicode(String inStr) throws Exception {
		String str = (inStr == null ? "" : inStr);
		String tmp;
		StringBuffer sb = new StringBuffer(1000);
		char c;
		int i, j;
		sb.setLength(0);
		for (i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			sb.append("\\u");
			j = (c >>> 8); // 取出高8位
			tmp = Integer.toHexString(j);
			if (tmp.length() == 1)
				sb.append("0");
			sb.append(tmp);
			j = (c & 0xFF); // 取出低8位
			tmp = Integer.toHexString(j);
			if (tmp.length() == 1)
				sb.append("0");
			sb.append(tmp);

		}
		return sb.toString().getBytes();
	}
//		public static byte[] utf82unicode(String inStr) throws Exception {
//		if (null == inStr) {
//			inStr = "";
//		}
//
//		if (inStr.indexOf("\\") != -1) {
//			inStr = inStr.replaceAll("\\", "\\\\");
//		}
//        char[] myBuffer = inStr.toCharArray();
//        
//        StringBuffer sb = new StringBuffer();
//        for (int i = 0; i < inStr.length(); i++) {
//         UnicodeBlock ub = UnicodeBlock.of(myBuffer[i]);
//            if(ub == UnicodeBlock.BASIC_LATIN){
//             //英文及数字等
//             sb.append(myBuffer[i]);
//            }else if(ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS){
//             //全角半角字符
//             int j = (int) myBuffer[i] - 65248;
//             sb.append((char)j);
//            }else{
//             //汉字
//             short s = (short) myBuffer[i];
//                String hexS = Integer.toHexString(s);
//                String unicode = "\\u"+hexS;
//             sb.append(unicode.toLowerCase());
//            }
//        }
//        return sb.toString().getBytes();
//    }

//	public static byte[] utf82unicode(String str) throws Exception {
//		String temp = URLEncoder.encode(str, "UTF-8");
//
//		ByteBuf buf = Unpooled.copiedBuffer(temp, CharsetUtil.UTF_8);
//
//		return buf.array();
//	}
}
