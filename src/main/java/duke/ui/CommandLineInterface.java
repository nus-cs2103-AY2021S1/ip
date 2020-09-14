package duke.ui;

import java.util.Scanner;

/**
 * Class to handle Commandline interface, implements UserInterface contract.
 */
public class CommandLineInterface implements UserInterface {
    private static final String logo = "\tHello from\n"
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"
            + "\tHello! %s I'm Duke\n\tWhat can I do for you "
            + "\n";
    private static final String goodbye = "Bye %s! Hope to see you again soon!\n";
    private static final String linebreaker = "_".repeat(30) + "\n";
    private boolean isChatbotRunning;
    private String userName;
    private final Scanner scanner;

    /**
     * Constructs the CommandLineInterface UI for use without a GUI
     */
    public CommandLineInterface() {
        scanner = new Scanner(System.in);
        isChatbotRunning = false;
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
        isChatbotRunning = true;
        userName = userName;
        systemMessage(String.format(logo, userName));
    }

    @Override
    public void close() {
        assert isChatbotRunning : "CommandLineInterface should only end once";
        this.scanner.close();
        this.isChatbotRunning = false;
        systemMessage(String.format(goodbye, userName));
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
        System.out.print("\t" + linebreaker + indent(message) + linebreaker);
    }
    /**
     * Indents text
     * @param s text to indent
     * @return indented text
     */
    private String indent(String s) {
        return "    " + s.replace("\n", "\n\t");
    }
}
