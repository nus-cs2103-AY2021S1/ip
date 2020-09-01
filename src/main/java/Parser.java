import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a class that holds all the methods and variables required to interpret the user input and carry out
 * the relevant operations.
 */
public class Parser {
    public static boolean done = false;

    /**
     * Static method that takes in the string input from the user and executes the relevant tasks required and triggers
     * responses from the passed in Ui object.
     *
     * @param input User input in String format
     * @param storage Storage to save and retrieve duke information
     * @param tasks TaskList to hold tasks that the parser can interact with
     * @param ui Ui to display results and errors to the user
     */
    public static String parse(String input, Storage storage, TaskList tasks, Ui ui) {
        String output = ui.showDivider();

        try {
            if (input.equals("bye")) {
                output += ui.showGoodbyeMessage();
                done = true;
            } else if (input.equals("list")) {
                output += ui.showAllTasks(tasks);
            } else if (input.startsWith("done")) {
                output += markAsDone(input, tasks, ui);
                storage.saveTasks(tasks, ui);
            } else if (input.startsWith("delete")) {
                output += deleteTask(input, tasks, ui);
                storage.saveTasks(tasks, ui);
            } else if (input.startsWith("find")) {
                output += findTask(input, tasks, ui);
            } else {

                Task currTask = null;

                if (input.startsWith("todo")) {
                    currTask = createTodo(input);
                } else if (input.startsWith("deadline")) {
                    currTask = createDeadline(input);
                } else if (input.startsWith("event")) {
                    currTask = createEvent(input);
                } else { // Invalid Command
                    output += ui.showInvalidCommandMessage();
                }

                if (currTask != null) {
                    output += addTask(currTask, tasks, ui);
                    storage.saveTasks(tasks, ui);
                }
            }
            return output + "\n" + ui.showDivider();
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    private static String findTask(String input, TaskList tasks, Ui ui) throws DukeException {
        if (input.equals("find")) {
            throw new DukeException("Sorry sir you will have to try again and this time " +
                    "tell me which task you want me to find");
        }

        String searchTerm = input.split(" ")[1];
        TaskList searchResults = new TaskList();

        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            if (task.toString().contains(searchTerm)) {
                searchResults.addTask(task);
            }
        }

        return ui.showAllTasks(searchResults);
    }

    /**
     * Marks the task provided by the user as completed.
     *
     * @param input User input that starts with the "done" keyword
     * @param tasks TaskList that contains the task required
     * @param ui Ui to display results and errors to the user
     * @throws DukeException Thrown when the user does not specify a task
     */
    public static String markAsDone(String input, TaskList tasks, Ui ui) throws DukeException {
        if (input.equals("done")) { // Input does not contain which task to mark as done
            throw new DukeException("Sorry sir you will have to try again and this time " +
                    "tell me which task you want me to mark as done");
        }
        int index = Integer.parseInt(input.split(" ")[1]);
        Task task = tasks.getTask(index - 1);
        task.markAsDone();
        return ui.showDoneTask(task);
    }

    /**
     * Deletes the provided task from Duke.
     *
     * @param input User input that starts with the "delete" keyword
     * @param tasks TaskList that contains the task to be deleted
     * @param ui Ui to display results and errors to the user
     * @throws DukeException Thrown when the user does not specify a task
     */
    private static String deleteTask(String input, TaskList tasks, Ui ui) throws DukeException {
        if (input.equals("delete")) { // Input does not contain which task to mark as done
            throw new DukeException("Sorry sir you will have to try again and this time " +
                    "tell me which task you want me to delete");
        }
        int index = Integer.parseInt(input.split(" ")[1]);
        Task deletedTask = tasks.deleteTask(index - 1); // Subtract 1 due to offset
        return ui.showDeleteTask(deletedTask, tasks);
    }

    /**
     * Creates a Todo task based on the given input.
     *
     * @param input User input that starts with the "todo" keyword
     * @return Todo task created based on the user input
     * @throws DukeException Thrown when the user does not provide a description
     */
    private static Task createTodo(String input) throws DukeException {
        // Input does not contain the required keyword
        if (input.equals("todo") || input.substring(5).isEmpty()) {
            throw new DukeException("My apologies sir but the description of todo " +
                    "cannot be empty :(");
        }
        return new Todo(input.substring(5));
    }

    /**
     * Creates a LocalDate object that is used to create a task when the date specified is of the appropriate format.
     *
     * @param dateString Date given by the user in String format
     * @return LocalDate object of the given input date
     */
    private static LocalDate convertDate(String dateString) {
        LocalDate date = null;
        try {
            date = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            date = null;
        }
        return date;
    }

    /**
     * Creates a Deadline task based on the given input.
     *
     * @param input User input that starts with the "deadline" keyword
     * @return Deadline task created based on the user input
     * @throws DukeException Thrown when the user input is in the incorrect format or does not provide a description
     */
    private static Task createDeadline(String input) throws DukeException {
        // Input does not contain the required keyword
        if (input.equals("deadline") || !input.substring(9).contains("/by")) {
            throw new DukeException("My apologies sir but you will have to use the correct " +
                    "format to create a deadline :(");
        }

        String[] splicedInput = input.substring(9).split(" /by ");
        LocalDate date = convertDate(splicedInput[1]);

        if (date == null) {
            return new Deadline(splicedInput[0], splicedInput[1]);
        }

        return new Deadline(splicedInput[0], date);
    }

    /**
     * Creates an Event task based on the given input.
     *
     * @param input User input that starts with the "event" keyword
     * @return Event task create base don the user input
     * @throws DukeException Thrown when the user input is in the incorrect format or does not provide a description
     */
    private static Task createEvent(String input) throws DukeException {
        // Input does not contain the required keyword
        if (input.equals("event") || !input.substring(6).contains("/at")) {
            throw new DukeException("My apologies sir but you will have to use the correct " +
                    "format to create a event :(");
        }

        String[] splicedInput = input.substring(6).split(" /at ");
        LocalDate date = convertDate(splicedInput[1]);

        if (date == null) {
            return new Event(splicedInput[0], splicedInput[1]);
        }

        return new Event(splicedInput[0], date);
    }

    /**
     * Adds the given task to the given TaskList and displays a message to the user.
     *
     * @param task Task to be added to the list
     * @param tasks TaskList that the given task is to be added to
     * @param ui Ui to display results to the user
     */
    private static String addTask(Task task, TaskList tasks, Ui ui) {
        tasks.addTask(task);
        return ui.showAddTask(task, tasks);
    }

    public static boolean isDone() {
        return done;
    }
}
