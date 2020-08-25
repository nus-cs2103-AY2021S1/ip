import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    public static boolean done = false;

    public static void parse(String input, Storage storage, TaskList tasks, Ui ui) {
        ui.showDivider();
        try {
            if (input.equals("bye")) {
                ui.showGoodbyeMessage();
                done = true;
            } else if (input.equals("list")) {
                ui.showAllTasks(tasks);
            } else if (input.startsWith("done")) {
                markAsDone(input, tasks, ui);
                storage.saveTasks(tasks, ui);
            } else if (input.startsWith("delete")) {
                deleteTask(input, tasks, ui);
                storage.saveTasks(tasks, ui);
            } else if (input.startsWith("find")) {
                findTask(input, tasks, ui);
            } else {

                Task currTask = null;

                if (input.startsWith("todo")) {
                    currTask = createTodo(input);
                } else if (input.startsWith("deadline")) {
                    currTask = createDeadline(input);
                } else if (input.startsWith("event")) {
                    currTask = createEvent(input);
                } else { // Invalid Command
                    ui.showInvalidCommandMessage();
                }

                if (currTask != null) {
                    addTask(currTask, tasks, ui);
                    storage.saveTasks(tasks, ui);
                }
            }
            ui.showDivider();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    private static void findTask(String input, TaskList tasks, Ui ui) throws DukeException {
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

        ui.showAllTasks(searchResults);
    }

    public static void markAsDone(String input, TaskList tasks, Ui ui) throws DukeException {
        if (input.equals("done")) { // Input does not contain which task to mark as done
            throw new DukeException("Sorry sir you will have to try again and this time " +
                    "tell me which task you want me to mark as done");
        }
        int index = Integer.parseInt(input.split(" ")[1]);
        Task task = tasks.getTask(index - 1);
        task.markAsDone();
        ui.showDoneTask(task);
    }

    private static void deleteTask(String input, TaskList tasks, Ui ui) throws DukeException {
        if (input.equals("delete")) { // Input does not contain which task to mark as done
            throw new DukeException("Sorry sir you will have to try again and this time " +
                    "tell me which task you want me to delete");
        }
        int index = Integer.parseInt(input.split(" ")[1]);
        Task deletedTask = tasks.deleteTask(index - 1); // Subtract 1 due to offset
        ui.showDeleteTask(deletedTask, tasks);
    }

    private static Task createTodo(String input) throws DukeException {
        // Input does not contain the required keyword
        if (input.equals("todo") || input.substring(5).isEmpty()) {
            throw new DukeException("My apologies sir but the description of todo " +
                    "cannot be empty :(");
        }
        return new Todo(input.substring(5));
    }

    private static LocalDate convertDate(String dateString) {
        LocalDate date = null;
        try {
            date = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            date = null;
        }
        return date;
    }

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

    private static void addTask(Task task, TaskList tasks, Ui ui) {
        tasks.addTask(task);
        ui.showAddTask(task, tasks);
    }

    public static boolean isDone() {
        return done;
    }
}
