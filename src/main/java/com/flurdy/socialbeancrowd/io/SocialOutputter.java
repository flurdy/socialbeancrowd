package com.flurdy.socialbeancrowd.io;


import java.util.List;

public interface SocialOutputter {

   public void print(String inline);

   public void printLine(String line);

   public void printLines(List<String> lines);
   
}
