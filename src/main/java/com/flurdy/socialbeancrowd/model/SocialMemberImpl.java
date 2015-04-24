package com.flurdy.socialbeancrowd.model;


import com.flurdy.socialbeancrowd.infrastructure.*;
import com.google.common.base.Joiner;
import java.util.*;

public class SocialMemberImpl implements SocialMember {

    private final String memberName;
    private final LinkedList<SocialMessage> messages  = new LinkedList<>();
    private final Set<SocialMember> friends           = new HashSet<>();
    private final SocialMessageFactory messageFactory = new SocialMessageFactoryImpl();
    private final Joiner joiner = Joiner.on(" ");
    private final SocialMemberRepository repository;

    public SocialMemberImpl(SocialMemberRepository repository, String memberName) {
        this.memberName = memberName;
        this.repository = repository;
    }

    @Override
    public void postMessage(String message) {
        if(message != null && !message.isEmpty()){
            postMessage(messageFactory.newMessage(this, message));
        }
    }

    private void postMessage(Deque<String> input) {
        if(!input.isEmpty()){
            postMessage(joiner.join(input));
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
        final LinkedList<SocialMessage> wallMessages = new LinkedList<>(messages);
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

    protected Set<SocialMember> getFriends(){
        return friends;
    }

    @Override
    public List<String> processMemberAction(Deque<String> input){
        final List<String> lines = new ArrayList<>();
        if( input.isEmpty() ){
            lines.addAll(convertTimelineMessages());
        } else {
            final String action = input.pop();
            if( "wall".equals(action.toLowerCase()) ){
                lines.addAll(convertWallMessages());
            } else if( "->".equals(action) ){
                postMessage(input);
            } else if( "follows".equals(action) ){
                followFriend(input);
            }
        }
        return lines;
    }

    private List<String> convertTimelineMessages(){
        final List<String> lines = new ArrayList<>();
        for(SocialMessage message : getTimelineMessages()){
            lines.add(message.getPostMessage());
        }
        return lines;
    }

    private List<String> convertWallMessages(){
        final List<String> lines = new ArrayList<>();
        for(SocialMessage message : getWallMessages()){
            lines.add(message.getWallMessage());
        }
        return lines;
    }

    private void followFriend(Deque<String> input) {
        if(!input.isEmpty()){
            final String friendName   = input.pop();
            final SocialMember friend = repository.findMember(friendName);
            if(friend != null){
                follows(friend);
            }
        }
    }

}
