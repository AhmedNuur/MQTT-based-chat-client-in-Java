package com.mapr.demo.mqtt.simple;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SimpleMqttCallBack extends Publisher implements MqttCallback {

  public void connectionLost(Throwable throwable) {
    System.out.println("Connection to MQTT broker lost!");
  }

  public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
	 
		  System.out.println("Message from clientA:\t"+ new String(mqttMessage.getPayload()) ); 
		  
	  /*  if(getTopic() != "Example"){
	    	topic = new String("message"+mqttMessage.getPayload());
	    }
    */
  }

  public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
	  
  }
}
