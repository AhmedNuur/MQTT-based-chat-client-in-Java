package com.mapr.demo.mqtt.simple;

import java.util.Scanner;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Subscriber {
	
	public static String topic = "Client2Message";
  public static void main(String[] args) throws MqttException {
	
	   String content = null;
	   //int qos= 2;
	   String clientId = "ClientB";
	  
    System.out.println("== START CLIENT-B ==");

    MqttClient client=new MqttClient("tcp://localhost:1883", clientId);
    
    //*******Subscribe*****
    client.setCallback( new SimpleMqttCallBack() );
    client.connect();
    System.out.println(clientId+" Connected");
    client.subscribe(Publisher.getTopic());
    
    while(true){
    	Scanner scan = new Scanner(System.in);
        String scannerContent = scan.nextLine();
        System.out.println("ClientB publishing message: "+scannerContent);
        
        //*******Publish
        MqttMessage clientMessage = new MqttMessage(scannerContent.getBytes());
    	client.publish("Client2Message", clientMessage);
    	
    }
  }

}
