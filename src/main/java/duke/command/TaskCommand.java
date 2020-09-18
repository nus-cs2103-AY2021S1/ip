package duke.command;


import duke.ImageType;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyDescriptionException;
import duke.exception.WrongFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskType;
import duke.task.Todo;

public class TaskCommand extends Command {
    protected TaskType taskType;
    protected String inputToAdd;

    public TaskCommand(TaskType taskType, String inputToAdd) {
        super(CommandType.TASK, ImageType.TICK);
        this.taskType = taskType;
        this.inputToAdd = inputToAdd;
    }

    @Override
    public String execute(Ui ui, TaskList taskList) throws EmptyDescriptionException,
            WrongFormatException {
        if (inputToAdd.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        try {
            switch (taskType) {
            case TODO:
                taskList.addTask(new Todo(inputToAdd, taskType));
                break;
            case DEADLINE:
                String[] deadlineSplit = inputToAdd.split(",");
                taskList.addTask(new Deadline(deadlineSplit[0], deadlineSplit[1], taskType));
                break;
            case EVENT:
                String[] eventSplit = inputToAdd.split(",");
                taskList.addTask(new Event(eventSplit[0], eventSplit[1], taskType));
                break;
            default:
            }
            Storage.saveTaskChanges(taskList);
            return ui.printAddAcknowledgement(taskList) + ui.printAdditionActionMessage();
        } catch (IndexOutOfBoundsException e) {
            throw new WrongFormatException();
        }
    }
}
