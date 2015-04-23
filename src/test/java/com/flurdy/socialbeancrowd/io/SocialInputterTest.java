package com.flurdy.socialbeancrowd.io;

import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class SocialInputterTest {

    private SocialInputter inputter;
    private InputStream inputStream;

    @Test
    public void whenReadNextSocialAction_givenAction_thenReturnAction() throws Exception {
        inputStream = new ByteArrayInputStream("Alice -> Hello".getBytes());
        inputter    = new SocialInputterImpl(inputStream);

        final String firstLine = inputter.readNextSocialAction();

        assertEquals("Alice -> Hello",firstLine);
    }

}
