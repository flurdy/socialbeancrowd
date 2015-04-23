package com.flurdy.socialbeancrowd.infrastructure;

import com.flurdy.socialbeancrowd.model.*;


public class SocialMemberRepositoryImpl implements SocialMemberRepository {
   
   public SocialMember findMember(String memberName){
      return null;
   }

   private SocialMember createMember(String memberName){
      return null;
   }

   public SocialMember findOrCreateMember(String memberName){
      SocialMember member = findMember(memberName);
      if(member == null){
         member = createMember(memberName);
      } 
      return member;
   }

}
