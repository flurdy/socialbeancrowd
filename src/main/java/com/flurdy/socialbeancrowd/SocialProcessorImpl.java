package com.flurdy.socialbeancrowd;

import java.util.*;

import com.google.common.base.Joiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.flurdy.socialbeancrowd.io.*;
import com.flurdy.socialbeancrowd.infrastructure.*;
import com.flurdy.socialbeancrowd.model.*;

public class SocialProcessorImpl implements SocialProcessor {

   private final Logger log = LoggerFactory.getLogger(this.getClass());

   private final SocialOutputter outputter;
   private final SocialMemberRepository repository;
   private final Joiner joiner = Joiner.on(" ");

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
         final List<String> lines  = processMemberAction(member,input);
         outputter.printLines(lines);
      }
   }

   protected List<String> processMemberAction(SocialMember member, Deque<String> input){
      final List<String> lines = new ArrayList<>();
      if( input.isEmpty() ){
         lines.addAll(getAndConvertMessages(member));
      } else {
         final String action = input.pop();
         if( "wall".equals(action.toLowerCase()) ){
             lines.addAll(getAndConvertWallMessages(member));
         } else if( "->".equals(action) ){
            postMessage(member,input);
         } else if( "follows".equals(action) ){
             followFriend(member, input);
         }
      }
       return lines;
   }

    private List<String> getAndConvertMessages(SocialMember member){
       final List<String> lines = new ArrayList<>();
       final List<SocialMessage> messages = member.getTimelineMessages();
       for(SocialMessage message : messages){
           lines.add(message.getPostMessage());
       }
       return lines;
   }

   private List<String> getAndConvertWallMessages(SocialMember member){
       final List<String> lines = new ArrayList<>();
       final List<SocialMessage> messages = member.getWallMessages();
       for(SocialMessage message : messages){
           lines.add(message.getWallMessage());
       }
       return lines;
   }

    private void postMessage(SocialMember member, Deque<String> input) {
        if(!input.isEmpty()){
            final String message = joiner.join(input);
            member.postMessage(message);
        }
    }

    private void followFriend(SocialMember member, Deque<String> input) {
        if(!input.isEmpty()){
            final String friendName = input.pop();
            final SocialMember friend = repository.findMember(friendName);
            if(friend != null){
                member.follows(friend);
            }
        }
    }

}
