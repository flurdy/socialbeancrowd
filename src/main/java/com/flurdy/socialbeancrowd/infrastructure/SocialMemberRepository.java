package com.flurdy.socialbeancrowd.infrastructure;

import com.flurdy.socialbeancrowd.model.*;


public interface SocialMemberRepository {
   
   public SocialMember findMember(String memberName);

   public void addMember(SocialMember member);

   public SocialMember findOrCreateMember(String memberName);

}
