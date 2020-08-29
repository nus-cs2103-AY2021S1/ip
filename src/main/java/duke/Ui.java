package duke;

import java.util.Scanner;

/**
 * UI class.
 * Handles input from user.
 * Contains task list, storage and parser.
 *
 * @author YanCheng
 */
public class Ui {

    private TaskList taskList;
    private Storage storage;
    private Parser parser;

    /**
     * Constructor for Ui class.
     * @param taskList A TaskList object
     * @param storage A Storage object
     * @param parser A Parser object
     */
    public Ui(TaskList taskList, Storage storage, Parser parser) {
        this.taskList = taskList;
        this.storage = storage;
        this.parser = parser;
    }

    /**
     * Prompts user for input and delegates input to Parser.
     * For use with CLI.
     */
    public void echo() {

        Scanner sc = new Scanner(System.in);

        // read duke.txt file and initialise taskList
        try {
            storage.init();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(taskList.listOut());
            } else if (input.contains("find")) {
                System.out.println(taskList.find(input));
            } else if (input.contains("done")) {
                try {
                    System.out.println(taskList.done(input));
                    storage.save();
                } catch (DukeException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     " + e.getMessage());
                    System.out.println("    ____________________________________________________________");
                    System.out.println();
                }
            } else if (input.contains("delete")) {
                try {
                    System.out.println(taskList.delete(input));
                    storage.save();
                } catch (DukeException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     " + e.getMessage());
                    System.out.println("    ____________________________________________________________");
                    System.out.println();
                }
            } else {

                try {
                    Task task;
                    if (input.contains("todo")) {
                        task = parser.handleToDo(input);
                    } else if (input.contains("deadline")) {
                        task = parser.handleDeadline(input);
                    } else if (input.contains("event")) {
                        task = parser.handleEvent(input);
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }

                    System.out.println(taskList.add(task));
                    storage.save();
                } catch (DukeException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     " + e.getMessage());
                    System.out.println("    ____________________________________________________________");
                    System.out.println();
                }
            }
        }
        sc.close();
    }

    /**
     * Reads input from the user through the GUI and parses the input.
     * For use with Duke GUI.
     * @param input User input
     * @return Output to be displayed to user
     */
    public String readCommand(String input) {

        if (input.equals("bye")) {
            return "Have a nice day";
        } else if (input.equals("list")) {
            return taskList.listOut();
        } else if (input.contains("find")) {
            return taskList.find(input);
        } else if (input.contains("done")) {
            try {
                String output = taskList.done(input);
                storage.save();
                return output;
            } catch (DukeException e) {
                return e.getMessage();
            }
        } else if (input.contains("delete")) {
            try {
                String output = taskList.delete(input);
                storage.save();
                return output;
            } catch (DukeException e) {
                return e.getMessage();
            }
        } else {
            try {
                Task task;
                if (input.contains("todo")) {
                    task = parser.handleToDo(input);
                } else if (input.contains("deadline")) {
                    task = parser.handleDeadline(input);
                } else if (input.contains("event")) {
                    task = parser.handleEvent(input);
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                String output = taskList.add(task);
                storage.save();
                return output;
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
    }

    /**
     * Greeting used by Duke.
     */
    public void greet() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    /**
     * Farewell used by Duke.
     */
    public void exit() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
