package com.mapr.demo.mqtt.simple;

import java.io.PrintWriter;
import java.util.Scanner;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Publisher {

	public static String topic = "Example";

	public static void main(String[] args) throws MqttException {

	  
      int qos = 0;
      //String broker       = "tcp://iot.eclipse.org:1883";
      String broker = "tcp://localhost:1883";
      String clientId     = "ClientA";
      Scanner scanner = null;
      String content = null;
      MqttClient client = null;
      MemoryPersistence persistence = new MemoryPersistence();
     

      System.out.println("== START CLIENT-A ==");
    
    try {
        client = new MqttClient(broker, clientId, persistence);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(false);
        System.out.println("Connecting to broker: "+broker);
        client.connect(connOpts);
        System.out.println(clientId+" Connected");
        scanner = new Scanner(System.in);
        content = scanner.nextLine();
        System.out.println(clientId+" Publishing message: "+content);
        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(qos);
        client.publish(getTopic(), message);
        
        //------------------------------------
        client.subscribe("Client2Message");
        client.setCallback(new mqttCallback());
       
    } catch(MqttException me) {
        System.out.println("reason "+me.getReasonCode());
        System.out.println("msg "+me.getMessage());
        System.out.println("loc "+me.getLocalizedMessage());
        System.out.println("cause "+me.getCause());
        System.out.println("excep "+me);
        me.printStackTrace();
    }
    
    while(true){
    	content = scanner.nextLine();
    	System.out.println("ClientA Publishing message: "+content);
    	MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(qos);
        client.publish(getTopic(), message);
    
    
    }
  
   // System.out.println("== END PUBLISHER ==")
  }
  
 public void setTopic(String topic){
	this.topic = topic;
 }
 public static String getTopic(){
	 return topic;
 }
}