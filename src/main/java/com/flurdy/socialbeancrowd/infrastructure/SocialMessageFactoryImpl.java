package com.flurdy.socialbeancrowd.infrastructure;


import com.flurdy.socialbeancrowd.model.SocialMember;
import com.flurdy.socialbeancrowd.model.SocialMessage;
import com.flurdy.socialbeancrowd.model.SocialMessageImpl;

public class SocialMessageFactoryImpl implements SocialMessageFactory {

    @Override
    public SocialMessage newMessage(SocialMember socialMember, String message) {
        return new SocialMessageImpl(socialMember.getMemberName(),message);
    }

}
