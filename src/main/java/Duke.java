import java.io.IOException;
import java.util.Scanner;

import exception.DukeException;
import operation.Operation;
import parser.CommandParser;
import task.TaskList;
import storage.TaskStorage;

public class Duke {
    private TaskStorage taskStorage;
    private TaskList taskList;

    private static final String DIVIDER = "---------------------------------------------------------";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private void initialiseDuke() {
        this.taskStorage = TaskStorage.createTaskStorage();
        this.taskList = this.taskStorage.loadTaskList();
    }

    private void printStartMessage() {
        String message = "Greetings, what may I do for you?";
        System.out.println(Duke.LOGO + message);
    }

    public void runDuke() {
        printStartMessage();
        initialiseDuke();

        boolean isExit = false;
        Scanner userInput = new Scanner(System.in);
        CommandParser commandParser = new CommandParser();

        while (!isExit) {
            System.out.printf("\n");
            String command = userInput.nextLine();

            System.out.println(Duke.DIVIDER);
            try {
                Operation operation = commandParser.parse(command, this.taskList, this.taskStorage);
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
