package com.alibaba.util.mqtt;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.pojo.Machine;
import com.alibaba.pojo.MachineInfo;
import com.alibaba.service.FolderService;
import com.google.gson.Gson;
public class Test {
	
	public static MQTTServerManager manager;
	
	public static void main(String[] args) throws MqttPersistenceException,
			MqttException {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		FolderService workSignService=(FolderService)applicationContext.getBean("biz");
		List<String> sName = new ArrayList<String>();
		Machine m = new Machine();
		m.setType("0");
		m.setMachine_id("001");
		List<Machine> machineInfo = workSignService.getMachineInfo(m);
		for(Machine mm:machineInfo){
			sName.add(mm.getMachine_id());
		}
		sName.add(String.valueOf(machineInfo));
		manager = MQTTServerManager.getInstance(sName);
		try {
			MachineInfo s = new MachineInfo();
			s.setData(machineInfo);
			s.setMessageType("啊啊啊啊啊");
			System.out.println("================"+new Gson().toJson(s));
			send(new Gson().toJson(s));
		} catch (Exception e) {
			e.printStackTrace();
		}
		timer1();
	}

	public static void aaa() {

	}

	public static void timer1() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				try {
					System.out.println(" 发送第二次");
					send("第二次消息");
					timer2();
				} catch (MqttPersistenceException e) {
					e.printStackTrace();
				} catch (MqttException e) {
					e.printStackTrace();
				}
			}
		}, 10000);// 设定指定的时间time,此处为2000毫秒
	}

	public static void timer2() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				try {
					System.out.println(" 发送第三次");
					send("第三次消息");
				} catch (MqttPersistenceException e) {
					e.printStackTrace();
				} catch (MqttException e) {
					e.printStackTrace();
				}
			}
		}, 10000);// 设定指定的时间time,此处为2000毫秒
	}

	public static void send(String msg) throws MqttPersistenceException,
			MqttException {
		List<MqttTopic> mqttTopicList = manager.getTopicList();

		for (MqttTopic topic : mqttTopicList) {
			if (topic.getName().equals("001")) {

				MqttMessage mMessage = new MqttMessage();
				mMessage.setQos(2);
				mMessage.setRetained(true);
				mMessage.setPayload(msg.getBytes());

				manager.publish(topic, mMessage);
			}
		}
	}

}
