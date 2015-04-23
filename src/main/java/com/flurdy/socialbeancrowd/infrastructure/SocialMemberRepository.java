package com.flurdy.socialbeancrowd.infrastructure;

import com.flurdy.socialbeancrowd.model.*;


public interface SocialMemberRepository {
   
   public SocialMember findMember(String memberName);

   public SocialMember findOrCreateMember(String memberName);

}
