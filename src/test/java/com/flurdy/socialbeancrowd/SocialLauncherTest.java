package com.flurdy.socialbeancrowd;

import org.junit.*;
import com.flurdy.socialbeancrowd.io.*;

import static org.mockito.Mockito.*;


public class SocialLauncherTest {

   private SocialLauncher launcher;
   private SocialInputter inputter;
   private SocialOutputter outputter;
   private SocialProcessor processor;

   @Before
   public void setUp(){
      inputter  = mock(SocialInputter.class);
      outputter = mock(SocialOutputter.class);
      processor = mock(SocialProcessor.class);
      launcher  = new SocialLauncher();
   }  

   @Test
   public void whenReadInputAndProcess_thenCallsInputtersReadNextSocialAction() throws Exception {

      when(inputter.readNextSocialAction()).thenReturn("");

      launcher.readInputAndProcess(processor,inputter,outputter);

      verify(inputter,atLeastOnce()).readNextSocialAction();
   }  

   @Test
   public void whenReadInputAndProcess_givenNextSocialAction_thenCallsProcessorProcessAction() throws Exception {

      when(inputter.readNextSocialAction()).thenReturn("Alice -> Hello").thenReturn("");

      launcher.readInputAndProcess(processor,inputter,outputter);

      verify(processor,times(1)).processAction(eq("Alice -> Hello"));
   }   

   @Test
   public void whenReadInputAndProcess_givenEmptyString_thenDoesNothing() throws Exception {

      when(inputter.readNextSocialAction()).thenReturn("");

      launcher.readInputAndProcess(processor,inputter,outputter);

      verify(processor,never()).processAction(anyString());
   }   

   
}
