package com.flurdy.socialbeancrowd.model;

import com.flurdy.socialbeancrowd.infrastructure.SocialMemberRepository;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


public class SocialMemberVolumeTest {

    private SocialMember member;
    private SocialMemberRepository repository;

    @Before
    public void setUp(){
        repository = mock(SocialMemberRepository.class);
        member = new SocialMemberImpl(repository,"Alice");
    }

    @Test(timeout = 15000)
    public void whenGetWallMessages_givenManyFriendsAndManyMessage_thenRespondQuickly() throws Exception {
        final Random random = new Random();
        int i = 50000;
        while(i>0){
            i--;
            final SocialMember friend = new SocialMemberImpl(repository, "Friend"+i);
            member.follows(friend);
            int j = 5;
            while(j>0){
                j--;
                friend.postMessage(new SocialMessageImpl(
                        "Friend"+i,"Hello Me " + j, DateTime.now().minusSeconds( random.nextInt(5000) )) );
            }
        }
    }

}
