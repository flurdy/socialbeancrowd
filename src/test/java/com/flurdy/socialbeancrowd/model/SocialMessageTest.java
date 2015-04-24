package com.flurdy.socialbeancrowd.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class SocialMessageTest {

    private SocialMessage message;

    @Before
    public void setUp(){
        message = new SocialMessageImpl("Alice","Hello World");
    }

    @Test
    public void whenGetPostMessage_givenMessage_thenMatch() throws Exception {

        final String postMessage = message.getPostMessage();

        assertEquals("Hello World (now)",postMessage);
    }


    @Test
    public void whenGetWallMessage_givenMessage_thenMatch() throws Exception {

        final String wallMessage = message.getWallMessage();

        assertEquals("Alice - Hello World (now)",wallMessage);
    }


}
