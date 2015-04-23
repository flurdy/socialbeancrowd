package com.flurdy.socialbeancrowd.infrastructure;

import com.flurdy.socialbeancrowd.model.*;


public class SocialMemberRepositoryImpl implements SocialMemberRepository {

    @Override
    public SocialMember findMember(String memberName){
        return null;
    }

    @Override
    public void addMember(SocialMember member) {

    }

    private SocialMember createMember(String memberName){
      return null;
   }

   @Override
   public SocialMember findOrCreateMember(String memberName){
      SocialMember member = findMember(memberName);
      if(member == null){
         member = createMember(memberName);
         addMember(member);
      } 
      return member;
   }

}
