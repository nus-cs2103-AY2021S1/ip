package duke.command;

import duke.exception.StorageException;
import duke.task.TaskList;
import duke.task.TaskType;

import java.time.LocalDate;

public class AddTaskCommand extends Command {
    private TaskType type;
    private String taskName;
    private LocalDate taskDate;

    public AddTaskCommand(TaskType type, String name){
        this.type = type;
        this.taskName = name;
    }

    public AddTaskCommand(TaskType type, String name, LocalDate date){
        this.type = type;
        this.taskName = name;
        this.taskDate = date;
    }
    @Override
    public void execute(TaskList list) throws StorageException {
        switch(this.type){
        case TODO:
            list.addTask(this.taskName);
            break;
        case EVENT:
        case DEADLINE:
            list.addTask(this.type, this.taskName, this.taskDate);
            break;
        }
    }
}
