package com.spring.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
/**
 * ����mq��Ϣ
 * @author Administrator
 *
 */
public class MQConsumerListener implements MessageListener{
	public void onMessage(Message message) {
		TextMessage textMsg = (TextMessage) message;
		try {
			System.out.println("��ȡ�����ݣ�" +textMsg.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
