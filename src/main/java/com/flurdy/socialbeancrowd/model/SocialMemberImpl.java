package com.flurdy.socialbeancrowd.model;


import java.util.List;

public class SocialMemberImpl implements SocialMember {

    private final String memberName;

    public SocialMemberImpl(String memberName) {
        this.memberName = memberName;
    }

    @Override
    public void postMessage(String message) {

    }

    @Override
    public void follows(SocialMember friend) {

    }

    @Override
    public List<SocialMessage> getWallPosts() {
        return null;
    }

    @Override
    public String getMemberName() {
        return memberName;
    }

}
