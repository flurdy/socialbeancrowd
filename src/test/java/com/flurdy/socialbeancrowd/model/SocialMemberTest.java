package com.flurdy.socialbeancrowd.model;

import com.flurdy.socialbeancrowd.infrastructure.SocialMemberRepository;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class SocialMemberTest {

    private SocialMember member;
    private SocialMemberRepository repository;

    @Before
    public void setUp(){
        repository = mock(SocialMemberRepository.class);
        member = new SocialMemberImpl(repository,"Alice");
    }

    @Test
    public void whenPostMessage_givenEmptyMessage_thenNotAdded() throws Exception {

        member.postMessage("");

        assertTrue(member.getTimelineMessages().isEmpty());
    }

    @Test
    public void whenPostMessage_givenMessage_thenAdded() throws Exception {

        member.postMessage("Hello Guvnor");

        assertEquals("Hello Guvnor (now)", member.getTimelineMessages().get(0).getPostMessage());
    }

    @Test
    public void whenProcessMemberAction_givenPostAction_thenNoOutput() throws Exception {

        final Deque<String> input = new LinkedList<>( Arrays.asList("->","Hello","World") );

        final List<String> output = member.processMemberAction(input);

        assertTrue(output.isEmpty());
    }

    @Test
    public void whenProcessMemberAction_givenPostAction_thenAdded() throws Exception {

        final Deque<String> input = new LinkedList<>( Arrays.asList("->","Hello","World") );

        member.processMemberAction(input);

        assertEquals("Hello World (now)", member.getTimelineMessages().get(0).getPostMessage());
    }

    @Test
    public void whenProcessMemberAction_givenFollowsActionWithFriendName_thenNoOutput() throws Exception {

        final Deque<String> input = new LinkedList<>( Arrays.asList("follows","Peter") );

        final List<String> output = member.processMemberAction(input);

        assertTrue(output.isEmpty());
    }

    @Test
    public void whenProcessMemberAction_givenFollowsActionWithUnknownFriendName_thenNoOutput() throws Exception {

        final SocialMemberImpl member = new SocialMemberImpl(repository,"Alice");
        final Deque<String> input = new LinkedList<>( Arrays.asList("follows","Luke") );

        final List<String> output = member.processMemberAction(input);

        assertTrue(output.isEmpty());
        assertTrue(member.getFriends().isEmpty());
    }

    @Test
    public void whenProcessMemberAction_givenFollowsActionWithFriendName_thenFollowsFriend() throws Exception {

        final SocialMemberImpl member = new SocialMemberImpl(repository,"Alice");
        final SocialMember peter = mock(SocialMember.class);
        when(repository.findMember(eq("Peter"))).thenReturn(peter);

        final Deque<String> input = new LinkedList<>( Arrays.asList("follows","Peter") );

        member.processMemberAction(input);

        assertTrue(member.getFriends().contains(peter));
    }

    @Test
    public void whenFollows_givenFriend_thenFollowsFriend() throws Exception {

        final SocialMemberImpl member = new SocialMemberImpl(repository,"Alice");
        final SocialMember peter = mock(SocialMember.class);

        member.follows(peter);

        assertTrue(member.getFriends().contains(peter));
    }

    @Test
    public void whenFollows_givenYourself_thenNotFollowsYourself() throws Exception {

        final SocialMemberImpl alice1 = new SocialMemberImpl(repository,"Alice");

        alice1.follows(alice1);

        assertTrue(alice1.getFriends().isEmpty());
    }

    @Test
    public void whenProcessMemberAction_givenMemberAndWallAction_thenListsWallPosts() throws Exception {

        final Deque<String> input = new LinkedList<>( Arrays.asList("wall") );
        member.postMessage("Hello world");
        member.postMessage("Hello another World");

        final List<String> output = member.processMemberAction(input);

        assertEquals(2, output.size());
    }

    @Test
    public void whenGetWallMessages_givenSeveralFriends_thenListsWallPostsFromAllFriends() throws Exception {

        final SocialMember peter = new SocialMemberImpl(repository,"Peter");
        when(repository.findMember(eq("Peter"))).thenReturn(peter);
        final SocialMember rosie = new SocialMemberImpl(repository,"Rose");
        when(repository.findMember(eq("Rosie"))).thenReturn(peter);

        member.follows(peter);
        member.follows(rosie);

        member.postMessage(new SocialMessageImpl("Alice","Hello world", DateTime.now().minusSeconds(10)));
        member.postMessage(new SocialMessageImpl("Alice","Hello another", DateTime.now().minusMinutes(2)));
        peter.postMessage( new SocialMessageImpl("Peter","Hello Me", DateTime.now().minusSeconds(20)));
        peter.postMessage( new SocialMessageImpl("Peter","Hello Peter", DateTime.now().minusMinutes(1)));
        rosie.postMessage( new SocialMessageImpl("Rosie","Hello Rosie", DateTime.now().minusSeconds(5)));

        final List<SocialMessage> output = member.getWallMessages();

        assertEquals(5, output.size());
    }

    @Test
    public void whenGetWallMessages_givenSeveralFriends_thenListsWallPostsInOrder() throws Exception {

        final SocialMember peter = new SocialMemberImpl(repository,"Peter");
        when(repository.findMember(eq("Peter"))).thenReturn(peter);
        final SocialMember rosie = new SocialMemberImpl(repository,"Rose");
        when(repository.findMember(eq("Rosie"))).thenReturn(peter);
        final RelativeTimeFormatter seconds5  = mock(RelativeTimeFormatter.class);
        final RelativeTimeFormatter seconds10 = mock(RelativeTimeFormatter.class);
        final RelativeTimeFormatter seconds20 = mock(RelativeTimeFormatter.class);
        final RelativeTimeFormatter minute1   = mock(RelativeTimeFormatter.class);
        final RelativeTimeFormatter minutes2  = mock(RelativeTimeFormatter.class);
        when(seconds5.format(any(DateTime.class)) ).thenReturn("5 seconds ago");
        when(seconds10.format(any(DateTime.class))).thenReturn("10 seconds ago");
        when(seconds20.format(any(DateTime.class))).thenReturn("20 seconds ago");
        when(minute1.format(any(DateTime.class))  ).thenReturn("1 minute ago");
        when(minutes2.format(any(DateTime.class)) ).thenReturn("2 minutes ago");

        member.follows(peter);
        member.follows(rosie);

        member.postMessage(new SocialMessageImpl(
                "Alice","Hello world", DateTime.now().minusSeconds(10), seconds10 ));
        member.postMessage(new SocialMessageImpl(
                "Alice","Hello another", DateTime.now().minusMinutes(2), minutes2));
        peter.postMessage( new SocialMessageImpl(
                "Peter","Hello Me", DateTime.now().minusSeconds(20), seconds20));
        peter.postMessage( new SocialMessageImpl(
                "Peter","Hello Peter", DateTime.now().minusMinutes(1), minute1));
        rosie.postMessage( new SocialMessageImpl(
                "Rosie","Hello Rosie", DateTime.now().minusSeconds(5), seconds5));

        final List<SocialMessage> output = member.getWallMessages();

        assertEquals("Rosie - Hello Rosie (5 seconds ago)",output.get(0).getWallMessage());
        assertEquals("Alice - Hello world (10 seconds ago)",output.get(1).getWallMessage());
        assertEquals("Alice - Hello another (2 minutes ago)",output.get(4).getWallMessage());
    }

}
