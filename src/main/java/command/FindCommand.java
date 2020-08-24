package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import task.Task;
import task.TodoTask;

import java.util.Objects;

public class FindCommand extends Command {
    String findString;

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList filteredTaskList = taskList.find(this.findString);
        ui.displayMatchingTasks(filteredTaskList);
    }

    public FindCommand(String findString) {
        super(CommandType.Find);
        this.findString = findString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindCommand that = (FindCommand) o;
        return Objects.equals(findString, that.findString);
    }

}