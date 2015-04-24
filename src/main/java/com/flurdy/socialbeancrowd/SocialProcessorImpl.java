package com.flurdy.socialbeancrowd;

import java.util.*;

import com.flurdy.socialbeancrowd.io.*;
import com.flurdy.socialbeancrowd.infrastructure.*;
import com.flurdy.socialbeancrowd.model.*;

public class SocialProcessorImpl implements SocialProcessor {

   private final SocialOutputter outputter;
   private final SocialMemberRepository repository;

   public SocialProcessorImpl(SocialOutputter outputter, SocialMemberRepository repository){
      this.outputter = outputter;
      this.repository = repository;
   }

   public void processAction(String input){
      processAction( new LinkedList<>( Arrays.asList( input.split("\\s+") ) ) );
   }

   protected void processAction(Deque<String> input){
      if( !input.isEmpty() ){
         final String memberName   = input.pop();
         final SocialMember member = repository.findOrCreateMember(memberName);
         final List<String> lines  = member.processMemberAction(input);
         outputter.printLines(lines);
      }
   }

}
