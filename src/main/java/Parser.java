import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Parser class handles interpretation of user input
 *
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
     *
     * @param input String comes from the scanner object in Duke.main
     */
    public void parse(String input) {

        String errorMessage = DukeExceptionHandler.handleException(input);
        if (errorMessage != null) {
            System.out.println(errorMessage);
            return;
        }

        if (input.equals("list")) {
            tasks.printTasks();
        }
        else {
            String[] words = input.split(" ", 2);
            String inputType = words[0];
            String description = words[1];

            try {
                if (inputType.equals("todo")) {
                    Todo todo = new Todo(description);
                    tasks.addTask(storage, todo);
                } else if (inputType.equals("deadline")) {
                    Deadline deadline = new Deadline(description);
                    tasks.addTask(storage, deadline);
                } else if (inputType.equals("event")) {
                    Event event = new Event(description);
                    tasks.addTask(storage, event);
                } else if (inputType.equals("done")) {
                    tasks.setDone(Integer.parseInt(description), storage);
                } else if (inputType.equals("delete")) {
                    tasks.delete(Integer.parseInt(description));
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Date must be in the YYYY-MM-DD format!");
            } catch (DukeException e) {
                e.printStackTrace();
            }
        }
    }
}