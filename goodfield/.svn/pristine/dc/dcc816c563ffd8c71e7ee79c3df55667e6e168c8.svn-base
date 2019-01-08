package com.alibaba.util;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class PushCallback implements MqttCallback {

	public void connectionLost(Throwable arg0) {
		System.out.println(" connectionLost ");
	}

	public void deliveryComplete(IMqttDeliveryToken arg0) {
		System.out.println(" deliveryComplete ");
	}

	public void messageArrived(String arg0, MqttMessage message) throws Exception {
		  // subscribe后得到的消息会执行到这里面  
		     System.out.println("接收消息主题 : " + arg0);  
		     System.out.println("接收消息内容 : " + new String(message.getPayload()));  
	}

}
