import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

public class Duke {
    public static final String LOGO = " ____        _        \n"
                                      + "|  _ \\ _   _| | _____ \n"
                                      + "| | | | | | | |/ / _ \\\n"
                                      + "| |_| | |_| |   <  __/\n"
                                      + "|____/ \\__,_|_|\\_\\___|\n";
    public static final Reader INPUTSTREAMREADER = new InputStreamReader(System.in);
    public static final BufferedReader READER = new BufferedReader(INPUTSTREAMREADER);

    public static void main(String[] args) throws Exception {
        executeProgram();
    }

    private static void executeProgram() throws Exception {
        printWelcomeMessage(LOGO);
        String command = READER.readLine();
        parseCommands(command);
        printByeMessage();
    }

    private static void printWelcomeMessage(String LOGO) {
        System.out.println("Hello there! My name is\n" + LOGO + "\nWhat can I do for you?");
    }

    private static void parseCommands(String command) throws Exception {
        while (command != null && !command.equals("bye")) {
            printBorder();
            System.out.println(command);
            command = READER.readLine();
        }
    }

    private static void printBorder() {
        System.out.print("---------------------------\n");
    }

    private static void printByeMessage() {
        printBorder();
        System.out.println("Bye. Hope to see you again soon!");
    }
}