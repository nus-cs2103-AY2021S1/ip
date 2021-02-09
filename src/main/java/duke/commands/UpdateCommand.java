package duke.commands;

import java.time.LocalDate;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UpdateCommand extends Command {

    private int taskNo;
    private String type;
    private String content;

    /**
     * Constructor of UpdateCommand.
     * @param taskNo The task number.
     * @param type The type of information to be updated.
     * @param content The new content.
     */
    public UpdateCommand(int taskNo, String type, String content) {
        super("update");
        this.taskNo = taskNo;
        this.type = type;
        this.content = content;
    }

    /**
     * Constructor of UpdateCommand
     * @param taskNo The task number.
     * @param type The type of information to be updated.
     */
    public UpdateCommand(int taskNo, String type) {
        super("update");
        this.taskNo = taskNo;
        this.type = type;
    }

    /**
     * Executes the command to update a specific task.
     * @param tasks The task list that is involved.
     * @param ui The UI of Duke.
     * @param storage The storage of Duke.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (type.equals("date")) {
            LocalDate date = LocalDate.parse(content);
            return tasks.updateDate(taskNo, date);
        } else if (type.equals("task")) {
            return tasks.updateTaskDescription(taskNo, content);
        } else if (type.equals("undo")) {
            return tasks.undoTask(taskNo);
        } else {
            // invalid type
            throw new DukeException("Invalid Update type");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
