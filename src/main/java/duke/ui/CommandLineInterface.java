package duke.ui;

import java.util.Scanner;

public class CommandLineInterface implements UserInterface{
    private static final String logo = "\tHello from\n"
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"
            + "\tHello! %s I'm duke.Duke\n\tWhat can I do for you "
            + "\n";
    private static final String goodbye = "Bye %s! Hope to see you again soon!";
    private static final String linebreaker = "_".repeat(30) + "\n";
    
    private boolean isChatbotRunning;
    private String userName;
    private final Scanner scanner;
    public CommandLineInterface(){
        this.scanner = new Scanner(System.in);
        this.isChatbotRunning = false;
    }
    @Override
    public boolean isRunning() {
        return isChatbotRunning;
    }

    
    /**
     * Greeting from Duke Bot and set username of user
     * @param userName Name of the user
     */
    @Override
    public void start(String userName) {
        assert !isChatbotRunning : "CommandLineInterface should only start once"; 
        this.isChatbotRunning = true;
        this.userName = userName;
        systemMessage(String.format(logo, userName));
    }

    @Override
    public void close() {
        assert isChatbotRunning : "CommandLineInterface should only end once";
        this.scanner.close();
        this.isChatbotRunning = false;
        systemMessage(String.format(goodbye,userName));
    }

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }

    /**
     * Pass Message into the System for display as text
     * @param message from duke to human
     */
    @Override
    public void systemMessage(String message) {
        System.out.print("\t"+linebreaker + indent(message) + linebreaker);
    }
    
    /**
     * Indents text
     * @param s text to indent
     * @return indented text
     */
    private String indent(String s) {
        return "    " + s.replace("\n","\n\t");
    }
}
