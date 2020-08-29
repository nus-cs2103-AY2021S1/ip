import exceptions.DukeIOException;
import exceptions.DukeUnknownException;

import java.util.Scanner;

/**
 * Front End Facing Script for the UI of ChatbotApplication
 */
public class ChatbotApplication {
    Duke dukeProgram;
    boolean isChatbotRunning;
    private final String linebreaker;
    Scanner sc;

    /**
     * Constructor class of the ChatbotApplication
     * @param linebreaker the display aesthetic of the output from Duke
     * @param pth the path to read a file from.
     */
    ChatbotApplication(String linebreaker, String pth){
        dukeProgram = new Duke(linebreaker,pth);
        isChatbotRunning = true;
        this.linebreaker = linebreaker.repeat(50)+"\n";
    }

    /**
     * Takes a scanner object as user input and initialises the dukeLoop
     * @param sc UserInput for the Application.
     */
    public void dukeLoop(Scanner sc){
        print("Please Enter your name");
        String name = sc.nextLine();
        print(dukeProgram.greeting(name));
        String in = "";
        String out = "";
        while (this.isRunning()){
            in = sc.nextLine();
            try {
                out = dukeProgram.takeInput(in);
                if (Boolean.parseBoolean(out)){
                    this.setChatbotRunning(false);
                }else{
                    print(out);
                }
            } catch (DukeUnknownException e){
                print(e.toString());
            }
        }
        try {
            dukeProgram.saveTasks();
        } catch (DukeIOException e) {
            print(e.toString());
        }
        sc.close();
        print(dukeProgram.goodbye(name));
    }
    
    /**
     * Running state of the Duke Application
     * @return State of Duke running
     */
    private boolean isRunning() {
        return this.isChatbotRunning;
    }
    /**
     * Wraps all text output and prints to the console
     * @param s String output
     */
    private void print(String s){
        System.out.printf("%s%s\n%s%n",linebreaker,s,linebreaker);
    }

    /**
     * Setter for the status of the Chatbot Object
     * @param b toggle on or off for chatbot
     */
    private void setChatbotRunning(boolean b){
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
        // To refactor ChatbotApplication to hold mainloop such that UI elements to be added in future
        // can be interactive with the application through ChatbotApplication class directly. 
        d.dukeLoop(sc);
    }
}
