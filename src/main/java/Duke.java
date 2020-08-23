import exceptions.DukeMissingTimeException;
import exceptions.DukeException;
import exceptions.DukeEmptyMessageException;
import exceptions.DukeInvalidMessageException;
import exceptions.DukeUnknownCommandException;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private final static String FILEPATH = System.getProperty("user.dir") + (System.getProperty("user.dir").endsWith("text-ui-test")
            ? "/saved-tasks.txt"
            : "/text-ui-test/saved-tasks.txt");

    public Duke() {
        ui = new Ui();
        storage = new Storage(FILEPATH);
        taskList = new TaskList();
    }

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }

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

    public void handleTodo(String description) {
        Todo todo = new Todo(description);
        TaskList.taskList.add(todo);
        ui.printTask(todo);
    }

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
