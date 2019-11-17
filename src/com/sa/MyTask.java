/**
 *
 * 项目名称：NettyClient
 * 类名称：MyTask
 * 类描述：
 * 创建人：Y.P
 * 创建时间：2019年1月30日 下午6:56:06
 * 修改人：Y.P
 * 修改时间：2019年1月30日 下午6:56:06
 * @version  1.0
 *
 */
package com.sa;

import java.util.Map;
import java.util.TimerTask;

import com.sa.base.BaseDataPool;
import com.sa.service.server.ServerRequestbRoom;

public class MyTask extends TimerTask {
	private String content = "";
	public MyTask() {}
	
	public MyTask(String content) {
		this.content = content;
	}

	@Override
	public void run() {
		for (Map.Entry<String, String> entry : BaseDataPool.USER_ROOM_MAP.entrySet()) {
			ServerRequestbRoom serverRequestbRoom = new ServerRequestbRoom();
			Integer transactionId = (int) (1 + Math.random()*100000000);
			serverRequestbRoom.setTransactionId(transactionId);
			serverRequestbRoom.setRoomId(entry.getValue());
			serverRequestbRoom.setFromUserId(entry.getKey());
			serverRequestbRoom.setToUserId("");
			serverRequestbRoom.setStatus(0);

			serverRequestbRoom.setOption(1,entry.getKey()+"["+this.content+"]");

			serverRequestbRoom.execPacket();
		}
	}

}
