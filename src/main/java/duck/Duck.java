package duck;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import duck.exception.DuckException;
import duck.storage.LocalStorage;
import duck.storage.Storage;
import duck.task.Task;
import duck.task.TaskFactory;
import duck.task.TaskList;


/**
 * Duck is the bot that handles all user input and calls the
 * respective classes needed.
 */
public class Duck {

    private final Storage storage = new LocalStorage("data/data.ser");
    private TaskList taskList;
    private final List<String> responses;

    /**
     * Initializes the components needed by the bot.
     * Loads the existing data from storage if present.
     */
    public Duck() {
        try {
            taskList = storage.load();
        } catch (DuckException e) {
            taskList = new TaskList();
        }

        responses = new ArrayList<>();
    }

    private String getNumberOfTasks() {
        return "Now you have " + taskList.getLength() + " tasks in the list.";
    }

    /**
     * Returns default greetings when user first sees the bot.
     */
    public String[] greet() {
        List<String> welcomeMessage = new ArrayList<>();
        welcomeMessage.add("Hello! I'm Duck");
        welcomeMessage.add("What can I do for you?");
        return welcomeMessage.toArray(String[]::new);
    }

    /**
     * Saves the existing tasks into storage
     */
    private void save() {
        try {
            storage.save(this.taskList);
        } catch (DuckException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Runs when the user exits the application or when the bot closes.
     * This will send a message as well as save the existing data via storage.
     */
    private void shutdown() {
        responses.add("Bye. Hope to see you again soon!");
        save();
        System.exit(0);
    }

    /**
     * Gets statuses of all tasks in TaskList and adds to response.
     */
    private void listTasks() {
        responses.add("Here are the tasks in your list");
        String[] statuses = taskList.getStatuses();
        responses.addAll(Arrays.asList(statuses));
    }

    /**
     * Lists all the tasks with dates in ascending date order.
     * Triggered by "due" with optional date term "due /by 2020-12-12".
     *
     * @param input Input from user.
     */
    private void listByDueDate(String input) {
        Optional<LocalDate> optionalDate;
        try {
            optionalDate = Optional.ofNullable(Parser.parseDate(input));
            responses.add("Here are the tasks up to the date: "
                    + optionalDate.get().format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        } catch (DuckException e) {
            // This block is run if no date is provided or an invalid date is detected.
            optionalDate = Optional.empty();
            responses.add("Here are the tasks sorted by date");
            responses.add("You can filter up to a date by using \"due [/at | /by] yyyy-mm-dd\"");
        }

        String[] statusesByDueDate = taskList.getStatusesByDate(optionalDate);
        responses.addAll(Arrays.asList(statusesByDueDate));
    }


    /**
     * Gets the list of statuses from TaskList and displays them.
     *
     * @param input Input from User.
     * @throws DuckException If description field is empty.
     */
    private void listByFind(String input) throws DuckException {
        input = input.substring(4);

        String[] statusesByFind = taskList.getStatusesByFind(input);
        responses.add("Here are the matching tasks in your list:");
        Collections.addAll(responses, statusesByFind);

    }

    /**
     * Marks the task as done and displays the done task.
     *
     * @param input Input from user.
     * @throws DuckException If index obtained is less than 1 or greater than number of tasks.
     */
    private void markTaskAsDone(String input) throws DuckException {
        int taskNumber = Parser.parseTaskNumber(input);
        Task task = taskList.markDone(taskNumber);
        responses.add("Nice! I've marked this as done");
        responses.add("  " + task.getStatus());
        save();
    }

    /**
     * Deletes the task and displays the deleted task.
     *
     * @param input Input from user.
     * @throws DuckException If index obtained is less than 1 or greater than number of tasks.
     */
    private void deleteTask(String input) throws DuckException {
        int taskNumber = Parser.parseTaskNumber(input);
        Task task = taskList.deleteTask(taskNumber);
        responses.add("Noted. I've removed this task");
        responses.add("  " + task.getStatus());
        responses.add(getNumberOfTasks());
        save();
    }

    /**
     * Obtains a task from TaskFactory and adds it to the TaskList.
     *
     * @param input Input from user.
     * @throws DuckException If input is unable to be parsed into any Task.
     */
    private void createNewTask(String input) throws DuckException {
        Task newTask = TaskFactory.createTaskFromInput(input);
        taskList.addTask(newTask);
        responses.add("Got it. I've added this task");
        responses.add("  " + newTask.getStatus());
        responses.add(getNumberOfTasks());
        save();
    }


    /**
     * Handles input from user and returns responses in the form of an
     * array of String. Each entry in the String array corresponds to a
     * new line of response.
     *
     * @param input Input from user.
     * @return Responses in the form of String[].
     */
    public String[] handleInput(String input) {
        responses.clear();
        Option option = Parser.parseOption(input);
        try {
            switch (option) {
            case BYE:
                shutdown();
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
            case FIND:
                listByFind(input);
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
        }

        return responses.toArray(String[]::new);

    }
}


