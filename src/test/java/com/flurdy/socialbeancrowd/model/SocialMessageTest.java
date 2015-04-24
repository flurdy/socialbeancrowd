package com.flurdy.socialbeancrowd.model;

import org.joda.time.DateTime;
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


    @Test
    public void whenCompareTo_givenOlderMessage_thenNegative() throws Exception {

        final SocialMessage olderMessage = new SocialMessageImpl(
                "Alice","Hello Old World", DateTime.now().minusHours(5));

        final int comparison = message.compareTo(olderMessage);

        assertEquals(-1,comparison);
    }

    @Test
    public void whenCompareTo_givenNewerMessage_thenNegative() throws Exception {

        final SocialMessage newerMessage = new SocialMessageImpl(
                "Alice","Hello Old World", DateTime.now().minusHours(5));

        final int comparison = newerMessage.compareTo(message);

        assertEquals(1,comparison);
    }


}
