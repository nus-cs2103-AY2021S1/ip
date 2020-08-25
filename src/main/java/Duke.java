package main.java;

import main.java.command.Commands;
import main.java.exception.InvalidArgumentException;
import main.java.exception.UserException;
import main.java.misc.Parser;
import main.java.misc.Ui;
import main.java.task.TaskList;

public class Duke {

    /**
     * Run the program.
     */
    public static void run() {
        TaskList taskList = new TaskList();
        Ui.start();
        try {
            taskList.initialize();
        } catch (InvalidArgumentException e) {
            Ui.wrap(() -> System.out.println("Error parsing file"));
        }
        String input = "";

        do {
            try {
                input = Ui.feed();
                Commands.create(Parser.parseCommand(input)).run(taskList);
            } catch (UserException e) {
                Ui.wrap(() -> System.out.println(e.getMessage()));
            } catch (Exception e) {
                System.out.println("Unhandled exception:");
                System.out.println(e.getMessage());
            }
        }
        while (!input.equals("bye"));
    }

    public static void main(String[] args) {
        run();
    }
}











