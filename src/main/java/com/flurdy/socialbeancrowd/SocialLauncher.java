package com.flurdy.socialbeancrowd;

// import java.util.InputMismatchException;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import com.flurdy.socialbeancrowd.io.*;
import com.flurdy.socialbeancrowd.infrastructure.*;


public class SocialLauncher {

    // private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Main method to start Social Bean Crowd application from the command line
     * Will trigger further methods to read names and actions from command line
     * @param args not used
     */
    public static void main(String args[]){
        final SocialInputter  inputter  = new SocialInputterImpl(System.in);
        final SocialOutputter outputter = new SocialOutputterImpl(System.out);
        final SocialMemberRepository repository = new SocialMemberRepositoryImpl();
        final SocialProcessor processor = new SocialProcessorImpl(outputter,repository);
        final SocialLauncher  launcher  = new SocialLauncher();
        outputter.printLine("Welcome to Social Bean Crowd. Enter a blank line to finish");
        launcher.readInputAndProcess(processor, inputter, outputter);
        outputter.printLine("Social Bean Crowd Finished");
    }

    /**
     *
     * @param processor The current processor to parse actions.
     * @param inputer A wrapper for the input stream to read the names and actions from.
     * @param outputer A wrapper for the output stream to print application output.
     */
    protected void readInputAndProcess(SocialProcessor processor, SocialInputter inputter, SocialOutputter outputter){
        try {
            String input;
            while(!(input=inputter.readNextSocialAction()).equals("")){
                processor.processAction(input);
            }
        } catch (Exception exception) {
            System.err.println("Unfortunately the application threw an error: "+exception.getMessage());
            exception.printStackTrace();
        }
    }
   
}
