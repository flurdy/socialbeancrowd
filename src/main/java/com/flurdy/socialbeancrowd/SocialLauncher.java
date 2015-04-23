package com.flurdy.socialbeancrowd;

import java.util.InputMismatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SocialLauncher {
    
    private transient Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Main method to start Social Bean Crowd application from the command line
     * Will trigger further methods to read names and actions from command line
     * @param args not used
     */
    public static void main(String args[]){
        final SocialBeanCrowd application = new SocialBeanCrowdImpl();
        final SocialInput     input       = new SocialInputImpl(System.in);
        final SocialLauncher  launcher    = new SocialLauncher();
        launcher.readInputAndLaunch(application, input);
    }

    /**
     *
     * @param application The current application to use.
     * @param input An wrapper for the input stream to read the names and actions from.
     */
    protected void readInputAndLaunch(SocialBeanCrowd application, SocialInput input){
        try {
     
            


        } catch (Exception exception) {
            System.err.println("Unfortunately the application threw an error: "+exception.getMessage());
            exception.printStackTrace();
        }
    }
   
}
