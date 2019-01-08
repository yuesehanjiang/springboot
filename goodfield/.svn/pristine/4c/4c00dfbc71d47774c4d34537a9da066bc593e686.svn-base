package com.alibaba.util.mqtt;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.json.JSONUtil;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.alibaba.util.PushCallback;

public class MQTTServerManager {
	private MqttClient client;
	private MqttConnectOptions options;
	private String userName = "admin";
	private String passWord = "password";
	private String host = "tcp://192.168.5.223:61613";
	private String clientid = "server";
	private List<String> mTopicListName = null;
	private List<MqttTopic> mqttTopicList = null;

	private static MQTTServerManager mInstance;

	/**
	 * 
	 * {
			"messageType": "adv",
			"data": {
				"storesId": "abc123",
				"deviceId": "123456",
				"screen": "0",
				"sourceList": [" ["http://www.qzy.com/abc.jpg", "", ", "http://www.qzy.com/bad.jpg"]
			"]
		}
	 * @param topicNameList
	 * @return
	 */
	
	public static MQTTServerManager getInstance(List<String> topicNameList) {
		System.out.println(" -----------getInstance------------  ");
		if (mInstance == null) {
			synchronized (MQTTServerManager.class) {
				if (mInstance == null) {
					mInstance = new MQTTServerManager(topicNameList);
				}
			}
		}
		return mInstance;
	}

	private MQTTServerManager(List<String> topicNameList) {
		mTopicListName = topicNameList;
		init();
	}

	private void init() {
		try {
			mqttTopicList = new ArrayList<MqttTopic>();
			client = new MqttClient(host, clientid, new MemoryPersistence());
			options = new MqttConnectOptions();
			options.setCleanSession(false);
			options.setUserName(userName);
			options.setPassword(passWord.toCharArray());
			// 设置超时时间
			options.setConnectionTimeout(60);
			// 设置会话心跳时间
			options.setKeepAliveInterval(20);
			connect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	public List<MqttTopic> getTopicList() {
		return mqttTopicList;
	}

	private void connect() {
		try {
			client.setCallback(new PushCallback());
			client.connect(options);
			MqttTopic topic = null;
			for (String s : mTopicListName) {
				System.out.println(" s === "+JSONUtil.serialize(s));
				topic = client.getTopic(s);
				mqttTopicList.add(topic);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void publish(MqttTopic topic, MqttMessage message)
			throws MqttPersistenceException, MqttException {
		MqttDeliveryToken token = topic.publish(message);
		token.waitForCompletion();
	}

}
