package com.flurdy.socialbeancrowd.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class SocialMemberTest {

    private SocialMember member;
    @Before
    public void setUp(){
        member = new SocialMemberImpl("Alice");
    }

    @Test
    public void whenPostMessage_givenEmptyMessage_thenNotAdded() throws Exception {

        member.postMessage("");

        assertTrue(member.getTimelineMessages().isEmpty());
    }

    @Test
    public void whenPostMessage_givenMessage_thenAdded() throws Exception {

        member.postMessage("Hello Guvnor");

        assertEquals("Hello Guvnor (now)",member.getTimelineMessages().get(0).getPostMessage());
    }
   
}
