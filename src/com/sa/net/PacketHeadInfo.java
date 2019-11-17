/**
 *
 * 项目名称:[NettyServer]
 * 包:	 [com.sa.net]
 * 类名称: [PacketHeadCP]
 * 类描述: [消息头字段]
 * 创建人: [Y.P]
 * 创建时间:[2017年7月11日 下午4:13:16]
 * 修改人: [Y.P]
 * 修改时间:[2017年7月11日 下午4:13:16]
 * 修改备注:[说明本次修改内容]
 * 版本:	 [v1.0]
 *
 */
package com.sa.net;

public class PacketHeadInfo {
	private Integer transactionId = 0;	// 事务id
	private String roomId = "";			// 房间id
	private String fromUserId = "";		// 发信人id
	private String toUserId = "";		// 目标用户id
	private Integer status = 0;			// 状态

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
