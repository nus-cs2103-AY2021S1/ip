public class ToDoCommand implements Command {

    public final String command;

    public ToDoCommand(String command) {
        this.command = command;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String description = command.substring(4).trim();
        if (description.length() == 0) {
            throw new MissingDescriptionException();
        } else {
            ToDo t = new ToDo(description, false);
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
