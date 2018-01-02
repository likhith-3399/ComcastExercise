package com.comcast.coding.test.utilitys;

import org.springframework.stereotype.Component;

@Component
public class DeadLockScenario {
	
	
	private String str1 = "Java";
    private String str2 = "UNIX";

	public static void createDealLockScenario(int timeout) {
		DeadLockScenario deadLockExample = new DeadLockScenario();
        deadLockExample.trd1.start();
        deadLockExample.trd2.start();
	}

	public static void main(String a[]){
		DeadLockScenario mdl = new DeadLockScenario();
        mdl.trd1.start();
        mdl.trd2.start();
    }
	
    Thread trd1 = new Thread("My Thread 1"){
        public void run(){
            while(true){
                synchronized(str1){
                    synchronized(str2){
                        System.out.println(str1 + str2);
                    }
                }
            }
        }
    };
     
    Thread trd2 = new Thread("My Thread 2"){
        public void run(){
            while(true){
                synchronized(str2){
                    synchronized(str1){
                        System.out.println(str2 + str1);
                    }
                }
            }
        }
    };
    
}
