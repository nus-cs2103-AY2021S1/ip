import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Parser class handles interpretation of user input
 */
public class Parser {

    private TaskList tasks;
    private Storage storage;

    /**
     * Constructor for Parser object
     *
     * @param tasks
     * @param storage
     */
    public Parser(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * reads a String input and outputs according to different cases of input
     * @param input String in the scanner
     * @return output in response to String input
     */
    public String parse(String input) {

        String errorMessage = DukeExceptionHandler.handleException(input);
        if (errorMessage != null) {
            return errorMessage;
        }

        if (input.equals("list")) {
            return tasks.printTasks();
        } else if (input.equals("bye")) {
            return Ui.Bye();
        } else {
            String[] words = input.split(" ", 2);
            String inputType = words[0];
            String description = words[1];

            try {
                if (inputType.equals("todo")) {
                    Todo todo = new Todo(description);
                    tasks.addTask(storage, todo);

                    return Ui.print("Got it. I've added this task:\n" + "" + todo.toString() +
                            "\nNow you have " + tasks.getList().size() + " tasks in the list");

                } else if (inputType.equals("deadline")) {
                    Deadline deadline = new Deadline(description);
                    tasks.addTask(storage, deadline);

                    return Ui.print("Got it. I've added this task:\n" + "" + deadline.toString() +
                            "\nNow you have " + tasks.getList().size() + " tasks in the list");

                } else if (inputType.equals("event")) {
                    Event event = new Event(description);
                    tasks.addTask(storage, event);

                    return Ui.print("Got it. I've added this task:\n" + "" + event.toString() +
                            "\nNow you have " + tasks.getList().size() + " tasks in the list");

                } else if (inputType.equals("done")) {
                    tasks.setDone(Integer.parseInt(description), storage);
                    Task doneTask = tasks.getList().get(Integer.parseInt(description) - 1);
                    return Ui.print("Nice! I've marked this task as done:\n" + doneTask);

                } else if (inputType.equals("delete")) {
                    tasks.delete(Integer.parseInt(description));
                    return Ui.printList(tasks.getList());

                } else if (inputType.equals("find")) {
                    return tasks.find(input.substring(5));
                } else return "Input format incorrect.";
            } catch (FileNotFoundException e) {
                return e.getMessage();
            } catch (IOException e) {
                return "Something went wrong: " + e.getMessage();
            } catch (DateTimeParseException e) {
                return "Date must be in the YYYY-MM-DD format!";
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
    }
}