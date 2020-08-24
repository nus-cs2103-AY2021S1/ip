import exceptions.DukeException;
import exceptions.DukeUnknownCommandException;

import java.io.IOException;

import java.time.format.DateTimeParseException;

/**
 * Duke Class is the main class that will run based on different commands
 * given by user. Available commands include todo, deadline, event,
 * done, delete. Todo, deadline and event are different types of tasks command.
 * Done and delete are commands to mark the list item as done or to
 * delete it respectively.
 * Todo, deadline and event are followed by a description or message.
 * Eg: todo do CS2103T project
 * This would translate to a todo list item added into the user's overall list.
 * Event and Deadline would require /at and /by to specify the timing.
 * Description of done and delete would be a number to specify which
 * item in the list that should be marked as done or deleted.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor for Duke.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList();
    }

    /**
     * Invoke run for duke chatbot programme.
     *
     * @param args argument.
     * @throws IOException if file does not exist.
     */
    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Run Duke programme depending on the different commands
     * given by user.
     *
     * @throws IOException if file does not exist.
     */
    public void run() throws IOException {
        storage.handleLoad();
        ui.greeting();
        ui.showList();
        boolean isExit = false;
        while (!isExit) {
            try {
                Command c = new Command(taskList, ui);
                String toEcho = ui.getCommand();
                if (toEcho.equals("bye")) {
                    ui.bye();
                    isExit = true;
                } else if (toEcho.equals("list")) {
                    ui.showList();
                } else if (toEcho.startsWith("done")) {
                    c.handleDone(toEcho);
                } else if (toEcho.startsWith("todo")) {
                    c.handleTodo(toEcho);
                } else if (toEcho.startsWith("deadline")) {
                    c.handleDeadline(toEcho);
                } else if (toEcho.startsWith("event")) {
                    c.handleEvent(toEcho);
                } else if (toEcho.startsWith("delete")) {
                    c.handleDelete(toEcho);
                } else if (toEcho.startsWith("find")) {
                    c.handleFind(toEcho);
                } else {
                    throw new DukeUnknownCommandException();
                }
                storage.saveTasks();
            } catch (DateTimeParseException e) {
                ui.printDateTimeParseError(e.getMessage());
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            }
        }
    }

}
