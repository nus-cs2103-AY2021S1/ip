package duke.command;

import duke.Ui;
import duke.Storage;

import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;

import java.util.Arrays;

public class AddCommand extends Command {
    private CommandType commandType;
    private String[] description;

    public AddCommand(CommandType commandType, String... description) {
        this.commandType = commandType;
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        switch (this.commandType) {
        case TODO:
            ToDo toDo = new ToDo(this.description[0]);
            taskList.addTask(toDo);
            storage.addLine(toDo.toFileString());
            ui.showAddedTask(toDo.toString(), taskList.getNumTasks());
            break;
        case DEADLINE:
            Deadline deadline = new Deadline(this.description[0], this.description[1]);
            taskList.addTask(deadline);
            storage.addLine(deadline.toFileString());
            ui.showAddedTask(deadline.toString(), taskList.getNumTasks());
            break;
        case EVENT:
            Event event = new Event(this.description[0], this.description[1]);
            taskList.addTask(event);
            storage.addLine(event.toFileString());
            ui.showAddedTask(event.toString(), taskList.getNumTasks());
            break;
        default:
            break;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return this.commandType.toString() + " " + Arrays.toString(this.description);
    }
}
