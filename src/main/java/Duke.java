import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class Duke {

    public static final String LOGO = " ____        _        \n"
                                      + "|  _ \\ _   _| | _____ \n"
                                      + "| | | | | | | |/ / _ \\\n"
                                      + "| |_| | |_| |   <  __/\n"
                                      + "|____/ \\__,_|_|\\_\\___|\n";
    public static final Reader INPUTSTREAMREADER = new InputStreamReader(System.in);
    public static final BufferedReader READER = new BufferedReader(INPUTSTREAMREADER);
    public static final ArrayList<String> STORAGE = new ArrayList<String>();

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
            if (command.equals("list")) {
                int index = 1;
                for (String inputString: STORAGE) {
                    System.out.println(index + ". " + inputString);
                    index++;
                }
            } else {
                System.out.println("added: " + command);
                STORAGE.add(command);
            }
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