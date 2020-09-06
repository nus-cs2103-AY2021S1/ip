package duke;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

/**
 * <h1>duke.Duke!</h1>
 * The duke.Duke program implements an application that
 * helps you keep track of your tasks.
 *
 * @author Augustine Kau
 * @version 0.1
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
        BYE, LIST, DONE, TODO, DEADLINE, EVENT, DELETE, CHECK, FIND
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
            assert(isFileInStorage()): "File should exist.";
            // rewrite the file to update latest changes
            Storage.saveFile(getStorageFile(), ls);
            return ui.outputBye();
        case LIST:
            if (ls.isEmpty()) {
                return ui.outputListNoTask();
            } else {
                return ui.outputListTask(ls);
            }
        case DONE:
            try {
                int numToBeMarkedAsDone = Parser.parseInt(str);
                Task tsk = ls.get(numToBeMarkedAsDone);
                tsk.markAsDone();
                ls.set(numToBeMarkedAsDone, tsk);
                return ui.outputDoneMsg(tsk);
            } catch (Exception ex) {
                return ui.outputDoneError();
            }
        case TODO:
            try {
                Task newTask = new Todo(arr[1]);
                ls.add(newTask);
                return ui.outputTodoMsg(ls, newTask);
            } catch (Exception ex) {
                return ui.outputTodoError();
            }
        case DEADLINE:
            try {
                String[] arrOfStr = Parser.parse(arr, 1);
                try {
                    Task newTask = new Deadline(arrOfStr[0], arrOfStr[1]);
                    ls.add(newTask);
                    return ui.outputDeadlineEventMsg(ls, newTask);
                } catch (Exception ex) {
                    return ui.outputDeadlineFormatError();
                }
            } catch (Exception ex) {
                return ui.outputDeadlineError();
            }
        case EVENT:
            try {
                String[] arrOfStr = Parser.parse(arr, 2);
                try {
                    Task newTask = new Event(arrOfStr[0], arrOfStr[1]);
                    ls.add(newTask);
                    return ui.outputDeadlineEventMsg(ls, newTask);
                } catch (Exception ex) {
                    return ui.outputEventFormatError();
                }
            } catch (Exception ex) {
                return ui.outputEventError();
            }
        case DELETE:
            try {
                int numToBeDeleted = Parser.parseInt(str);
                Task tsk = ls.get(numToBeDeleted);
                ls.remove(numToBeDeleted);
                return ui.outputDeleteMsg(ls, tsk);
            } catch (Exception ex) {
                return ui.outputDeleteError();
            }
        case CHECK:
            try {
                String checkDate = arr[1];
                LocalDate date = LocalDate.parse(checkDate);
                if (ls.isEmpty()) {
                    return ui.outputCheckNoTask();
                } else {
                    return ui.outputCheckTask(ls, date);
                }
            } catch (Exception ex) {
                return ui.outputCheckError();
            }
        case FIND:
            try {
                String keyword = arr[1];
                if (ls.isEmpty()) {
                    return ui.outputCheckNoTask();
                } else {
                    return ui.outputFindTask(ls, keyword);
                }
            } catch (Exception ex) {
                return ui.outputFindError();
            }
        default:
            return ui.outputInvalidInput();
        }
    }
}
