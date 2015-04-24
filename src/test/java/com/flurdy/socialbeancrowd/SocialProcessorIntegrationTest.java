package com.flurdy.socialbeancrowd;

import com.flurdy.socialbeancrowd.infrastructure.SocialMemberRepository;
import com.flurdy.socialbeancrowd.infrastructure.SocialMemberRepositoryImpl;
import com.flurdy.socialbeancrowd.io.MockOutputter;
import org.junit.Before;
import org.junit.Test;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class SocialProcessorIntegrationTest {

    private SocialMemberRepository repository;
    private MockOutputter outputter;
    private SocialProcessor processor;

    @Before
    public void setUp(){
        outputter  = new MockOutputter();
        repository = new SocialMemberRepositoryImpl();
        processor  = new SocialProcessorImpl(outputter, repository);
    }

    @Test
    public void whenProcessAction_givenMemberAndFriends_thenGetWall() throws Exception {

        processor.processAction("Alice -> Hello");
        TimeUnit.MILLISECONDS.sleep(50);
        processor.processAction("Alice -> Hello again");
        TimeUnit.MILLISECONDS.sleep(50);
        processor.processAction("Alice follows John");
        processor.processAction("Peter -> Hello Alice");
        processor.processAction("Alice follows Peter");
        TimeUnit.MILLISECONDS.sleep(50);
        processor.processAction("Alice -> Hello Peter");
        processor.processAction("Alice wall");

        assertEquals(4, outputter.history.size());
        assertTrue(outputter.history.get(0).startsWith("Alice - Hello Peter ("));
        assertTrue(outputter.history.get(1).startsWith("Peter - Hello Alice ("));
        assertTrue(outputter.history.get(2).startsWith("Alice - Hello again ("));
        assertTrue(outputter.history.get(3).startsWith("Alice - Hello ("));
    }

    @Test
    public void whenProcessAction_givenFollowsBeforeCreated_thenIgnoresFriend() throws Exception {

        processor.processAction("Alice -> Hello");
        processor.processAction("Alice follows John"); // Will be ignored
        TimeUnit.MILLISECONDS.sleep(50);
        processor.processAction("John -> Hello Alice");
        TimeUnit.MILLISECONDS.sleep(50);
        processor.processAction("Peter -> Hello Alice");
        TimeUnit.MILLISECONDS.sleep(50);
        processor.processAction("Alice follows Peter");
        processor.processAction("Alice wall");

        assertEquals(2, outputter.history.size());
        assertTrue(outputter.history.get(0).startsWith("Peter - Hello Alice ("));
        assertTrue(outputter.history.get(1).startsWith("Alice - Hello ("));
    }

}
