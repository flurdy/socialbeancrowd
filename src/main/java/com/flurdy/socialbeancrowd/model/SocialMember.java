package com.flurdy.socialbeancrowd.model;


import java.util.Deque;
import java.util.List;

public interface SocialMember {

    void postMessage(String message);

    void postMessage(SocialMessage message);

    void follows(SocialMember friend);

    List<SocialMessage> getWallMessages();

    List<SocialMessage> getTimelineMessages();

    String getMemberName();

    List<String> processMemberAction(Deque<String> input);

}
