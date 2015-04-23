package com.flurdy.socialbeancrowd.io;

import java.io.OutputStream;


public class SocialOutputterImpl implements SocialOutputter {

   private final OutputStream outputStream;

   public SocialOutputterImpl(OutputStream outputStream){
      this.outputStream = outputStream;
   }

   public void printLine(String line){
      System.out.println(line);
   }
   
}
