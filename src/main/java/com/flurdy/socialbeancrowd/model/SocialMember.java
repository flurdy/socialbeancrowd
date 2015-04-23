package com.flurdy.socialbeancrowd.model;


import java.util.List;

public interface SocialMember {

    void postMessage(String message);

    void follows(SocialMember friend);

    List<SocialMessage> getWallPosts();

    String getMemberName();

}
