package duke.classes;

import java.io.IOException;

import duke.exceptions.BlahException;
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
            this.taskList = new TaskList(data.loadData());
        } catch (IOException | DukeInvalidTimeException | ArrayIndexOutOfBoundsException e) {
            System.out.println("FAILURE: Unable to load data from local drive.");
            try {
                data = new Data();
                this.taskList = new TaskList(data.loadData());
            } catch (IOException | ArrayIndexOutOfBoundsException | DukeInvalidTimeException err) {
                System.out.println("FAILURE: Unable to create any file for saving data");
            }
        }
    }

    /** Constructor whereby the duke class loads data from a given path on the hard disk */

    public Duke() {
        try {
            data = new Data("data/duke.txt");
            this.taskList = new TaskList(data.loadData());
        } catch (IOException | DukeInvalidTimeException | ArrayIndexOutOfBoundsException e) {
            System.out.println("FAILURE: Unable to load data from local drive.");
            try {
                data = new Data();
                this.taskList = new TaskList(data.loadData());
            } catch (IOException | ArrayIndexOutOfBoundsException | DukeInvalidTimeException err) {
                System.out.println("FAILURE: Unable to create any file for saving data");
            }
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

        this.ui = new Ui();
        this.parser = new Parser();
        String word = this.parser.scan.nextLine();

        while (!word.equals("bye")) {
            Commands currentCommand = this.parser.analyse(word);
            assign(currentCommand, word);
            word = this.parser.scan.nextLine();
        }

        this.ui.endDuke();
        localSave();
    }

    /**
     * Abstracts the process of saving latest changes to disk.
     */
    public void localSave() {
        try {
            data.save(this.taskList.todoList);
        } catch (IOException | NullPointerException e) {
            System.out.println("FAILURE: Could not save data to main/data directory.");
        }
    }

    /**
     * Void method that carries the logic behind assigning an action based on the command.
     * @param command Type of command
     * @param task Actual task
     */

    public String assign(Commands command, String task) {
        String completedString = "";
        switch (command) {
        case LIST:
            completedString = this.ui.displayList(this.taskList.todoList);
            break;
        case FIND:
            try {
                completedString = this.ui.displayList(this.taskList.find(task));
            } catch (DukeInvalidTimeException e) {
                completedString = this.ui.printError(e.toString());
            }
            break;
        case TODO:
        case EVENT:
        case DEADLINE:
            completedString = decideTaskType(command, task);
            break;
        case DONE:
            Task todo = this.taskList.markDone(task);
            completedString = this.ui.completeTask(todo);
            break;
        case DELETE:
            Task deletedTask = this.taskList.delete(task);
            completedString = this.ui.deleteTask(deletedTask, this.taskList.todoList);
            break;
        case BLAH:
        case TASK:
            completedString = assignOtherTasks(task);
            break;
        default:
            completedString = ui.endDuke();
            break;
        }
        System.out.println(completedString);
        return completedString;
    }

    /**
     * Void method that carries the logic behind assigning the unconventional tasks.
     * tasks like normal tasks and blah.
     * @param task Actual task
     */

    public String assignOtherTasks(String task) {
        try {
            this.taskList.storeTask(task);
            return this.ui.addOtherTask(task);
        } catch (BlahException e) {
            return this.ui.printError(e.toString());
        }
    }

    /**
     * Void method that abstracts away the logic behind assigning a method based on task type.
     * @param commands Commands of TODO, DEADLINE & EVENT
     * @param task Actual activity
     */

    public String decideTaskType(Commands commands, String task) {
        Task todo = null;
        try {
            switch (commands) {
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
            return this.ui.addTask(todo, this.taskList.todoList);
        } catch (EmptyDukeException | DukeInvalidTimeException e) {
            return this.ui.printError(e.toString());
        }
    }
    /**
     * Generates a response to user input.
     * @param input the task/command given to Duke
     * @return String message from Duke
     */
    public String getResponse(String input) {
        this.ui = new Ui();
        this.parser = new Parser();
        Commands currentCommand = this.parser.analyse(input);
        String response = assign(currentCommand, input);
        return "Duke says: \n" + response;
    }
}
