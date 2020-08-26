public class Command {
    private CommandType commandType;
    private int taskNumber;
    private String description;
    private String dateTime;
    private boolean isInProgram = true;

    public Command(CommandType commandType) {
        this.commandType = commandType;
    }

    public Command(CommandType commandType, int taskNumber) {
        this.commandType = commandType;
        this.taskNumber = taskNumber;
    }

    public Command(CommandType commandType, String description) {
        this.commandType = commandType;
        this.description = description;
    }

    public Command(CommandType commandType, String description, String dateTime) {
        this.commandType = commandType;
        this.description = description;
        this.dateTime = dateTime;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (commandType == CommandType.BYE) {
            ui.close();
            isInProgram = false;
        } else if (commandType == CommandType.LIST) {
            ui.listStoredTasks(taskList.getStoredTasks());
        } else if (commandType == CommandType.DONE) {
            ui.printDoneMessage(taskList.markTaskAsDone(taskNumber));
            storage.updateTasks(taskList);
        } else if (commandType == CommandType.DELETE) {
            ui.printDeleteMessage(taskList.deleteTask(taskNumber), taskList.getCount());
            storage.updateTasks(taskList);
        } else if (commandType == CommandType.TODO) {
            ToDo newTask = new ToDo(description);
            taskList.addTask(newTask);
            ui.printAddMessage(newTask, taskList.getCount());
            storage.updateTasks(taskList);
        } else if (commandType == CommandType.DEADLINE) {
            Deadline newTask = new Deadline(description, dateTime);
            taskList.addTask(newTask);
            ui.printAddMessage(newTask, taskList.getCount());
            storage.updateTasks(taskList);
        } else if (commandType == CommandType.EVENT) {
            Event newTask = new Event(description, dateTime);
            taskList.addTask(newTask);
            ui.printAddMessage(newTask, taskList.getCount());
            storage.updateTasks(taskList);
        } else {
            throw new DukeException("Wrong command type");
        }
    }

    public boolean isInProgram() {
        return isInProgram;
    }
}
