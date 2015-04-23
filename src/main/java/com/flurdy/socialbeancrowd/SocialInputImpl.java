package com.flurdy.socialbeancrowd;

import java.io.InputStream;
import java.util.Scanner;


public class SocialInputImpl implements SocialInput {

   private final Scanner scanner;

   public SocialInputImpl(InputStream inputStream) {
     this.scanner = new Scanner(inputStream);
   }
   
}
