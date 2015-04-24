package com.flurdy.socialbeancrowd.infrastructure;

import com.flurdy.socialbeancrowd.model.SocialMember;
import com.flurdy.socialbeancrowd.model.SocialMemberImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SocialMemberRepositoryTest {

    private SocialMemberRepository repository;

    @Before
    public void setUp(){
        repository = new SocialMemberRepositoryImpl();
    }

    @Test
    public void whenFindMember_givenMemberName_thenFindsMember() throws Exception {

        final SocialMember member = new SocialMemberImpl("Alice");

        repository.addMember(member);

        final SocialMember foundMember = repository.findMember("Alice");

        assertNotNull(foundMember);
        assertEquals("Alice",foundMember.getMemberName());
    }

    @Test
    public void whenFindMember_givenUnknownMemberName_thenReturnsNull() throws Exception {

        final SocialMember member = repository.findMember("John");

        assertNull(member);
    }


    @Test
    public void whenFindOrCreateMember_givenUnknownMemberName_thenCreatesMember() throws Exception {

        final SocialMember member = repository.findOrCreateMember("John");

        assertNotNull(member);
        assertEquals("John", member.getMemberName());
    }


    @Test
    public void whenFindOrCreateMember_givenKnownMemberName_thenFindsMember() throws Exception {

        final SocialMember memberToAdd = new SocialMemberImpl("Alice");
        repository.addMember(memberToAdd);

        final SocialMember memberFound = repository.findOrCreateMember("Alice");

        assertNotNull(memberFound);
        assertEquals("Alice", memberFound.getMemberName());
    }

}
