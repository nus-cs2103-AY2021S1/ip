import exceptions.DukeMissingTimeException;
import exceptions.DukeException;
import exceptions.DukeEmptyMessageException;
import exceptions.DukeInvalidMessageException;
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
     * Determine which filepath to return depending if it is run
     * from duke class or runtest.bat.
     */
    private final static String FILEPATH = System.getProperty("user.dir") + (System.getProperty("user.dir").endsWith("text-ui-test")
            ? "/saved-tasks.txt"
            : "/text-ui-test/saved-tasks.txt");

    /**
     * Constructor for Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILEPATH);
        taskList = new TaskList();
    }

    /**
     * Invoke run for duke chatbot programme.
     *
     * @param args argument.
     * @throws IOException if file does not exist.
     */
    public static void main(String[] args) throws IOException {
        new Duke().run();
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
                String toEcho = ui.getCommand();
                String[] command = Parser.splitCommandAndDescription(toEcho);
                if (toEcho.equals("bye")) {
                    ui.bye();
                    isExit = true;
                } else if (toEcho.equals("list")) {
                    ui.showList();
                } else if (toEcho.startsWith("done")) {
                    if (toEcho.length() == 4) {
                        throw new DukeEmptyMessageException("Done");
                    } else if (Integer.parseInt(command[1]) > TaskList.taskList.size()) {
                        throw new DukeInvalidMessageException();
                    } else {
                        int index = Integer.parseInt(command[1]) - 1;
                        taskList.markDone(index);
                        ui.printDone(index);
                    }
                } else if (toEcho.startsWith("todo")) {
                    if (toEcho.length() == 4) {
                        throw new DukeEmptyMessageException("Todo");
                    }
                    handleTodo(command[1]);
                } else if (toEcho.startsWith("deadline")) {
                    if (toEcho.length() == 8) {
                        throw new DukeEmptyMessageException("Deadline");
                    }
                    handleDeadline(command[1]);
                } else if (toEcho.startsWith("event")) {
                    if (toEcho.length() == 5) {
                        throw new DukeEmptyMessageException("Event");
                    }
                    handleEvent(command[1]);
                } else if (toEcho.startsWith("delete")) {
                    if (toEcho.length() == 6) {
                        throw new DukeEmptyMessageException("Delete");
                    } else if (Integer.parseInt(command[1]) > TaskList.taskList.size() ||
                            Integer.parseInt(command[1]) < 0) {
                        throw new DukeInvalidMessageException();
                    }
                    int indexToDelete = Integer.parseInt(command[1]) - 1;
                    ui.printDelete(indexToDelete);
                } else if (toEcho.startsWith("find")) {
                    if (toEcho.length() == 4) {
                        throw new DukeEmptyMessageException("Find");
                    }
                    ui.printFind(command[1]);
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

    /**
     * Add todo into the list and print the relevant todo message.
     *
     * @param description description of todo.
     */
    public void handleTodo(String description) {
        Todo todo = new Todo(description);
        TaskList.taskList.add(todo);
        ui.printTask(todo);
    }

    /**
     * Add deadline into the list and print the relevant deadline message.
     *
     * @param description description of deadline.
     * @throws DukeMissingTimeException thrown when user does not input the timing required.
     */
    public void handleDeadline(String description) throws DukeMissingTimeException {
        try {
            String[] strArr = Parser.splitDeadlineTime(description);
            String todo = strArr[0];
            String time = strArr[1];
            Deadline deadline = new Deadline(todo, time);
            taskList.add(deadline);
            ui.printTask(deadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeMissingTimeException();
        }
    }

    /**
     * Add event into the list and print the relevant event message.
     *
     * @param description description of event.
     * @throws DukeMissingTimeException thrown when user does not input the timing required.
     */
    public void handleEvent(String description) throws DukeMissingTimeException {
        try {
            String[] strArr = Parser.splitEventTime(description);
            String todo = strArr[0];
            String time = strArr[1];
            Event event = new Event(todo, time);
            taskList.add(event);
            ui.printTask(event);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeMissingTimeException();
        }
    }

}
