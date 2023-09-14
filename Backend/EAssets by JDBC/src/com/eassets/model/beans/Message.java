package com.eassets.model.beans;

public class Message {
	private int messageId;
	private int transactionId;
	private String messageContent;
	
	public Message() {
		super();
	}
	
	public Message(int messageId, int transactionId, String messageContent) {
		super();
		this.messageId = messageId;
		this.transactionId = transactionId;
		this.messageContent = messageContent;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
}
