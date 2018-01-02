package com.comcast.coding.test.utilitys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class DeadLockScenario {
	
	private static final Logger logger = LogManager.getLogger(DeadLockScenario.class);

	public static String createDealLockScenario(int timeout) throws InterruptedException {
		logger.info(" Method: createDealLockScenario(), Stage: Started");

		List<Integer> list1 = new ArrayList<>(Arrays.asList(2, 4, 6, 8, 10));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 3, 7, 9, 11));

        Thread thread1 = new Thread(() -> {
            moveListItem(list1, list2, 2, 10);
        });
        Thread thread2 = new Thread(() -> {
            moveListItem(list2, list1, 9, 1);
        });


        thread1.start();
        Thread.sleep(timeout);
        thread1.interrupt();

        thread2.start();
        Thread.sleep(0);
        thread2.interrupt();
		logger.info(" Method: createDealLockScenario(), Stage: Ended");
		return new StringBuilder()
				.append("Thread One Status:")
				.append(thread1.getState().toString())
				.append(".....")
				.append("Thread Two Status:")
				.append(thread1.getState().toString())
				.toString();
	}

	private static void moveListItem (List<Integer> from, List<Integer> to, Integer item, int timeout) {
		logger.info("attempting lock for from list", from);
        synchronized (from) {
        	logger.info("lock acquired for from list", from);
            try {
                TimeUnit.SECONDS.sleep(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("attempting lock for to list ", to);
            synchronized (to) {
            	logger.info("lock acquired for to list", to);
                if(from.remove(item)){
                  to.add(item);
                }
                logger.info("moved item to to list ", to);
            }
        }
    }
}
