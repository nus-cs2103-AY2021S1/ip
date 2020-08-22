import java.util.Scanner;

import exception.DukeException;
import operation.Operation;
import parser.CommandParser;
import task.TaskList;

public class Duke {
    private final TaskList taskStorage;

    private static final String DIVIDER = "---------------------------------------------------------";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    Duke() {
        this.taskStorage = new TaskList();
    }

    private void printStartMessage() {
        String message = "Greetings, what may I do for you?";
        System.out.println(Duke.LOGO + message);
    }

    public void runDuke() {
        printStartMessage();

        boolean isExit = false;
        Scanner userInput = new Scanner(System.in);
        CommandParser commandParser = new CommandParser();

        while (!isExit) {
            System.out.printf("\n");
            String command = userInput.nextLine();

            System.out.println(Duke.DIVIDER);
            try {
                Operation operation = commandParser.parse(command, this.taskStorage);
                operation.execute();
                isExit = operation.isExit();
            } catch (DukeException exception) {
                System.out.println(exception.getMessage());
            }
            System.out.println(Duke.DIVIDER);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.runDuke();
    }
}
