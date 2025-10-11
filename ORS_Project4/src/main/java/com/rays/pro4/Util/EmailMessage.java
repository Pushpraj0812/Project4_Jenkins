package com.rays.pro4.Util;

/**
 * Represents an email message with all standard fields such as to, from, cc,
 * bcc, subject, message content, and message type.
 * 
 * Provides constants for message types: HTML or plain text.
 * 
 * Usage: This class can be used in combination with EmailUtility to send
 * emails.
 * 
 * @author Pushpraj Singh Kachhaway
 */
public class EmailMessage {

	/** Recipient email address */
	private String to = null;

	/** Sender email address */
	private String from = null;

	/** CC (carbon copy) email addresses */
	private String cc = null;

	/** BCC (blind carbon copy) email addresses */
	private String bcc = null;

	/** Subject of the email */
	private String subject = null;

	/** Message body of the email */
	private String message = null;

	/** Type of the message, default is TEXT_MSG */
	private int messageType = TEXT_MSG;

	/** Constant representing HTML message type */
	public static final int HTML_MSG = 1;

	/** Constant representing plain text message type */
	public static final int TEXT_MSG = 2;

	/**
	 * Gets the recipient email address.
	 * 
	 * @return the 'to' email address
	 */
	public String getTo() {
		return to;
	}

	/**
	 * Sets the recipient email address.
	 * 
	 * @param to the 'to' email address
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * Gets the sender email address.
	 * 
	 * @return the 'from' email address
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Sets the sender email address.
	 * 
	 * @param from the 'from' email address
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * Gets the CC email addresses.
	 * 
	 * @return the 'cc' email addresses
	 */
	public String getCc() {
		return cc;
	}

	/**
	 * Sets the CC email addresses.
	 * 
	 * @param cc the 'cc' email addresses
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}

	/**
	 * Gets the BCC email addresses.
	 * 
	 * @return the 'bcc' email addresses
	 */
	public String getBcc() {
		return bcc;
	}

	/**
	 * Sets the BCC email addresses.
	 * 
	 * @param bcc the 'bcc' email addresses
	 */
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	/**
	 * Gets the subject of the email.
	 * 
	 * @return the email subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the subject of the email.
	 * 
	 * @param subject the email subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Gets the message body of the email.
	 * 
	 * @return the email message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message body of the email.
	 * 
	 * @param message the email message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the message type (HTML_MSG or TEXT_MSG).
	 * 
	 * @return the message type
	 */
	public int getMessageType() {
		return messageType;
	}

	/**
	 * Sets the message type (HTML_MSG or TEXT_MSG).
	 * 
	 * @param messageType the message type
	 */
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}
}