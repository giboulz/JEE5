package com.gbz.tuto.test;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "HelloStdWorld", eager = true)
public class HelloStdWorld {
   public HelloStdWorld() {
      System.out.println("HelloWorld started!");
   }
   public String getMessage() {
      return "Hello World!";
   }
}