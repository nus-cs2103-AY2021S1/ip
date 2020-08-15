import java.util.Scanner;

import operation.Operation;
import parser.CommandParser;
import storage.TaskStorage;

public class Duke {

    private final TaskStorage taskStorage;
    private static String DIVIDER = "-------------------------------------------------";
    private static String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private Duke() {
        this.taskStorage = new TaskStorage();
    }

    private void printStartMessage() {
        String message = "Greetings, what may I do for you?";
        System.out.println(Duke.LOGO + message);
    }

    public void runDuke() {
        printStartMessage();

        boolean done = false;
        Scanner userInput = new Scanner(System.in);
        CommandParser commandParser = new CommandParser();

        while (!done) {
            System.out.printf("\n");
            String command = userInput.nextLine();

            System.out.println(Duke.DIVIDER);
            Operation operation = commandParser.parse(command, this.taskStorage);
            operation.execute();
            System.out.println(Duke.DIVIDER);

            if (operation.isExit()) {
                done = true;
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.runDuke();
    }
}
