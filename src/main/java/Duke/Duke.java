package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

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
     */
    private String parseCommand(String res) throws FileNotFoundException, DukeException {
        if (res.equals("list")) {
            return ui.printList(tasks.getData());
        } else if (res.startsWith("done")) {
            return tasks.done(res, ui, storage);
        } else if (res.startsWith("delete")) {
            return tasks.delete(res, ui, storage);
        } else if (res.startsWith("durationtask")) {
            return tasks.durationTask(res, ui, storage);
        } else if (res.startsWith("todo")) {
            return tasks.todo(res, ui, storage);
        } else if (res.startsWith("deadline")) {
            return tasks.deadline(res, ui, storage);
        } else if (res.startsWith("event")) {
            return tasks.event(res, ui, storage);
        } else if (res.startsWith("find")) {
            return tasks.find(res, ui);
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
    String getResponse(String input) {
        String res = "";
        try {
            res = parseCommand(input);
        } catch (NumberFormatException e) {
            res = ui.showNumberFormatError();
        } catch (DukeException e) {
            res = ui.showDukeError(e);
        } catch (FileNotFoundException e) {
            res = ui.showFileNotFoundError();
        }
        return res;
    }
}
