public class AddCommand extends Command {

    private CommandType commandType;
    private String description;
    private String dateTime;

    public AddCommand(CommandType commandType, String description) {
        this.commandType = commandType;
        this.description = description;
    }

    public AddCommand(CommandType commandType, String description, String dateTime) {
        this.commandType = commandType;
        this.description = description;
        this.dateTime = dateTime;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (commandType == CommandType.TODO) {
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

    @Override
    public boolean isInProgram() {
        return true;
    }
}
