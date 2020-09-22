package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exception.DeleteOutOfRangeException;
import duke.exception.DoneOutOfRangeException;
import duke.exception.EmptyDeadlineException;
import duke.exception.EmptyEventException;
import duke.exception.EmptyTodoException;
import duke.exception.MissingDeadlineDateException;
import duke.exception.MissingDeleteArgumentException;
import duke.exception.MissingDoneArgumentException;
import duke.exception.MissingEventDateException;
import duke.exception.MissingFindArgumentException;
import duke.exception.UnknownCommandException;

/**
 * Duke Bot is a program that can schedule tasks and mark them as done, delete
 * and save them on a file.
 *
 * @author Lim Jin Feng
 * @version 0.1
 * @since   2020-08-20
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs Duke Bot in a given file path.
     *
     * @param filePath  Where Duke Bot will save it's tasks.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException e) {
            this.ui.sendFailedInitialiseMessage();
            this.tasks = new TaskList();
        }
    }

    /**
     * Overloaded constructor that constructs Duke Bot with default file path,
     * if it is not specified.
     */
    public Duke() {
        String filePath = System.getProperty("user.dir") + "/data";
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException e) {
            this.ui.sendFailedInitialiseMessage();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs Duke Bot.
     */
    public void run() {
        this.ui.sendInitialiseMessage();
        this.ui.sendBar();
        this.ui.greet();
        this.ui.sendBar();
        String userInput = this.ui.getUserInput();
        while (!userInput.equals("bye")) {
            this.ui.sendBar();
            try {
                ParseInfo parseInfo = Parser.parseAndExecute(userInput, this.tasks, this.ui);
                if (parseInfo.isListUndone()) {
                    if (storage.undo()) {
                        //if can undo, load from previous
                        this.tasks = new TaskList(this.storage.load());
                        this.ui.sendUndoSuccessMessage();
                    } else {
                        //if cannot undo, do nothing
                        this.ui.sendUndoFailMessage();
                    }
                }
                if (parseInfo.isListUpdated()) {
                    this.storage.save(tasks);
                }
            } catch (MissingDoneArgumentException | DoneOutOfRangeException | MissingDeleteArgumentException
                    | DeleteOutOfRangeException | EmptyTodoException | MissingDeadlineDateException
                    | EmptyDeadlineException | MissingEventDateException | EmptyEventException
                    | UnknownCommandException | MissingFindArgumentException e) {
                this.ui.sendExceptionMessage(e);
            } catch (DateTimeParseException e) {
                this.ui.sendExceptionMessage("\uD83D\uDE41 OOPS! Date should be in the format: YYYY-MM-DD");
            } catch (IOException e) {
                this.ui.sendExceptionMessage("\uD83D\uDE41 OOPS! Duke failed to save");
            }
            this.ui.sendBar();
            userInput = this.ui.getUserInput();
        }
        this.ui.sendBar();
        this.ui.bidFarewell();
        this.ui.sendBar();
    }

    public String getGreeting() {
        return this.ui.getGreeting();
    }

    public ArrayList<String> getResponses(String input) {
        ParseInfo parseInfo = new ParseInfo();
        try {
            parseInfo = Parser.parseAndExecuteAndGetMessage(input, this.tasks, this.ui);
            if (parseInfo.isListUndone()) {
                if (storage.undo()) {
                    //if can undo, load from previous
                    this.tasks = new TaskList(this.storage.load());
                    parseInfo.addResponse(
                            this.ui.getUndoSuccessMessage()
                    );
                } else {
                    //if cannot undo, do nothing
                    parseInfo.addResponse(
                            this.ui.getUndoFailMessage()
                    );
                }
            }
            if (parseInfo.isListUpdated()) {
                this.storage.save(tasks);
            }
        } catch (MissingDoneArgumentException | DoneOutOfRangeException | MissingDeleteArgumentException
                | DeleteOutOfRangeException | EmptyTodoException | MissingDeadlineDateException
                | EmptyDeadlineException | MissingEventDateException | EmptyEventException
                | UnknownCommandException | MissingFindArgumentException e) {
            parseInfo.addResponse(
                    this.ui.getExceptionMessage(e)
            );
        } catch (DateTimeParseException e) {
            //this.ui.sendExceptionMessage("\uD83D\uDE41 OOPS! Date should be in the format: YYYY-MM-DD");
            parseInfo.addResponse(
                    this.ui.getExceptionMessage("\uD83D\uDE41 OOPS! Date should be in the format: YYYY-MM-DD")
            );
        } catch (IOException e) {
            parseInfo.addResponse(
                    this.ui.getExceptionMessage("\uD83D\uDE41 OOPS! Duke failed to save")
            );
        }
        return parseInfo.getResponses();
    }

    public static void main(String[] args) {
        new Duke(System.getProperty("user.dir") + "/data").run();
    }
}
