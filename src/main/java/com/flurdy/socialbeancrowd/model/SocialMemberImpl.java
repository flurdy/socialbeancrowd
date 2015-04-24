package com.flurdy.socialbeancrowd.model;


import org.joda.time.DateTime;

import java.util.*;

public class SocialMemberImpl implements SocialMember {

    private final String memberName;
    private final LinkedList<SocialMessage> messages;
    private final Set<SocialMember> friends;

    public SocialMemberImpl(String memberName) {
        this.memberName = memberName;
        this.messages   = new LinkedList<>();
        this.friends    = new HashSet<>();
    }

    @Override
    public void postMessage(String message) {
        if(message != null && !message.isEmpty()){
            postMessage(new SocialMessageImpl(this,message));
        }
    }

    @Override
    public void postMessage(SocialMessage message) {
        messages.add(message);
        Collections.sort(messages);
    }

    @Override
    public void follows(SocialMember friend) {
        if(!friend.equals(this)){
            friends.add(friend);
        }
    }

    @Override
    public List<SocialMessage> getWallMessages() {
        final List<SocialMessage> wallMessages = new LinkedList<>(messages);
        for(SocialMember friend : friends){
            wallMessages.addAll(friend.getTimelineMessages());
        }
        Collections.sort(wallMessages);
        return wallMessages;
    }

    @Override
    public List<SocialMessage> getTimelineMessages() {
        return messages;
    }

    @Override
    public String getMemberName() {
        return memberName;
    }

}
