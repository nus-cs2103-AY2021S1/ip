package duck;

import duck.exception.DuckException;
import duck.storage.Storage;
import duck.task.Task;
import duck.task.TaskFactory;
import duck.task.TaskList;
import duck.ui.Colour;
import duck.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

/**
 * Duck is the bot that handles all user input and calls the
 * respective classes needed.
 */
public class Duck {

    private Ui userInterface;
    private Storage storage;
    private TaskList taskList;
    private List<String> responses;

    /**
     * Initializes the components needed by the bot.
     * Loads the existing data from storage if present.
     *
     * @param ui Object that implements the Ui interface.
     * @param storage Object that implements the Storage interface.
     */
    public Duck(Ui ui, Storage storage) {
        this.userInterface = ui;
        this.storage = storage;
        try {
            this.taskList = storage.load();
        } catch (DuckException e) {
            this.taskList = new TaskList();
        }

        this.responses = new ArrayList<>();
    }

    private String getNumberOfTasks() {
        return "Now you have " + this.taskList.getLength() + " tasks in the list.";
    }

    /**
     * Default greeting when user first sees the bot.
     */
    public void greet() {
        List<String> welcomeMessage = new ArrayList<>();
        welcomeMessage.add("Hello! I'm Duck");
        welcomeMessage.add("What can I do for you?");
        userInterface.respond(welcomeMessage);
    }

    /**
     * To be run when the user exits the application or when the bot closes.
     * This will send a message as well as save the existing data via storage.
     */
    public void shutdown() {
        responses.add("Bye. Hope to see you again soon!");
        try {
            this.storage.save(this.taskList);
        } catch (DuckException e) {
            responses.add("Failed to save file");
        }

    }

    /**
     * Gets statuses of all tasks in TaskList and adds to response.
     */
    public void listTasks() {
        responses.add("Here are the tasks in your list");
        String[] statuses = this.taskList.getStatuses();
        for (int i = 0; i < statuses.length; i++) {
            responses.add(statuses[i]);
        }
    }

    /**
     * Lists all the tasks with dates in ascending date order.
     * Triggered by "due" with optional date term "due /by 2020-12-12".
     *
     * @param input Input from user.
     */
    public void listByDueDate(String input) {
        Optional<LocalDate> optionalDate;
        try {
            optionalDate = Optional.ofNullable(Parser.parseDate(input));
            responses.add("Here are the tasks up to the date: " +
                    optionalDate.get().format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        } catch (DuckException e) {
            optionalDate = Optional.empty();
            responses.add("Here are the tasks sorted by date");
            responses.add("You can filter up to a date by using \"due [/at | /by] yyyy-mm-dd\"");
        }

        String[] statusesByDueDate = this.taskList.getStatusesByDate(optionalDate);
        for (int i = 0; i < statusesByDueDate.length; i++) {
            responses.add(statusesByDueDate[i]);
        }
    }

    /**
     * Marks the task as done and displays the done task.
     *
     * @param input Input from user.
     * @throws DuckException If index obtained is less than 1 or greater than number of tasks.
     */
    public void markTaskAsDone(String input) throws DuckException {
        int taskNumber = Parser.parseTaskNumber(input);
        Task task = this.taskList.markDone(taskNumber);
        responses.add("Nice! I've marked this as " + Colour.Green("done"));
        responses.add("  " + task.getStatus());
    }

    /**
     * Deletes the task and displays the deleted task.
     *
     * @param input Input from user.
     * @throws DuckException If index obtained is less than 1 or greater than number of tasks.
     */
    public void deleteTask(String input) throws DuckException {
        int taskNumber = Parser.parseTaskNumber(input);
        Task task = this.taskList.deleteTask(taskNumber);
        responses.add("Noted. I've removed this task");
        responses.add("  " + task.getStatus());
        responses.add(getNumberOfTasks());

    }

    /**
     * Obtains a task from TaskFactory and adds it to the TaskList.
     *
     * @param input Input from user.
     * @throws DuckException If input is unable to be parsed into any Task.
     */
    public void createNewTask(String input) throws DuckException {
        Task newTask = TaskFactory.createTaskFromInput(input);
        this.taskList.addTask(newTask);
        responses.add("Got it. I've added this task");
        responses.add("  " + newTask.getStatus());
        responses.add(getNumberOfTasks());
    }

    /**
     * Main loop of the bot.
     * Continuously waits for input until "bye" command is given.
     */
    public void run() {
        greet();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                shutdown();
            }
        });
        boolean run = true;
        Scanner sc = new Scanner(System.in);
        while (run) {
            String input = sc.nextLine();
            responses.clear();
            Option option = Parser.parseOption(input);
            try {
                switch (option) {
                case BYE:
                    shutdown();
                    run = false;
                    break;
                case LIST:
                    listTasks();
                    break;
                case DONE:
                    markTaskAsDone(input);
                    break;
                case DELETE:
                    deleteTask(input);
                    break;
                case DUE:
                    listByDueDate(input);
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    createNewTask(input);
                    break;
                case UNRECOGNIZED:
                default:
                    throw new DuckException("Instruction not recognized");

                }
            } catch (DuckException e) {
                responses.add(e.toString());
            } finally {
                userInterface.respond(responses);
            }
        }
    }
}

