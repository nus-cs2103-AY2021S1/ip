package duke;

import java.io.FileNotFoundException;

import exception.DukeErrorException;
import exception.InvalidDeadlineException;
import exception.InvalidEventException;
import exception.InvalidTodoException;
import exception.UnknownCommandException;
import ui.Ui;


/**
 * Main class for the Duke application
 */
public class Duke {
    // Ui object to print out displays
    private Ui ui;
    // Storage object to handle saving to file
    private Storage storage;
    // TaskList object to handle insertion, deletion, etc of tasks
    private TaskList tl;

    /**
     * A constructor to initialize Duke Chatbot
     */
    public Duke() {
        ui = new Ui();
        tl = new TaskList();
        storage = new Storage();
    }

    /**
     * Method to parse data.txt file
     */
    public void putExistingTask() {
        try {
            storage.readFile(tl, ui, "data/duke.txt");
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
            command = Parser.processCommand(splitted);
        } catch (DukeErrorException ex) {
            return ex.toString();
        }
        assert command != null : "Command should not be null";
        if (command.equals(Commands.BYE)) {
            storage.writeFile(tl);
            return ui.sayFarewell();
        } else if (command.equals(Commands.LIST)) {
            return ui.printList(tl.getTaskList());
        } else if (command.equals(Commands.DONE)) {
            try {
                int taskIndex = Integer.parseInt(splitted[1]) - 1;
                return tl.makeDone(taskIndex);
            } catch (ArrayIndexOutOfBoundsException | DukeErrorException ex) {
                return ex.toString();
            }
        } else if (command.equals(Commands.DELETE)) {
            try {
                int taskIndex = Integer.parseInt(splitted[1]) - 1;
                return tl.deleteTask(taskIndex);
            } catch (ArrayIndexOutOfBoundsException | DukeErrorException ex) {
                return ex.toString();
            }
        } else if (command.equals(Commands.DEADLINE)) {
            try {
                return tl.addDeadline(ui, splitted[1], true, false);
            } catch (ArrayIndexOutOfBoundsException | InvalidDeadlineException ex) {
                return ex + ". ☹ Task deadline must be specified.";
            }
        } else if (command.equals(Commands.TODO)) {
            try {
                return tl.addTodo(ui, splitted[1], true, false);
            } catch (ArrayIndexOutOfBoundsException | InvalidTodoException ex) {
                return ex + ". ☹ The description of a todo cannot be empty.";
            }
        } else if (command.equals(Commands.EVENT)) {
            try {
                return tl.addEvent(ui, splitted[1], true, false);
            } catch (ArrayIndexOutOfBoundsException | InvalidEventException ex) {
                return ex + ". ☹ The description of an event cannot be empty.";
            }
        } else if (command.equals(Commands.FIND)) {
            return ui.printList(tl.findTask(splitted[1]));
        } else if (command.equals(Commands.UPDATE)) {
            try {
                return tl.updateTask(ui, splitted[1]);
            } catch (DukeErrorException | UnknownCommandException | ArrayIndexOutOfBoundsException e) {
                return e.toString();
            }
        } else {
            return "Unknown command!";
        }
    }
}
