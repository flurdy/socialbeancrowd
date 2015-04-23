package com.flurdy.socialbeancrowd.io;

import java.io.InputStream;
import java.util.Scanner;
// import java.util.InputMismatchException;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;


public class SocialInputerImpl implements SocialInputer {

    // private final Logger log = LoggerFactory.getLogger(this.getClass());

   private final Scanner scanner;

   public SocialInputerImpl(InputStream inputStream) {
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
