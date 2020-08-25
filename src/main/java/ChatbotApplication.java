import exceptions.DukeIOException;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

/**
 * Front End Facing Script for the UI.
 */
public class ChatbotApplication {
    /**
     * Execution Class to contain main loop
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //System.out.println("What is your name?");
        String path = System.getProperty("user.dir");
        Duke d = new Duke("##", path);
        // To refactor ChatbotApplication to hold mainloop such that UI elements to be added in future
        // can be interactive with the application through ChatbotApplication class directly. 
        d.dukeLoop(sc);
    }
    
}
