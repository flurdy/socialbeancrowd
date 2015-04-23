package com.flurdy.socialbeancrowd;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.flurdy.socialbeancrowd.io.*;
import com.flurdy.socialbeancrowd.infrastructure.*;
import com.flurdy.socialbeancrowd.model.*;

public class SocialProcessorImpl implements SocialProcessor {

   private final Logger log = LoggerFactory.getLogger(this.getClass());

   private final SocialOutputer outputer;
   private final SocialMemberRepository repository;

   public SocialProcessorImpl(SocialOutputer outputer, SocialMemberRepository repository){
      this.outputer = outputer;
      this.repository = repository;
   }

   public void processAction(String input){
      processAction( new LinkedList<String>( Arrays.asList( input.split("\\s+") ) ) );
   }

   public void processAction(Deque<String> input){
      if( !input.isEmpty() ){
         final String memberName = input.pop();
         final SocialMember member = repository.findOrCreateMember(memberName);
         processMemberAction(member,input);
      }
   }

   public void processMemberAction(SocialMember member, Deque<String> input){
      if( input.isEmpty() ){
         // list posts
      } else {
         final String action = input.pop();
         if( "wall".equals(action.toLowerCase()) ){
            // list wall
         } else if( "->".equals(action) ){
            // post message
         } else if( "follows".equals(action) ){
            // follow friend
         }
      }
   }

}
