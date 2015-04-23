package com.flurdy.socialbeancrowd.io;

import java.io.InputStream;
import java.util.Scanner;
// import java.util.InputMismatchException;

public class SocialInputterImpl implements SocialInputter {

   private Scanner scanner;

   public SocialInputterImpl(InputStream inputStream) {
     this.scanner = new Scanner(inputStream);
   }

   public String readNextSocialAction(){
      final String actionLine = scanner.nextLine();
      if(actionLine != null){ 
         return actionLine.trim();
      } else {
         return "";
      }
  }

}
