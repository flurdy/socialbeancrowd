package com.flurdy.socialbeancrowd.infrastructure;

import com.flurdy.socialbeancrowd.model.SocialMember;
import com.flurdy.socialbeancrowd.model.SocialMessage;

public interface SocialMessageFactory {

    public SocialMessage newMessage(SocialMember socialMember, String message);

}
