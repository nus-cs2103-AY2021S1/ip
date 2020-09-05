package duke;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeException;
import duke.exceptions.DukeUnknownCommandException;



/**
 * Duke Class is the main class that will run based on different commands
 * given by user. Available commands include todo, deadline, event,
 * done, delete. Todo, deadline and event are different types of tasks command.
 * Done and delete are commands to mark the list item as done or to
 * delete it respectively.
 * duke.Todo, deadline and event are followed by a description or message.
 * Eg: todo do CS2103T project
 * This would translate to a todo list item added into the user's overall list.
 * Event and duke.Deadline would require /at and /by to specify the timing.
 * Description of done and delete would be a number to specify which
 * item in the list that should be marked as done or deleted.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Empty constructor for duke, will be initiated when running GUI.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            taskList = new TaskList();
            storage.handleLoad();
        } catch (IOException e) {
            ui.printError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Constructor for Duke.
     * Loads if there are any existing tasks in storage.
     *
     * @param filePath filepath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList();
            storage.handleLoad();
        } catch (IOException e) {
            ui.printError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Invoke run for duke chatbot programme.
     *
     * @param args argument.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Run Duke programme depending on the different commands
     * given by user.
     *
     */
    public void run() {
        System.out.println(ui.greeting());
        System.out.println(ui.showList());
        boolean isExit = false;
        while (!isExit) {
            try {
                Command command = new Command(taskList, ui);
                String toEcho = ui.getCommand();
                if (toEcho.equals("bye")) {
                    System.out.println(ui.bye());
                    isExit = true;
                } else if (toEcho.equals("list")) {
                    System.out.println(ui.showList());
                } else if (toEcho.startsWith("done")) {
                    System.out.println(command.handleDone(toEcho));
                } else if (toEcho.startsWith("todo")) {
                    System.out.println(command.handleTodo(toEcho));
                } else if (toEcho.startsWith("deadline")) {
                    System.out.println(command.handleDeadline(toEcho));
                } else if (toEcho.startsWith("event")) {
                    System.out.println(command.handleEvent(toEcho));
                } else if (toEcho.startsWith("delete")) {
                    System.out.println(command.handleDelete(toEcho));
                } else if (toEcho.startsWith("find")) {
                    System.out.println(command.handleFind(toEcho));
                } else if (toEcho.startsWith("stats")) {
                    System.out.println(command.findStats(toEcho));
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

    public String getResponse(String input) {
        try {
            Command command = new Command(taskList, ui);
            if (input.equals("bye")) {
                storage.saveTasks();
                return ui.bye();
            } else if (input.equals("list")) {
                storage.saveTasks();
                return ui.showList();
            } else if (input.startsWith("done")) {
                storage.saveTasks();
                return command.handleDone(input);
            } else if (input.startsWith("todo")) {
                storage.saveTasks();
                return command.handleTodo(input);
            } else if (input.startsWith("deadline")) {
                storage.saveTasks();
                return command.handleDeadline(input);
            } else if (input.startsWith("event")) {
                storage.saveTasks();
                return command.handleEvent(input);
            } else if (input.startsWith("delete")) {
                storage.saveTasks();
                return command.handleDelete(input);
            } else if (input.startsWith("find")) {
                storage.saveTasks();
                return command.handleFind(input);
            } else if (input.startsWith("stats")) {
                storage.saveTasks();
                return command.findStats(input);
            } else {
                throw new DukeUnknownCommandException();
            }
        } catch (DateTimeParseException e) {
            return e.getMessage();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
