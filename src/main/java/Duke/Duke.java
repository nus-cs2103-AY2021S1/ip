package duke;

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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

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
        } catch (FileNotFoundException e) {
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
                if(
                Parser.parseAndExecute(userInput, this.tasks, this.ui)
                ) {
                    this.storage.save(tasks);
                }
            } catch (MissingDoneArgumentException e) {
                this.ui.sendExceptionMessage(e);
            } catch (DoneOutOfRangeException e) {
                this.ui.sendExceptionMessage(e);
            } catch (MissingDeleteArgumentException e) {
                this.ui.sendExceptionMessage(e);
            } catch (DeleteOutOfRangeException e) {
                this.ui.sendExceptionMessage(e);
            } catch (EmptyTodoException e) {
                this.ui.sendExceptionMessage(e);
            } catch (MissingDeadlineDateException e) {
                this.ui.sendExceptionMessage(e);
            } catch (EmptyDeadlineException e) {
                this.ui.sendExceptionMessage(e);
            } catch (MissingEventDateException e) {
                this.ui.sendExceptionMessage(e);
            } catch (EmptyEventException e) {
                this.ui.sendExceptionMessage(e);
            } catch (UnknownCommandException e) {
                this.ui.sendExceptionMessage(e);
            } catch (IOException e) {
                this.ui.sendExceptionMessage(e);
            } catch (DateTimeParseException e) {
                this.ui.sendExceptionMessage("\uD83D\uDE41 OOPS! Date should be in the format: YYYY-MM-DD");
            } catch (MissingFindArgumentException e) {
                this.ui.sendExceptionMessage(e);
            }
            this.ui.sendBar();
            userInput = this.ui.getUserInput();
        }
        this.ui.sendBar();
        this.ui.bidFarewell();
        this.ui.sendBar();
    }

    public static void main(String[] args) {
        new Duke(System.getProperty("user.dir") + "/data/Duke.txt").run();
    }
}