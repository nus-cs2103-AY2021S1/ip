package duke.parser;

import duke.dukeexception.DukeKeywordMissingException;
import duke.dukeexception.DukeTaskNonExistException;
import duke.dukeexception.DukeUnknownInputException;
import duke.dukeexception.EmptyDescriptionException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * This class deals with the interpretation of the user's inputs by the program.
 */
public class Parser {
    public Storage storage;
    public TaskList shelf;
    public Scanner sc;
    public UI ui;
    public int indexer;

    /**
     * Constructor for the parser.
     * @param storage Storage object that would deal with the storing of previously saved lists
     * @param tasklist Tasklist object that contains the task lists for the to do list
     * @param ui UI which deals with interactions with the user
     */
    public Parser(Storage storage, TaskList tasklist, UI ui) {
        this.storage = storage;
        this.shelf = tasklist;
        this.sc = new Scanner(System.in);
        this.ui = ui;
    }

    /**
     * Method that is used as a flag in the duke handler method in the duke class to indicate when the program should
     * stop running.
     * @param response Response would refer to the input by the user
     * @return boolean value, true = the program should stop, false = the program should continue running
     */
    public boolean toStop(String response) {
        return response.equals("bye");
    }

    /**
     * Method that takes in different commands by the user and calls the relevant methods in the UI to deal with them.
     *
     * <p>
     *     <h3> Userinput = bye</h3>
     *      Stops the entire program
     * </p>
     *
     * <p>
     *     <h3> UserInput = list</h3>
     *      Prints out a list of the tasks in the TaskList
     * </p>
     *
     * <p>
     *     <h3> UserInput = delete xxx task</h3>
     *     Triggers the UI to delete the task and converts the one-based index given by the user
     *     to a zero-based index.
     * </p>
     *
     * <p>
     *     <h3> UserInput = done</h3>
     *     Triggers the UI to mark the task as done and converts the one-based index given by the user
     *     to a zero-based index.
     * </p>
     *
     * <p>
     *     <h3> UserInput = create Event/Deadline/Todo </h3>
     *     Triggers the UI to create the corresponding task object and separates the user's input to
     *     show the name of the task, type of task and the duedate of the task(only applicable for deadline and events).
     *     It may throw an error if the description is empty, keywords are missing or the dates are in the wrong format.
     * </p>
     *
     * @param response String response which represents the user's input
     */
    public void listen(String response) {
        try {
            if (response.equals("bye")) {
                ui.replyBye();
            } else if (response.equals("list")) {
                ui.replyList();
            } else if (response.indexOf("delete") == 0) {
                indexer = Integer.parseInt(response.replaceAll("\\D+", "")) - 1;
                ui.replyDelete(indexer);
            } else if (response.indexOf("done ") == 0) {
                indexer = Integer.parseInt(response.replaceAll("\\D+", "")) - 1;
                ui.replyDone(indexer);
            } else if (response.indexOf("todo ") == 0) {
                if (response.length() <= 5) {
                    throw new EmptyDescriptionException("todo");
                }
                ui.addTodo(response.substring(4));
            } else if (response.indexOf("deadline ") == 0) {
                if (response.length() <= 9) {
                    throw new EmptyDescriptionException("deadline");
                }
                if (!response.contains("/by ")) {
                    throw new DukeKeywordMissingException("/by ");
                }
                String date = response.substring(response.indexOf("/by ") + 4);
                response = response.substring(response.indexOf("deadline ") + 8, response.indexOf("/by "));
                ui.addDeadline(response, date);
            } else if (response.indexOf("event ") == 0) {
                if (response.length() <= 6) {
                    throw new EmptyDescriptionException("event");
                }
                if (!response.contains("/at ")) {
                    throw new DukeKeywordMissingException("/at ");
                }
                String date = response.substring(response.indexOf("/at ") + 4);
                response = response.substring(response.indexOf("event ") + 5, response.indexOf("/at "));
                ui.addEvent(response, date);
            } else {
                throw new DukeUnknownInputException("error");
            }
        } catch (IOException | DukeTaskNonExistException | EmptyDescriptionException |
                DukeKeywordMissingException | DukeUnknownInputException e) {
            ui.showError(e);
        } catch (DateTimeParseException e) {
            System.out.println("☹ OOPS!!! Ensure that the datetime input is in the format YYYY-MM-DD HH:MM");
        }
    }

}
