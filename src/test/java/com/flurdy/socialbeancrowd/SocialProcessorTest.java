package com.flurdy.socialbeancrowd;


import com.flurdy.socialbeancrowd.infrastructure.SocialMemberRepository;
import com.flurdy.socialbeancrowd.io.SocialOutputter;
import com.flurdy.socialbeancrowd.model.SocialMember;
import com.flurdy.socialbeancrowd.model.SocialMemberImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Deque;

import static org.mockito.Mockito.*;

public class SocialProcessorTest {

    private SocialProcessor processor;
    private SocialOutputter outputter;
    private SocialMemberRepository repository;

    @Before
    public void setUp(){
        outputter  = mock(SocialOutputter.class);
        repository = mock(SocialMemberRepository.class);
        processor  = new SocialProcessorImpl(outputter, repository);
    }

    @Test
    public void whenProcessAction_givenMemberName_thenFindsMember() throws Exception {
        final SocialMember member = mock(SocialMember.class);
        when(repository.findOrCreateMember(anyString())).thenReturn(member);

        processor.processAction("Alice -> Hello");

        verify(repository,atLeastOnce()).findOrCreateMember(eq("Alice"));
    }

    @Test
    public void whenProcessAction_givenMember_thenDelegatesToprocessMemberAction() throws Exception {
        final SocialMember member = mock(SocialMember.class);
        when(repository.findOrCreateMember(anyString())).thenReturn(member);

        processor.processAction("Alice -> Hello");

        verify(member,atLeastOnce()).processMemberAction(any(Deque.class));
    }

}
