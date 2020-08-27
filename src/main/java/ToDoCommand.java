public class ToDoCommand implements Command {

    public final String command;

    public ToDoCommand(String command) {
        this.command = command;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] commandArgs = command.split(" ");
        if (commandArgs.length != 2) {
            throw new MissingDescriptionException();
        } else {
            ToDo t = new ToDo(commandArgs[1], false);
            taskList.addTask(t);
            storage.write(taskList.tasks);
            ui.showLine();
            ui.createToDoSuccessMessage(t, taskList.tasks.size());
            ui.showLine();
        }
    }

    public boolean isExit() {
        return false;
    }
}
