package com.flurdy.socialbeancrowd.model;


import org.joda.time.DateTime;

public class SocialMessageImpl implements SocialMessage {

    private final DateTime timestamp;
    private final String message;
    private final String memberName;

    public SocialMessageImpl(String memberName, String message) {
        this.timestamp  = DateTime.now();
        this.message    = message;
        this.memberName = memberName;
    }

    public SocialMessageImpl(String memberName, String message, DateTime timestamp) {
        this.timestamp  = timestamp;
        this.message    = message;
        this.memberName = memberName;
    }

    public SocialMessageImpl(SocialMember member, String message) {
        this.timestamp  = DateTime.now();
        this.message    = message;
        this.memberName = member.getMemberName();
    }

    @Override
    public String getPostMessage() {
        return null;
    }

    @Override
    public String getWallMessage() {
        return null;
    }

    @Override
    public DateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public int compareTo(Object that) {
        if(that instanceof SocialMessage){
            final SocialMessage thatMessage = (SocialMessage) that;
            if(thatMessage.getTimestamp().isBefore(timestamp)){
                return -1;
            } else if(thatMessage.getTimestamp().isAfter(timestamp)){
                return 1;
            }
        }
        return 0;
    }
}
