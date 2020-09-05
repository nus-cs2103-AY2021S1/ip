package duke;

import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.exceptions.DukeIoException;
import duke.exceptions.DukeUnknownException;



/**
 * Front End Facing Script for the UI of duke.ChatbotApplication
 */
public class ChatbotApplication {
    private final String linebreaker;
    private final Duke dukeProgram;
    private boolean isChatbotRunning;
    private String userName;
    /**
     * Constructor class of the duke.ChatbotApplication
     * @param linebreaker the display aesthetic of the output from duke.Duke
     * @param pth the path to read a file from.
     */
    public ChatbotApplication(String linebreaker, String pth) {
        dukeProgram = new Duke(pth);
        isChatbotRunning = true;
        this.linebreaker = linebreaker.repeat(50) + "\n";
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * In
     * @return
     */
    public String initialiseApplication(){
        return wrap("Please Enter your UserName:");
    }
    /**
     * Greets the user by given username
     * @return Greeting from Duke to the user
     */
    public String greeting(){
        return dukeProgram.greeting(this.userName);
    }

    /**
     * Takes input for one iteration of DukeLoop
     * @param input User Input
     * @return String to output to Commandline
     */
    public String takeInput(String input){
        String out = "";
        try {
            out = dukeProgram.takeInput(input);
            if (Boolean.parseBoolean(out)) {
                this.setChatbotRunning(false);
            }
        } catch (DukeUnknownException e) {
            out = e.toString();
            this.setChatbotRunning(false);
        } catch (DukeException e) {
            out = e.toString();
        } 
        return out;
    }
    
    /**
     * Takes a scanner object as user input and initialises the dukeLoop when running off GUI in Text
     * @param sc UserInput for the Application.
     */
    public void dukeLoop(Scanner sc) {
        initialiseApplication();
        setUserName(sc.nextLine());
        print(greeting());
        String in = "";
        String out = "";
        while (this.isRunning()) {
            in = sc.nextLine();
            print(takeInput(in));
        }
        try {
            dukeProgram.saveTasks();
        } catch (DukeIoException e) {
            print(e.toString());
        }
        sc.close();
        print(dukeProgram.goodbye(userName));
    }

    /**
     * Running state of the duke.Duke Application
     * @return State of duke.Duke running
     */
    private boolean isRunning() {
        return this.isChatbotRunning;
    }

    /**
     * Wraps all text output and prints to the console
     * @param s String output
     */
    private void print(String s) {
        System.out.printf("%s%s\n%s%n", linebreaker, s, linebreaker);
    }
    private String wrap(String s) {
        return String.format("%s%s\n%s%n", linebreaker, s, linebreaker);
    }

    /**
     * Setter for the status of the Chatbot Object
     * @param b toggle on or off for chatbot
     */
    private void setChatbotRunning(boolean b) {
        this.isChatbotRunning = b;
    }
    /**
     * Execution Class to contain main loop
     * @param args args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //System.out.println("What is your name?");
        String path = System.getProperty("user.dir");
        ChatbotApplication d = new ChatbotApplication("##", path);
        // To refactor duke.ChatbotApplication to hold mainloop such that UI elements to be added in future,
        // can be interactive with the application through duke.ChatbotApplication class directly.
        d.dukeLoop(sc);
    }
}
