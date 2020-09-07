package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;

public class UpdateCommand extends Command {

    private int taskNo;
    private String type;
    private String content;

    public UpdateCommand(int taskNo, String type, String content) {
        super("update");
        this.taskNo = taskNo;
        this.type = type;
        this.content = content;
    }

    public UpdateCommand(int taskNo, String type) {
        super("update");
        this.taskNo = taskNo;
        this.type = type;
    }

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
