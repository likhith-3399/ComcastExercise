package com.comcast.coding.test.utilitys;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Utils {

  private static final Logger logger = LogManager.getLogger(Utils.class);

  public static List<Long> printFibonacciNumberWrapper(Long number) {
      logger.info(" Method: printFibonacciNumbers(), Stage: Started");
      List<Long> fibonacciArray = new ArrayList<Long>();
      for (Long i = new Long(0); i<= number; i++){
          fibonacciArray.add(printFibonacciNumbers(i));
      }
      logger.info(" Method: printFibonacciNumbers(), Stage: Ended");
      return fibonacciArray;
  }

  private static Long printFibonacciNumbers(Long num){
      if (num == 0 || num == 1) {
          return num;
      }
      return printFibonacciNumbers(num - 1) + printFibonacciNumbers(num - 2);
  }
}
