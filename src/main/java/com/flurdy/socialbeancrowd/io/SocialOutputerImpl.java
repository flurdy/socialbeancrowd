package com.flurdy.socialbeancrowd.io;

import java.io.OutputStream;


public class SocialOutputerImpl implements SocialOutputer {

   private final OutputStream outputStream;

   public SocialOutputerImpl(OutputStream outputStream){
      this.outputStream = outputStream;
   }

   public void printLine(String line){
      System.out.println(line);
   }
   
}
