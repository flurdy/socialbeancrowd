package com.flurdy.socialbeancrowd.infrastructure;

import com.flurdy.socialbeancrowd.model.SocialMember;

public interface SocialMemberFactory {

    public SocialMember createMember(String memberName, SocialMemberRepository socialMemberRepository);

}
