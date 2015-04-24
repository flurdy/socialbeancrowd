package com.flurdy.socialbeancrowd.infrastructure;

import com.flurdy.socialbeancrowd.model.SocialMember;
import com.flurdy.socialbeancrowd.model.SocialMemberImpl;

public class SocialMemberFactoryImpl implements SocialMemberFactory {

    @Override
    public SocialMember createMember(String memberName, SocialMemberRepository socialMemberRepository) {
        return new SocialMemberImpl(memberName);
    }

}
