package duke.logic;

import java.io.FileNotFoundException;
import java.time.DateTimeException;

import duke.exception.DukeException;
import duke.model.Deadline;
import duke.model.DurationTask;
import duke.model.Event;
import duke.model.TaskList;
import duke.model.Todo;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Encapsulates a parser that will parse the command.
 */
public class CommandParser {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Creates a parser object.
     * @param ui is the UI component.
     * @param storage is the storage component.
     * @param tasks is the task list component.
     */
    public CommandParser(Ui ui, Storage storage, TaskList tasks) {
        this.ui = ui;
        this.storage = storage;
        this.tasks = tasks;
    }

    /**
     * parses the command and do the corresponding operation.
     * @param input is the command.
     * @return the output message.
     * @throws FileNotFoundException if file is not found.
     * @throws DukeException if the command is not recognized.
     * @throws DateTimeException handles date and time error.
     */
    public String parse(String input) throws FileNotFoundException, DukeException, DateTimeException {
        if (input.equals("list")) {
            return ui.printList(tasks.getData());
        } else if (input.startsWith("done")) {
            int n = DoneCommandParser.run(input);
            return tasks.done(n, ui, storage);
        } else if (input.startsWith("delete")) {
            int n = DeleteCommandParser.run(input);
            return tasks.delete(n, ui, storage);
        } else if (input.startsWith("durationtask")) {
            DurationTask task = DurationTaskCommandParser.run(input);
            return tasks.durationTask(task, ui, storage);
        } else if (input.startsWith("todo")) {
            Todo task = TodoCommandParser.run(input);
            return tasks.todo(task, ui, storage);
        } else if (input.startsWith("deadline")) {
            Deadline task = DeadlineCommandParser.run(input);
            return tasks.deadline(task, ui, storage);
        } else if (input.startsWith("event")) {
            Event task = EventCommandParser.run(input);
            return tasks.event(task, ui, storage);
        } else if (input.startsWith("find")) {
            String keyword = FindCommandParser.run(input);
            return tasks.find(keyword, ui);
        } else if (input.equals("bye")) {
            return "";
        } else {
            throw new DukeException("      OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
