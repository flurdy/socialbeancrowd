package com.flurdy.socialbeancrowd.io;

import java.io.OutputStream;
import java.util.List;


public class SocialOutputterImpl implements SocialOutputter {

   private final OutputStream outputStream;

   public SocialOutputterImpl(OutputStream outputStream){
      this.outputStream = outputStream;
   }

   @Override
   public void print(String inline) {
      System.out.print(inline);
   }

   @Override
   public void printLine(String line){
      System.out.println(line);
   }

   @Override
   public void printLines(List<String> lines){
      for(String line : lines){
         printLine(line);
      }
   }

}
