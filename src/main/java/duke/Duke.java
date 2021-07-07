package duke;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

/**
 * <h1>Duke!</h1>
 * The duke.Duke program implements an application that
 * helps you keep track of your tasks.
 *
 * @author Augustine Kau
 * @version 0.2
 * @since 2020-08-18
 */
public class Duke {

    private final Storage storage;
    private final TaskList ls;
    private final Ui ui;

    /**
     * Constructor for duke.Duke
     * @param filePath For file todolist.txt.
     * @throws IOException On input error.
     */
    public Duke(String filePath) throws IOException {
        storage = new Storage(filePath);
        ls = new TaskList(storage.loadFile());
        ui = new Ui();
    }

    /**
     * Command that user can input
     */
    enum Command {
        BYE, LIST, DONE, TODO, DEADLINE, EVENT, DELETE, CHECK, FIND, HELP
    }

    /**
     * Getter method to get storage.
     * @return Storage.
     */
    private Storage getStorage() {
        return storage;
    }

    /**
     * Get the file in Storage.
     * @return File.
     */
    private File getStorageFile() {
        return getStorage().getFile();
    }

    /**
     * Check if the to-do file exists.
     * @return boolean True is the file exist.
     */
    private boolean isFileInStorage() {
        return getStorageFile().exists();
    }

    /**
     * Returns the response of Duke given the input.
     * @param str String User input.
     * @return String Duke's response.
     * @throws IOException Exception.
     */
    public String getResponse(String str) throws IOException {
        String[] arr = str.split(" ", 2);

        String upperCaseCmd = Parser.parseCmd(arr[0]);
        Command cmd;
        try {
            cmd = Command.valueOf(upperCaseCmd);
        } catch (IllegalArgumentException ex) {
            return ui.outputInvalidInput();
        }

        switch (cmd) {
        case BYE:
            return cmdBye();
        case LIST:
            return cmdList();
        case DONE:
            return cmdDone(str);
        case TODO:
            return cmdTodo(arr[1]);
        case DEADLINE:
            return cmdDeadline(arr);
        case EVENT:
            return cmdEvent(arr);
        case DELETE:
            return cmdDelete(str);
        case CHECK:
            return cmdCheck(arr[1]);
        case FIND:
            return cmdFind(arr[1]);
        case HELP:
            return ui.outputHelp();
        default:
            return ui.outputInvalidInput();
        }
    }

    private String cmdBye() throws IOException {
        assert(isFileInStorage()) : "File should exist.";
        // rewrite the file to update latest changes
        Storage.saveFile(getStorageFile(), ls);
        return ui.outputBye();
    }

    private String cmdFind(String keyword) {
        try {
            if (isListEmpty()) {
                return ui.outputCheckNoTask();
            }
            return ui.outputFindTask(ls, keyword);
        } catch (Exception ex) {
            return ui.outputFindError();
        }
    }

    private boolean isListEmpty() {
        return ls.isEmpty();
    }

    private String cmdCheck(String checkDate) {
        try {
            LocalDate date = LocalDate.parse(checkDate);
            if (isListEmpty()) {
                return ui.outputCheckNoTask();
            }
            return ui.outputCheckTask(ls, date);
        } catch (Exception ex) {
            return ui.outputCheckError();
        }
    }

    private String cmdDelete(String str) {
        try {
            int numToBeDeleted = Parser.parseInt(str);
            Task tsk = ls.get(numToBeDeleted);
            ls.remove(numToBeDeleted);
            return ui.outputDeleteMsg(ls, tsk);
        } catch (Exception ex) {
            return ui.outputDeleteError();
        }
    }

    private String cmdEvent(String[] arr) {
        String[] arrOfStr;
        try {
            arrOfStr = Parser.parse(arr, 2);
        } catch (Exception ex) {
            return ui.outputEventError();
        }
        try {
            Task newTask = new Event(arrOfStr[0], arrOfStr[1]);
            ls.add(newTask);
            return ui.outputDeadlineEventMsg(ls, newTask);
        } catch (Exception ex) {
            return ui.outputEventFormatError();
        }
    }

    private String cmdDeadline(String[] arr) {
        String[] arrOfStr;
        try {
            arrOfStr = Parser.parse(arr, 1);
        } catch (Exception ex) {
            return ui.outputDeadlineError();
        }
        try {
            Task newTask = new Deadline(arrOfStr[0], arrOfStr[1]);
            ls.add(newTask);
            return ui.outputDeadlineEventMsg(ls, newTask);
        } catch (Exception ex) {
            return ui.outputDeadlineFormatError();
        }
    }

    private String cmdTodo(String description) {
        try {
            Task newTask = new Todo(description);
            ls.add(newTask);
            return ui.outputTodoMsg(ls, newTask);
        } catch (Exception ex) {
            return ui.outputTodoError();
        }
    }

    private String cmdDone(String str) {
        try {
            int numToBeMarkedAsDone = Parser.parseInt(str);
            Task tsk = ls.get(numToBeMarkedAsDone);
            tsk.markAsDone();
            ls.set(numToBeMarkedAsDone, tsk);
            return ui.outputDoneMsg(tsk);
        } catch (Exception ex) {
            return ui.outputDoneError();
        }
    }

    private String cmdList() {
        if (isListEmpty()) {
            return ui.outputListNoTask();
        }
        return ui.outputListTask(ls);
    }
}
