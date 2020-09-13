package duke.command;

import java.time.LocalDateTime;

import duke.exception.DukeException;
import duke.parser.DateTimeParser;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskType;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddDeadlineCommand extends AddCommand {

    private final String description;

    public AddDeadlineCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a {@code Deadline} task into the {@code TaskList}.
     *
     * @param tasks Task List object.
     * @param ui User Interface object.
     * @param storage Storage object.
     * @throws DukeException If input format is wrong.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] resultArr = Parser.parseTaskDescription(description, TaskType.DEADLINE);
        String deadline = getDeadline(resultArr);
        String taskDetails = getTaskDetails(resultArr);
        LocalDateTime dateTime = DateTimeParser.getDateTime(deadline);
        return addTask(new Deadline(taskDetails, dateTime), tasks, ui, storage);
    }

    private String getTaskDetails(String[] arr) {
        return arr[0];
    }

    private String getDeadline(String[] arr) {
        return arr[1];
    }
}
