package duke.classes;

import java.io.IOException;

import duke.exceptions.BlahException;
import duke.exceptions.DukeExcessException;
import duke.exceptions.DukeInvalidCommandException;
import duke.exceptions.DukeInvalidTimeException;
import duke.exceptions.EmptyDukeException;
import duke.tasks.Task;



/**
 * The Duke class implements the DukeChatBot that is wired to store, display, update and delete
 * a variety of tasks.
 *
 * @author Rishi Ravikumar
 */
public class Duke {

    private Data data;
    private Parser parser;
    private TaskList taskList;
    private Ui ui;
    /**
     * Constructor whereby the duke class loads data from a given path on the hard disk.
     * @param path File path for local data
     */
    public Duke(String path) {
        try {
            data = new Data(path);
            taskList = new TaskList(data.loadData());
        } catch (IOException | DukeInvalidTimeException | ArrayIndexOutOfBoundsException e) {
            System.out.println("FAILURE: Unable to load data from local drive.");
            setDuke();
        }
    }

    /** Constructor whereby the duke class loads data from a default path on the hard disk */

    public Duke() {
        try {
            data = new Data("data/duke.txt");
            taskList = new TaskList(data.loadData());
        } catch (IOException | DukeInvalidTimeException | ArrayIndexOutOfBoundsException e) {
            System.out.println("FAILURE: Unable to load data from local drive.");
            setDuke();
        }
    }

    /**
     * Sets up a new default path for storage of data.
     */
    public void setDuke() {
        try {
            data = new Data();
            taskList = new TaskList(data.loadData());
        } catch (IOException | ArrayIndexOutOfBoundsException | DukeInvalidTimeException err) {
            System.out.println("FAILURE: Unable to create any file for saving data");
        }
    }

    /**
     * Main method whereby the Duke chatbot runs.
     * @param args Command to run program
     */
    public static void main(String[] args) {
        new Duke().run(); // new Duke("data/duke.txt").run();
    }

    /**
     * Void method that abstracts away most the logic behind running the Duke chatbot.
     */
    public void run() {
        // Initialise
        ui = new Ui();
        parser = new Parser();
        String word = parser.scan.nextLine();
        // Take in inputs
        while (!word.equals("bye")) {
            Command currentCommand = parser.analyse(word);
            assign(currentCommand, word);
            word = parser.scan.nextLine();
        }
        // Completion of program
        ui.endDuke();
        localSave();
    }

    /**
     * Abstracts the process of saving latest changes to disk.
     */
    public void localSave() {
        try {
            data.save(taskList.todoList);
        } catch (IOException | NullPointerException e) {
            System.out.println("FAILURE: Could not save data to main/data directory.");
        }
    }

    /**
     * Void method that carries the logic behind assigning an action based on the command.
     * @param command Type of command
     * @param task Actual task
     */

    public String assign(Command command, String task) {
        String completedString = "";
        switch (command) {
        case LIST:
            completedString = ui.displayList(taskList.todoList);
            break;
        case FIND:
            completedString = findTask(task);
            break;
        case TAG:
            Task taskToBeTagged = taskList.tagTask(task);
            completedString = ui.printTagging(taskToBeTagged);
            break;
        case TODO:
        case EVENT:
        case DEADLINE:
            completedString = decideTaskType(command, task);
            break;
        case DONE:
            Task todo = taskList.markDone(task);
            completedString = ui.completeTask(todo);
            break;
        case DELETE:
            Task deletedTask = taskList.delete(task);
            completedString = ui.deleteTask(deletedTask, taskList.todoList);
            break;
        case BLAH:
        case INVALID:
            completedString = assignOtherTasks(task);
            break;
        default:
            completedString = ui.endDuke();
            break;
        }
        System.out.println(completedString);
        // Assertion: Duke can associate a command to the task
        assert completedString != null : "Assignment of command to task has failed.";
        return completedString;
    }

    /**
     * Outputs the string upon query of a task in task list.
     * @param task Task to be searched for
     * @return String representation of tasks queried
     */
    public String findTask(String task) {
        try {
            return ui.displayList(taskList.find(task));
        } catch (DukeInvalidTimeException e) {
            return ui.printError(e.toString());
        }
    }

    /**
     * Void method that carries the logic behind assigning the unconventional tasks.
     * tasks like normal tasks and blah.
     * @param task Actual task
     */

    public String assignOtherTasks(String task) {
        try {
            taskList.storeInvalidTask(task);
            return ui.addOtherTask(task);
        } catch (BlahException | DukeInvalidCommandException e) {
            return ui.printError(e.toString());
        }
    }

    /**
     * Void method that abstracts away the logic behind assigning a method based on task type.
     * @param command Commands of TODO, DEADLINE & EVENT
     * @param task Actual activity
     */

    public String decideTaskType(Command command, String task) {
        Task todo = null;
        try {
            switch (command) {
            case TODO:
                todo = taskList.storeTodo(task);
                break;
            case DEADLINE:
                todo = taskList.storeDeadline(task);
                break;
            case EVENT:
                todo = taskList.storeEvent(task);
                break;
            default:
                break;
            }
            return ui.addTask(todo, taskList.todoList);
        } catch (EmptyDukeException | DukeInvalidTimeException | DukeExcessException e) {
            return ui.printError(e.toString());
        }
    }
    /**
     * Generates a response to user input.
     * @param input the task/command given to Duke
     * @return String message from Duke
     */
    public String getResponse(String input) {
        ui = new Ui();
        parser = new Parser();
        Command currentCommand = parser.analyse(input);
        String response = assign(currentCommand, input);
        // Assertion: Duke is able to parse the command given
        assert !response.isBlank() || !response.isEmpty() : "Response from duke is empty.";
        return "Duke says: \n" + response;
    }
}
