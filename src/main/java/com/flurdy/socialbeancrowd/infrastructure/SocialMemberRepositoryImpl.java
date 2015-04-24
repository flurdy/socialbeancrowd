package com.flurdy.socialbeancrowd.infrastructure;

import com.flurdy.socialbeancrowd.model.*;

import java.util.HashMap;
import java.util.Map;


public class SocialMemberRepositoryImpl implements SocialMemberRepository {

    private final SocialMemberFactory factory = new SocialMemberFactoryImpl();

    private final Map<String,SocialMember> members = new HashMap<>();

    public SocialMemberRepositoryImpl() {
    }

    @Override
    public SocialMember findMember(String memberName){
        return members.get(memberName.toLowerCase());
    }

    @Override
    public void addMember(SocialMember member) {
        members.put(member.getMemberName().toLowerCase(),member);
    }

    private SocialMember createMember(String memberName){
      return factory.createMember(memberName,this);
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
