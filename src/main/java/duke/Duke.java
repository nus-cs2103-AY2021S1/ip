package duke;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import exception.DukeErrorException;
import exception.InvalidDeadlineException;
import exception.InvalidEventException;
import exception.InvalidTodoException;
import ui.Ui;


/**
 * Main class for the Duke application
 */
public class Duke {
    // Scanner to read input
    private Scanner readSc;
    // Scanner to give input
    private Scanner inputSc;
    // List of tasks
    private ArrayList<Task> tasks;
    // Ui object to print out displays
    private Ui ui;
    // Storage object to handle saving to file
    private Storage storage;
    // TaskList object to handle insertion, deletion, etc of tasks
    private TaskList tl;
    // Parser object to process inputs and commands
    private Parser parser;

    /**
     * A constructor to initialize Duke Chatbot
     */
    public Duke() {
        inputSc = new Scanner(System.in);
        tasks = new ArrayList<>();
        ui = new Ui();
        tl = new TaskList();
        parser = new Parser();
        storage = new Storage();
    }

    /**
     * Method to parse data.txt file
     */
    public void putExistingTask() {
        try {
            storage.readFile(readSc, tl, ui, tasks, "data/duke.txt");
        } catch (FileNotFoundException | NullPointerException e) {
            System.out.println("Folder data not found! " + e);
        }
    }

    /**
     * Get the suitable response from Duke Chatbot
     */
    public String getResponse(String input) {
        return interact(input);
    }

    /**
     * A method to handle the logic of Duke Chatbot and interact with users
     */
    public String interact(String input) {
        String[] splitted = input.split(" ", 2);
        Commands command;
        try {
            command = parser.processCommand(splitted);
        } catch (DukeErrorException ex) {
            return ex.toString();
        }

        if (command.equals(Commands.BYE)) {
            storage.writeFile(tasks);
            return ui.sayFarewell();
        } else if (command.equals(Commands.LIST)) {
            return ui.printList(tasks);
        } else if (command.equals(Commands.DONE)) {
            try {
                return tl.makeDone(tasks, Integer.parseInt(splitted[1]) - 1);
            } catch (ArrayIndexOutOfBoundsException | DukeErrorException ex) {
                return ex.toString();
            }
        } else if (command.equals(Commands.DELETE)) {
            try {
                return tl.deleteTask(tasks, Integer.parseInt(splitted[1]) - 1);
            } catch (ArrayIndexOutOfBoundsException | DukeErrorException ex) {
                return ex.toString();
            }
        } else if (command.equals(Commands.DEADLINE)) {
            try {
                return tl.addDeadline(ui, tasks, splitted[1], true, false);
            } catch (ArrayIndexOutOfBoundsException | InvalidDeadlineException ex) {
                return ex + ". ☹ Task deadline must be specified.";
            }
        } else if (command.equals(Commands.TODO)) {
            try {
                return tl.addTodo(ui, tasks, splitted[1], true, false);
            } catch (ArrayIndexOutOfBoundsException | InvalidTodoException ex) {
                return ex + ". ☹ The description of a todo cannot be empty.";
            }
        } else if (command.equals(Commands.EVENT)) {
            try {
                return tl.addEvent(ui, tasks, splitted[1], true, false);
            } catch (ArrayIndexOutOfBoundsException | InvalidEventException ex) {
                return ex + ". ☹ The description of an event cannot be empty.";
            }
        } else {
            return ui.printList(tl.findTask(tasks, splitted[1]));
        }
    }
}
