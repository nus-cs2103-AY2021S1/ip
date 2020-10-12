package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;

import duke.exception.DukeException;
import duke.logic.DeadlineParser;
import duke.logic.DeleteCommandParser;
import duke.logic.DoneCommandParser;
import duke.logic.DurationTaskCommandParser;
import duke.logic.EventParser;
import duke.logic.FindParser;
import duke.logic.TodoCommandParser;
import duke.model.Deadline;
import duke.model.DurationTask;
import duke.model.Event;
import duke.model.TaskList;
import duke.model.Todo;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a todo manager bot.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Duke bot.
     * @param filePath the path of the file which store the previous data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFile());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * parses the command and do the corresponding operation.
     * @param res is the resulted command.
     * @return the output string.
     * @throws FileNotFoundException if file is not found.
     * @throws DukeException if the command is not recognized.
     * @throws DateTimeException handles date and time error.
     */
    private String chooseCommand(String res) throws FileNotFoundException, DukeException, DateTimeException {
        if (res.equals("list")) {
            return ui.printList(tasks.getData());
        } else if (res.startsWith("done")) {
            int n = DoneCommandParser.run(res);
            return tasks.done(n, ui, storage);
        } else if (res.startsWith("delete")) {
            int n = DeleteCommandParser.run(res);
            return tasks.delete(n, ui, storage);
        } else if (res.startsWith("durationtask")) {
            DurationTask task = DurationTaskCommandParser.run(res);
            return tasks.durationTask(task, ui, storage);
        } else if (res.startsWith("todo")) {
            Todo task = TodoCommandParser.run(res);
            return tasks.todo(task, ui, storage);
        } else if (res.startsWith("deadline")) {
            Deadline task = DeadlineParser.run(res);
            return tasks.deadline(task, ui, storage);
        } else if (res.startsWith("event")) {
            Event task = EventParser.run(res);
            return tasks.event(task, ui, storage);
        } else if (res.startsWith("find")) {
            String keyword = FindParser.run(res);
            return tasks.find(keyword, ui);
        } else if (res.equals("bye")) {
            return "";
        } else {
            throw new DukeException("      OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Displays response in GUI.
     * @param input is the command input.
     * @return the response for the command.
     */
    public String getResponse(String input) {
        String res = "";
        try {
            res = chooseCommand(input);
        } catch (NumberFormatException e) {
            res = ui.showNumberFormatError();
        } catch (DukeException e) {
            res = ui.showDukeError(e);
        } catch (FileNotFoundException e) {
            res = ui.showFileNotFoundError();
        } catch (DateTimeException e) {
            res = ui.showDateTimeError();
        }
        return res;
    }
}
