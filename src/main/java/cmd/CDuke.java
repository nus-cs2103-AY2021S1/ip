package cmd;

import command.Command;
import task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Command line application for Duke (Command line UI)
 */
public class CDuke {

    private static final String LOGO = ""
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING = "Hello! I'm Duke\nLet's have a conversation!";
    private static final String ENDING_GREETING = "Bye. Hope to see you again soon!";

    private final List<Task> taskList;
    private final Scanner scanner;

    public CDuke() {
        this.taskList = new ArrayList<>(100);
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main execution point for Duke commandline
     */
    public void run() {

        System.out.println("Hello from\n" + LOGO);
        System.out.println(GREETING);

        while(true) {

            // Prompt for input
            String input = scanner.nextLine();
            if (input.isBlank()) continue;

            // Look up command and execute
            Command c = Parser.parse(taskList, input);
            c.execute();

            // Exit CDuke
            if (c.isExit()) break;
        }

        System.out.println(ENDING_GREETING);
    }
}
