public class DeleteCommand implements Command {

    protected final String command;

    public DeleteCommand(String command) {
        this.command = command;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] commandArgs = command.split(" ");
        if (commandArgs.length < 2) {
            throw new MissingNumberFromCommandException();
        } else {
            int taskLength = taskList.tasks.size();
            int taskNumber = Integer.parseInt(commandArgs[1]) - 1;
            if (taskNumber < 0 || taskNumber > taskLength) {
                throw new InvalidNumberFromDoneCommandException();
            } else {
                Task t = taskList.tasks.get(taskNumber);
                taskList.deleteTask(taskNumber);
                storage.write(taskList.tasks);
                ui.showLine();
                ui.deleteCommandSuccessMessage(taskNumber + 1, t);
                ui.showLine();
            }
        }
    }

    public boolean isExit() {
        return false;
    }
}
